package cartify.shop.shopcartify.services.implementations.adminServices;

import cartify.shop.shopcartify.dto.reqests.SuperMarketAdminRegistrationRequest;
import cartify.shop.shopcartify.dto.responses.SuperMarketAdminRegistrationResponse;
import cartify.shop.shopcartify.models.ShopCartifyUser;
import cartify.shop.shopcartify.models.Supermarket;
import cartify.shop.shopcartify.repositories.SupermarketRepository;
import cartify.shop.shopcartify.repositories.UserRepository;
import cartify.shop.shopcartify.services.interfaces.adminServices.SupermarketAdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static cartify.shop.shopcartify.models.UserRole.SUPERMARKET_ADMIN;
import static cartify.shop.shopcartify.services.implementations.adminServices.ShopCartifySuperAdminService.getShopCartifyUsers;

@AllArgsConstructor
@Service
public class ShopCartifySupermarketAdminService implements SupermarketAdminService {
    public final SupermarketRepository supermarketRepository;
    public final UserRepository userRepository;
    @Override
    public SuperMarketAdminRegistrationResponse registerSupermarketAdmin(SuperMarketAdminRegistrationRequest superMarketAdminRegistrationRequest) {
        return null;
    }

    @Override
    public Supermarket findSupermarketAdminByEmail(String email) {
        return null;
    }



    @Override
    public List<ShopCartifyUser> findAllSupermarketAdminById(List<Long> id) {
        return getShopCartifyUsers(userRepository, SUPERMARKET_ADMIN);
    }

}
