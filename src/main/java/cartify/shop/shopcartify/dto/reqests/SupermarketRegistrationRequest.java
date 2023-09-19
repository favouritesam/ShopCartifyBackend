package cartify.shop.shopcartify.dto.reqests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SupermarketRegistrationRequest {
    private String supermarketName;
    private String supermarketEmail;
    private String cacUrl;
}
