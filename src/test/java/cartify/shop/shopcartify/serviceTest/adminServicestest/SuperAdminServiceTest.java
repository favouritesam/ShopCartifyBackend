package cartify.shop.shopcartify.serviceTest.adminServicestest;

import cartify.shop.shopcartify.dto.reqests.SuperMarketAdminRegistrationRequest;
import cartify.shop.shopcartify.dto.responses.SuperMarketAdminRegistrationResponse;
import cartify.shop.shopcartify.models.ShopCartifyUser;
import cartify.shop.shopcartify.repositories.UserRepository;
import cartify.shop.shopcartify.services.interfaces.adminServices.SupermarketAdminService;
import jdk.jfr.Registered;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static cartify.shop.shopcartify.enums.UserRole.SUPER_ADMIN;

@SpringBootTest
public class SuperAdminServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SupermarketAdminService supermarketAdminService;
    private ShopCartifyUser superAdmin ;

    @BeforeEach
    public void setUp(){
        superAdmin = createSuperAdmin();
    }
    @Test
    public void testSupermarketAdminCanRegister(){
        SuperMarketAdminRegistrationResponse response =
                supermarketAdminService.registerSupermarketAdmin(createSuperMarketAdminRegistrationRequest());
    }
    private SuperMarketAdminRegistrationRequest createSuperMarketAdminRegistrationRequest(){
        SuperMarketAdminRegistrationRequest request = SuperMarketAdminRegistrationRequest.builder()
                .build();

                return request;
    }

    private ShopCartifyUser createSuperAdmin(){
        ShopCartifyUser superAdmin = ShopCartifyUser.builder()
                .email("superadmin@example.com")
                .role(SUPER_ADMIN)
                .password("P@ssw0rd")
                .firstName("Super Admin")
                .lastName("name")
                .isEnabled(true)
                .build();
        Optional<ShopCartifyUser> foundSuperAdmin = userRepository.findShopCartifyUserByEmail(superAdmin.getEmail());
        return foundSuperAdmin.orElseGet(() -> userRepository.save(superAdmin));

    }
    
}
