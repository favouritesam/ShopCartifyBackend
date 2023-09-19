package cartify.shop.shopcartify.services.interfaces;

import cartify.shop.shopcartify.dto.reqests.LoginRequest;
import cartify.shop.shopcartify.dto.reqests.UserProfileUpdateRequest;
import cartify.shop.shopcartify.dto.reqests.UserRegistrationRequest;
import cartify.shop.shopcartify.dto.responses.UserLoginResponse;
import cartify.shop.shopcartify.dto.responses.UserUpdateResponse;
import cartify.shop.shopcartify.exceptions.UserAlreadyExistsException;
import cartify.shop.shopcartify.exceptions.UserNotFoundException;
import cartify.shop.shopcartify.models.ShopCartifyUser;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    ShopCartifyUser registerUser(UserRegistrationRequest registrationRequest) throws UserAlreadyExistsException;


    String validateToken(String verifyToken);

    Optional<ShopCartifyUser> findUserById(Long id) throws UserNotFoundException;

    List<ShopCartifyUser> findAllUsers();


    UserUpdateResponse updateUserProfile(UserProfileUpdateRequest request) throws UserNotFoundException;

    void  deleteUserById(Long id) throws UserNotFoundException;


    UserLoginResponse loginUser(LoginRequest request);


    Optional<ShopCartifyUser> findUserByEmail(String email);

//    void saveUserVerificationToken(User theUser, String verifyToken);

    void saveUserVerificationToken(ShopCartifyUser theUser, String verifyToken);
}
