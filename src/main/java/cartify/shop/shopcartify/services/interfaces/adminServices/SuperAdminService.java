package cartify.shop.shopcartify.services.interfaces.adminServices;

import cartify.shop.shopcartify.models.ShopCartifyUser;
import cartify.shop.shopcartify.models.Supermarket;

import java.util.List;

public interface SuperAdminService {
    Supermarket findSuperAdminByEmail(String email);
    List<ShopCartifyUser> findAllSuperAdminById(List<Long> id);
}

