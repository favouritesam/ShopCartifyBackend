package cartify.shop.shopcartify.services.interfaces.adminServices;

import cartify.shop.shopcartify.dto.reqests.SuperMarketAdminRegistrationRequest;
import cartify.shop.shopcartify.dto.responses.SuperMarketAdminRegistrationResponse;
import cartify.shop.shopcartify.models.ShopCartifyUser;
import cartify.shop.shopcartify.models.Supermarket;

import java.util.List;

public interface SupermarketAdminService {

    SuperMarketAdminRegistrationResponse registerSupermarketAdmin(SuperMarketAdminRegistrationRequest superMarketAdminRegistrationRequest);
     Supermarket findSupermarketAdminByEmail(String email);

    List<ShopCartifyUser> findAllSupermarketAdminById(List<Long> id);



}
