package cartify.shop.shopcartify.services.interfaces.adminServices;

import cartify.shop.shopcartify.models.ShopCartifyUser;
import cartify.shop.shopcartify.models.Supermarket;

import java.util.List;

public interface CheckoutAdmin {

    Supermarket findSuperAdminByEmail(String email);
    List<ShopCartifyUser> findAllCheckoutAdminById(List<Long> id);
}
