package cartify.shop.shopcartify.dto.reqests;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class UpdateProductDetailRequest {
    private BigDecimal ProductPrice;
    private String productName;
}
