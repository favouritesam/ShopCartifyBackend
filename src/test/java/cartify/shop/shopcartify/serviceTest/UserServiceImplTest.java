package cartify.shop.shopcartify.serviceTest;

import cartify.shop.shopcartify.dto.reqests.LoginRequest;
import cartify.shop.shopcartify.dto.reqests.UserProfileUpdateRequest;
import cartify.shop.shopcartify.dto.reqests.UserRegistrationRequest;
import cartify.shop.shopcartify.dto.responses.UserLoginResponse;
import cartify.shop.shopcartify.dto.responses.UserRegistrationResponse;
import cartify.shop.shopcartify.dto.responses.UserUpdateResponse;
import cartify.shop.shopcartify.exceptions.UserAlreadyExistsException;
import cartify.shop.shopcartify.exceptions.UserNotFoundException;
import cartify.shop.shopcartify.models.ShopCartifyUser;
import cartify.shop.shopcartify.repositories.UserRepository;
import cartify.shop.shopcartify.services.implementations.ShopCartifyUserService;
import cartify.shop.shopcartify.services.interfaces.UserService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Transactional
class UserServiceImplTest {



    @Autowired
    private ShopCartifyUserService userService;

    @Autowired
    private UserRepository userRepository;
    private UserRegistrationRequest userRegistrationRequest;

    @BeforeEach
    void setUp() {
        userRegistrationRequest = buildUserRegistration();

    }
    @Test
    void testToRegisterNewAUser() throws UserAlreadyExistsException {
        String foundUser = String.valueOf(userService.registerUser(userRegistrationRequest));
        assertNotNull(foundUser);
    }
    @Test
    void testFindUserById() throws UserNotFoundException {
        ShopCartifyUser user = new ShopCartifyUser();
        userRepository.save(user);

        Long userId = user.getUserId();
        Optional<ShopCartifyUser> foundUser = userService.findUserById(userId);

        assertTrue(foundUser.isPresent());
        assertEquals(userId, foundUser.get().getUserId());
    }

    @Test
    void testToFindAllUsers()  {
        UserRegistrationRequest user1 = new UserRegistrationRequest();
        userService.registerUser(user1);

        List<ShopCartifyUser> users = userService.findAllUsers();

        assertEquals(1, users.size());
    }
    private static UserRegistrationRequest buildUserRegistration(){
        UserRegistrationRequest registrationRequest = new UserRegistrationRequest();
        registrationRequest.setFirstName("Favour");
        registrationRequest.setLastName("Chiemela");
        registrationRequest.setPassword("2233");
        registrationRequest.setEmail("favourChi@gmail.com");
        return registrationRequest;
    }

    @Test
    void testUpdateUserProfile() throws UserNotFoundException {
        ShopCartifyUser user = new ShopCartifyUser();
        user.setFirstName("Femi");
        user.setLastName("Michael");
        user.setEmail("femi@gmail.com");

        ShopCartifyUser savedUser = userRepository.save(user);

        UserProfileUpdateRequest updatedUser = new UserProfileUpdateRequest();
        updatedUser.setFirstName("Femz");
        updatedUser.setLastName("femoo");

        Long userId = user.getUserId();

//        UserUpdateResponse returnedUser = userService.updateUserProfile(updatedUser.getUserId());
//
//        assertNotNull(returnedUser);
//        assertEquals(savedUser.getUserId(), returnedUser.getFirstName());
//        assertEquals(updatedUser.getFirstName(), returnedUser.getLastName());
    }

    @Test
    void testToDeleteUserById()  {
        try {
            userService.registerUser(userRegistrationRequest);
        } catch (UserAlreadyExistsException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        List<ShopCartifyUser> users = userService.findAllUsers();

        ShopCartifyUser lastRegisteredUser = users.get(users.size() - 1);
        try {
            userService.deleteUserById(lastRegisteredUser.getUserId());
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        users = userService.findAllUsers();

        assertEquals(0, users.size());
    }

    @Test
    void testToLoginUser() {
        LoginRequest registrationRequest = new LoginRequest();
        registrationRequest.setEmail("SexyFavour");
        registrationRequest.setPassword("2233");

        UserLoginResponse loginResponse = userService.loginUser(registrationRequest);
        assertEquals("You have logged in successfully", loginResponse.getMessage());
    }

}