package github.com.miralhas.ecommerce_uol.domain.repository;

import github.com.miralhas.ecommerce_uol.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
