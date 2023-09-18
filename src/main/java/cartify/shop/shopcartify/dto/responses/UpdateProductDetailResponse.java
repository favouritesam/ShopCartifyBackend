package cartify.shop.shopcartify.dto.responses;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateProductDetailResponse {
    private BigDecimal ProductPrice;
}
