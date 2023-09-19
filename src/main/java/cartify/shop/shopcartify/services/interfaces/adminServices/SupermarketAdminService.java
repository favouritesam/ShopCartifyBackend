package cartify.shop.shopcartify.services.interfaces.adminServices;

import cartify.shop.shopcartify.dto.reqests.SuperMarketAdminRegistrationRequest;
import cartify.shop.shopcartify.dto.responses.SuperMarketAdminRegistrationResponse;

public interface SupermarketAdminService {

    SuperMarketAdminRegistrationResponse registerSupermarketAdmin(SuperMarketAdminRegistrationRequest superMarketAdminRegistrationRequest);
}
