package cartify.shop.shopcartify.dto.reqests;


import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class SuperMarketAdminRegistrationRequest {
    private String email;
}
