package cartify.shop.shopcartify.services.implementations;

import cartify.shop.shopcartify.dto.reqests.LoginRequest;
import cartify.shop.shopcartify.dto.reqests.UserProfileUpdateRequest;
import cartify.shop.shopcartify.dto.reqests.UserRegistrationRequest;
import cartify.shop.shopcartify.dto.responses.UserLoginResponse;
import cartify.shop.shopcartify.dto.responses.UserRegistrationResponse;
import cartify.shop.shopcartify.dto.responses.UserUpdateResponse;
import cartify.shop.shopcartify.exceptions.UserAlreadyExistsException;
import cartify.shop.shopcartify.exceptions.UserNotFoundException;

import cartify.shop.shopcartify.models.ShopCartifyUser;
import cartify.shop.shopcartify.models.VerificationToken;
import cartify.shop.shopcartify.repositories.UserRepository;
import cartify.shop.shopcartify.repositories.VerificationTokenRepository;
import cartify.shop.shopcartify.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

import static cartify.shop.shopcartify.models.UserRole.ADMIN;


@Service
@RequiredArgsConstructor
public class ShopCartifyUserService implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final VerificationTokenRepository tokenRepository;

    @Override
    public ShopCartifyUser registerUser(UserRegistrationRequest request) throws UserAlreadyExistsException {
        Optional<ShopCartifyUser> user = userRepository.findUserByEmail(request.getEmail());
        if (user.isPresent()) throw new UserAlreadyExistsException("User with email: " + request.getEmail() + " already exist");
        var newUser = new ShopCartifyUser();
        modelMapper.map(request, newUser);
        newUser.setRole(ADMIN);
        var savedUser = userRepository.save(newUser);
        UserRegistrationResponse response = modelMapper.map(savedUser, UserRegistrationResponse.class);
        response.setMessage("Success! please check your email to complete your registration");
        return savedUser;
    }

    @Override
    public String validateToken(String verifyToken) {
        VerificationToken token = tokenRepository.findByToken(verifyToken);
        if (token == null){
            return "Invalid verification token";
        }
        ShopCartifyUser user = token.getUser();
        Calendar calendar = Calendar.getInstance();
        if ((token.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0){
            return "Verification link already expired, "+
                    " Please, click on the link below to receive a new verification link";
        }
        user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }
    @Override
    public Optional<ShopCartifyUser> findUserById(Long id) throws UserNotFoundException {
        Optional<ShopCartifyUser> foundUser = userRepository.findById(id);
        if (foundUser.isPresent()) {
            return foundUser;
        } else {
            throw new UserNotFoundException("User not found with ID: " + id);
        }
    }
    @Override
    public List<ShopCartifyUser> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserUpdateResponse updateUserProfile(UserProfileUpdateRequest request) throws UserNotFoundException {
        Optional<ShopCartifyUser> existingUser = userRepository.findById(request.getUserId());
        if (existingUser.isEmpty()) {
            throw new UserNotFoundException("User not found.");
        }
        ShopCartifyUser foundUser = existingUser.get();

        if (request.getFirstName() != null && !request.getFirstName().isEmpty())foundUser.setFirstName(request.getFirstName());
        if (request.getLastName() != null && !request.getLastName().isEmpty())foundUser.setLastName(request.getLastName());
        var newUpdate = userRepository.save(foundUser);
        UserUpdateResponse response = new UserUpdateResponse();
        return response;
    }
    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    public UserLoginResponse loginUser(LoginRequest request) {
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        String username = request.getEmail();
        String password = request.getPassword();
        if (authenticate(username, password)) {
            userLoginResponse.setMessage("You have logged in successfully");
        } else {
            userLoginResponse.setMessage("Failed to log in");
        }
        return userLoginResponse;
    }

    @Override
    public Optional<ShopCartifyUser> findUserByEmail(String email) {
        return Optional.empty();
    }


    @Override
    public void saveUserVerificationToken(ShopCartifyUser theUser, String verifyToken) {
        var verificationToken = new VerificationToken(verifyToken, theUser);
        tokenRepository.save(verificationToken);
    }

    private boolean authenticate(String username, String password) {
        if (username.equals("SexyFavour") && password.equals("2233")) {
            return true;
        } else {
            return false;
        }
    }

}
