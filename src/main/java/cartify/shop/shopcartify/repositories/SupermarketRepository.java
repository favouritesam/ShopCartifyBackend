package cartify.shop.shopcartify.repositories;

import cartify.shop.shopcartify.models.Supermarket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupermarketRepository extends JpaRepository<Supermarket, Long> {
    Optional<Supermarket> findBySupermarketCode(String supermarketCode);
}
