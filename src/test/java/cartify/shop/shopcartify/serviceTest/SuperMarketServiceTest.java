package cartify.shop.shopcartify.serviceTest;

import cartify.shop.shopcartify.dto.reqests.SupermarketRegistrationRequest;
import cartify.shop.shopcartify.dto.responses.SupermarketRegistrationResponse;
import cartify.shop.shopcartify.repositories.SupermarketRepository;
import cartify.shop.shopcartify.services.interfaces.SupermarketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SuperMarketServiceTest {
    @Autowired
    SupermarketService supermarketService;
    @Autowired
    SupermarketRepository supermarketRepository;

      @Test
    public void testSuperMarketCanRegister() {
        String superMarketName = "Fiddovea Supermarket";
        SupermarketRegistrationRequest supermarketRegistrationRequest = createSupermarketRegistrationRequest(superMarketName);
        SupermarketRegistrationResponse supermarketRegistrationResponse = supermarketService.registerNewSupermarket(supermarketRegistrationRequest);

        assertEquals(supermarketRegistrationResponse.getSupermarketEmail(), supermarketRegistrationRequest.getSupermarketEmail());
        assertEquals(supermarketRegistrationResponse.getSupermarketName(), supermarketRegistrationRequest.getSupermarketName());
    }
    @Test
    public void testSupermarketCanAddProduct() {
          
    }

    private SupermarketRegistrationRequest createSupermarketRegistrationRequest(String superMarketName) {
        return  SupermarketRegistrationRequest.builder()
                .supermarketName(superMarketName)
                .supermarketEmail("supermarketemail@supermarket.com")
                .cacUrl("cacurl://supermarket")
                .build();
    }
}
