package cartify.shop.shopcartify.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Supermarket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String supermarketName;
    private String supermarketEmail;
    private String supermarketCode;
    private String cacUrl;
}
