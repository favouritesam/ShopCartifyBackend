package cartify.shop.shopcartify.services.interfaces;

import cartify.shop.shopcartify.dto.reqests.SupermarketRegistrationRequest;
import cartify.shop.shopcartify.dto.responses.SupermarketRegistrationResponse;
import cartify.shop.shopcartify.models.Supermarket;

import java.sql.ClientInfoStatus;
import java.util.List;
import java.util.Optional;

public interface SupermarketService {
    SupermarketRegistrationResponse registerNewSupermarket(SupermarketRegistrationRequest supermarketRegistrationRequest);
    Optional<Long> viewProduct(Long id);
    Supermarket findBySuperMarketCode(String code);
    List<Supermarket>findAllSupermarket();
    List<SupermarketAdmin> findByEmail(String email);

}
