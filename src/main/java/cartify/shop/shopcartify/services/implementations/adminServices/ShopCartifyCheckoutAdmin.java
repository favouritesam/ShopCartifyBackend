package cartify.shop.shopcartify.services.implementations.adminServices;

import cartify.shop.shopcartify.factory.GeneratorFactory;
import cartify.shop.shopcartify.models.ShopCartifyUser;
import cartify.shop.shopcartify.models.Supermarket;
import cartify.shop.shopcartify.repositories.SupermarketRepository;
import cartify.shop.shopcartify.repositories.UserRepository;
import cartify.shop.shopcartify.services.interfaces.adminServices.CheckoutAdmin;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static cartify.shop.shopcartify.models.UserRole.CHECKOUT_ADMIN;

@Service
@AllArgsConstructor

public class ShopCartifyCheckoutAdmin implements CheckoutAdmin {

    private final UserRepository userRepository;
    private final SupermarketRepository supermarketRepository;
    @Override
    public Supermarket findSuperAdminByEmail(String email) {
        var foundSuperAdmin = supermarketRepository.findAllSuperAdminByEmail(Collections.singletonList(email));
        return (Supermarket) foundSuperAdmin;
        
    }
    @Override
    public List<ShopCartifyUser> findAllCheckoutAdminById(List<Long> id) {
        List<ShopCartifyUser> allAdmins = userRepository.findAll();
        List<ShopCartifyUser> checkoutAdmins = new ArrayList<>();

        for (int i = 0; i < allAdmins.size(); i++) {
            if(allAdmins.get(i).getRole().contains(CHECKOUT_ADMIN)){
                checkoutAdmins.add(allAdmins.get(i));
            }
        }
        return checkoutAdmins;
    }

}
