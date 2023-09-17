package cartify.shop.shopcartify.serviceTest;

import cartify.shop.shopcartify.dto.reqests.NewProductRequest;
import cartify.shop.shopcartify.dto.responses.NewProductResponse;
import cartify.shop.shopcartify.services.interfaces.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @BeforeEach
    public void setUp(){

    }
    @Test
    public void testProductCanBeCreated(){
        String productName = "Water";
        BigDecimal price = new BigDecimal("100.99");
        NewProductRequest productRequest = createProductRequest(productName, price);

        NewProductResponse newProductResponse =  productService.addNewProduct(productRequest);
        assertEquals(productName, newProductResponse.getProductName());
        assertEquals(price, newProductResponse.getProductPrice());
    }

    private NewProductRequest createProductRequest(String productName, BigDecimal price) {
        NewProductRequest productRequest = new NewProductRequest();
        productRequest.setProductName(productName);
        productRequest.setProductPrice(price);
        productRequest.setProductDescription("new product description");
        productRequest.setProductImageUrl("url from cloudinary");

        return productRequest;
    }
}
