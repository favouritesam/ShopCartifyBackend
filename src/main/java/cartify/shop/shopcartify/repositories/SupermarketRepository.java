package cartify.shop.shopcartify.repositories;

import cartify.shop.shopcartify.models.Supermarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface SupermarketRepository  extends JpaRepository<Supermarket, Long> {
    Optional<Supermarket> findBySupermarketCode(String supermarketCode);
}
