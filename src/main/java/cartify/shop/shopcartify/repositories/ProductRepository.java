package cartify.shop.shopcartify.repositories;


import cartify.shop.shopcartify.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductByProductName(String productName);

}
