package cartify.shop.shopcartify.repositories;

import cartify.shop.shopcartify.models.ShopCartifyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<ShopCartifyUser, Long> {
    Optional<ShopCartifyUser> findShopCartifyUserByEmail(String email);
}
