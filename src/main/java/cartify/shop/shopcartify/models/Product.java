package cartify.shop.shopcartify.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private String productImageUrl;
    private BigDecimal productPrice;
    private String productQrCodeUrl;
    private String productDescription;
    private String supermarketCode;
}
