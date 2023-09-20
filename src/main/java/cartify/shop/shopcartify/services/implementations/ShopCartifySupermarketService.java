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

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Long> viewProduct(Long id) {
        return Optional.empty();
    }

    @Override
    public Supermarket findBySuperMarketCode(String code) {
        return null;
    }

    @Override
    public List<Supermarket> findAllSupermarket() {
        return null;
    }

    @Override
    public List<SupermarketAdmin> findByEmail(String email) {
        return null;
    }

//    @Override
//    public ClassRoom findById(String id) {
//        classRoomRepo.findByClassLevel(id);
//
//        return new ClassRoom();
//    }
}
