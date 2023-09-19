package cartify.shop.shopcartify.controller;

import cartify.shop.shopcartify.dto.reqests.LoginRequest;
import cartify.shop.shopcartify.dto.reqests.UserProfileUpdateRequest;
import cartify.shop.shopcartify.dto.reqests.UserRegistrationRequest;
import cartify.shop.shopcartify.dto.responses.UserLoginResponse;
import cartify.shop.shopcartify.dto.responses.UserUpdateResponse;
import cartify.shop.shopcartify.events.RegistrationCompleteEvent;
import cartify.shop.shopcartify.events.RegistrationCompleteEventListener;
import cartify.shop.shopcartify.exceptions.UserAlreadyExistsException;
import cartify.shop.shopcartify.exceptions.UserNotFoundException;
import cartify.shop.shopcartify.models.ShopCartifyUser;
import cartify.shop.shopcartify.models.VerificationToken;
import cartify.shop.shopcartify.repositories.VerificationTokenRepository;
import cartify.shop.shopcartify.services.interfaces.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class UserController {

    private final UserService userService;

    private  final RegistrationCompleteEventListener eventListener;

    private final HttpServletRequest servletRequest;

    private final ApplicationEventPublisher publisher;

    private final VerificationTokenRepository tokenRepository;



    @PostMapping("/register")
    public ResponseEntity<String> registerNewUser(@RequestBody UserRegistrationRequest userRegistrationRequest, final HttpServletRequest request) throws UserAlreadyExistsException {

        ShopCartifyUser user = userService.registerUser(userRegistrationRequest);

        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return new ResponseEntity<>("Success! Please check your email to complete your registration", HttpStatus.CREATED);
    }

    public String applicationUrl(HttpServletRequest request){
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody LoginRequest request){
        return new ResponseEntity<>(userService.loginUser(request), HttpStatus.OK);

    }

    @GetMapping("/verifyEmail")
    public String sendVerificationToken(@RequestParam("token") String token){
        String url = applicationUrl(servletRequest)+"/api/v1/register/resend-verification-token?token="+token;

        VerificationToken verifyToken = tokenRepository.findByToken(token);
        if(verifyToken.getUser().isEnabled()){
            return "This account has already been verified. please login";
        }
        String verificationResult = userService.validateToken(token);
        if (verificationResult.equalsIgnoreCase("valid")){
            return "Email verified successfully.";
        }
        return "Invalid verification link, <a href=\"" +url+"\"> Get a new verification link. </a>";
    }

    @GetMapping("/users")
    public List<ShopCartifyUser> findAllUser() {
        return userService.findAllUsers();
    }


    @PutMapping("/update-user")
    public ResponseEntity<UserUpdateResponse> updateUser(@RequestBody UserProfileUpdateRequest request) throws UserNotFoundException {
        return new ResponseEntity<>(userService.updateUserProfile(request), HttpStatus.OK);
    }

    @DeleteMapping("/delete-user/{id}")
    public String deleteUserById(@PathVariable("id") Long userId) throws UserNotFoundException {
        userService.deleteUserById(userId);
        return "User has been deleted successfully";
    }
}
