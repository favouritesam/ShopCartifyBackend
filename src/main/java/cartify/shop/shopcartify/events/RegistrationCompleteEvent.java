package cartify.shop.shopcartify.events;

import cartify.shop.shopcartify.models.ShopCartifyUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {

    private ShopCartifyUser user;
    private String applicationUrl;


    public RegistrationCompleteEvent(ShopCartifyUser user, String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }
}
