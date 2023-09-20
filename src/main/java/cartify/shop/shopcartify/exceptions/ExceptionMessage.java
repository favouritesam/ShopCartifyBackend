package cartify.shop.shopcartify.exceptions;

public enum ExceptionMessage {

    USER_ALREADY_EXISTS("User with email %s already exists"),
    USER_NOT_FOUND_EXCEPTION("User not found"),
    USER_WITH_EMAIL_NOT_FOUND_EXCEPTION("User with email %s not found"),
    USER_REGISTRATION_FAILED_EXCEPTION("User registration failed"),
    ACCOUNT_ACTIVATION_FAILED_EXCEPTION("Account activation was not successful"),
    INVALID_CREDENTIALS_EXCEPTION("Invalid authentication credentials"),
    AUTHENTICATION_NOT_SUPPORTED("Authentication not supported on this system");


    ExceptionMessage(String message){
        this.message = message;
    }
    private final String message;

    public String getMessage(){
        return message;
    }
}
