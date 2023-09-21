package cartify.shop.shopcartify.services.implementations.adminServices;

import cartify.shop.shopcartify.models.ShopCartifyUser;
import cartify.shop.shopcartify.models.Supermarket;
import cartify.shop.shopcartify.models.UserRole;
import cartify.shop.shopcartify.repositories.UserRepository;
import cartify.shop.shopcartify.services.interfaces.adminServices.SuperAdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static cartify.shop.shopcartify.models.UserRole.SUPER_ADMIN;
@Service
@AllArgsConstructor
public class ShopCartifySuperAdminService implements SuperAdminService {
    public final UserRepository userRepository;

    @Override
    public Supermarket findSuperAdminByEmail(String email) {
        return null;
    }

    @Override
    public List<ShopCartifyUser> findAllSuperAdminById(List<Long> id) {
        return getShopCartifyUsers(userRepository, SUPER_ADMIN);
    }

    static List<ShopCartifyUser> getShopCartifyUsers(UserRepository userRepository, UserRole userRole) {
        List<ShopCartifyUser> allAdmins = userRepository.findAll();
        List<ShopCartifyUser> superAdmins = new ArrayList<>();

        for (ShopCartifyUser allAdmin : allAdmins) {
            if (allAdmin.getRole().contains(userRole)) {
                superAdmins.add(allAdmin);
            }
        }
        return superAdmins;
    }

}
