package github.com.miralhas.ecommerce_uol.domain.repository;

import github.com.miralhas.ecommerce_uol.domain.model.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<SalesOrder, Long> {

}