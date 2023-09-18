package cartify.shop.shopcartify.services.implementations;

import cartify.shop.shopcartify.dto.reqests.SupermarketRegistrationRequest;
import cartify.shop.shopcartify.dto.responses.SupermarketRegistrationResponse;
import cartify.shop.shopcartify.models.Supermarket;
import cartify.shop.shopcartify.services.interfaces.SupermarketService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ShopCartifySupermarketService implements SupermarketService {
    private final ModelMapper mapper = new ModelMapper();
    @Override
    public SupermarketRegistrationResponse registerNewSupermarket(SupermarketRegistrationRequest supermarketRegistrationRequest) {
        mapper.map(supermarketRegistrationRequest, Supermarket.class);
        return null;

    }
}
