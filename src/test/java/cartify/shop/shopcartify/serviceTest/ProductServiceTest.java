package cartify.shop.shopcartify.serviceTest;

import cartify.shop.shopcartify.dto.reqests.NewProductRequest;
import cartify.shop.shopcartify.dto.reqests.UpdateProductDetailRequest;
import cartify.shop.shopcartify.dto.responses.NewProductResponse;
import cartify.shop.shopcartify.dto.responses.UpdateProductDetailResponse;
import cartify.shop.shopcartify.models.Product;
import cartify.shop.shopcartify.replica.ProductReplica;
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
        String productName = "Milo";
        BigDecimal price = new BigDecimal("100.99");
        NewProductRequest productRequest = createProductRequest(productName, price);

        NewProductResponse newProductResponse =  productService.addNewProduct(productRequest);
        assertEquals(productName, newProductResponse.getProductName());
        assertEquals(price, newProductResponse.getProductPrice());
    }
    @Test
    public void testFindProductByProductName(){
        String productName = "Ice Cream";
        BigDecimal price = new BigDecimal("105.39");
        NewProductRequest productRequest = createProductRequest(productName, price);

        NewProductResponse newProductResponse =  productService.addNewProduct(productRequest);
        ProductReplica productReplica = productService.findProductReplicaByProductName(productName);

        assertEquals(productName, productReplica.getProductName());
        assertEquals(price, productReplica.getProductPrice());
    }

    @Test
    public void testProductPriceCanBeChanged(){
        String productName = "Bottle Water";
        BigDecimal price = new BigDecimal("100.99");
        NewProductRequest productRequest = createProductRequest(productName, price);

        NewProductResponse newProductResponse =  productService.addNewProduct(productRequest);
        assertEquals(productName, newProductResponse.getProductName());
        assertEquals(price, newProductResponse.getProductPrice());

        UpdateProductDetailRequest updateProductDetailRequest = new UpdateProductDetailRequest();
        updateProductDetailRequest.setProductPrice(new BigDecimal("150.88"));
        updateProductDetailRequest.setProductName(productName);
        UpdateProductDetailResponse updateProductResponse = productService.updateProductDetail(updateProductDetailRequest);

        assertEquals(productName, newProductResponse.getProductName());
        assertEquals(new BigDecimal("150.88"), updateProductResponse.getProductPrice());

        Product product = productService.findProductByProductName(productName);

        assertEquals(new BigDecimal("150.88"), product.getProductPrice());


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
