package cartify.shop.shopcartify.dto.reqests;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class NewProductRequest {
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private String productImageUrl;
    private String productQrCodeUrl;
}
