package cartify.shop.shopcartify.services.interfaces;

import cartify.shop.shopcartify.dto.reqests.SupermarketRegistrationRequest;
import cartify.shop.shopcartify.dto.responses.SupermarketRegistrationResponse;
import cartify.shop.shopcartify.models.Supermarket;

import java.util.List;

public interface SupermarketService {
    SupermarketRegistrationResponse registerNewSupermarket(SupermarketRegistrationRequest supermarketRegistrationRequest);
    Supermarket findProductById(Long id);
    Supermarket findBySuperMarketCode(String code);
    List<Supermarket>findAllSupermarket(List<Long> id);

}
