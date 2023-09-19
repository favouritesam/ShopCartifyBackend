package cartify.shop.shopcartify.services.implementations;

import cartify.shop.shopcartify.dto.reqests.SupermarketRegistrationRequest;
import cartify.shop.shopcartify.dto.responses.SupermarketRegistrationResponse;
import cartify.shop.shopcartify.factory.GeneratorFactory;
import cartify.shop.shopcartify.models.Supermarket;
import cartify.shop.shopcartify.repositories.SupermarketRepository;
import cartify.shop.shopcartify.services.interfaces.SupermarketService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class ShopCartifySupermarketService implements SupermarketService {
    private final ModelMapper mapper = new ModelMapper();
    private final GeneratorFactory generatorFactory ;

    private final SupermarketRepository supermarketRepository;
    @Override
    public SupermarketRegistrationResponse registerNewSupermarket(SupermarketRegistrationRequest supermarketRegistrationRequest) {
        Supermarket supermarket = mapper.map(supermarketRegistrationRequest, Supermarket.class);
        String supermarketCode = generatorFactory.generateSupermarketCode();

        supermarket.setSupermarketCode(supermarketCode);

        Supermarket savedSupermarket = supermarketRepository.save(supermarket);

        return SupermarketRegistrationResponse.builder()
                .supermarketCode(savedSupermarket.getSupermarketCode())
                .supermarketName(savedSupermarket.getSupermarketName())
                .supermarketEmail(savedSupermarket.getSupermarketEmail())
                .message("Supermarket Saved Successfully")
                .build();
    }
}
