package github.com.miralhas.ecommerce_uol.domain.repository;

import github.com.miralhas.ecommerce_uol.domain.model.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<SalesOrder, Long>, JpaSpecificationExecutor<SalesOrder> {
    @Query("from SalesOrder o where MONTH(o.createdAt) = :month")
    List<SalesOrder> findAllOrdersByMonth(Integer month);
    @Query("from SalesOrder o where YEARWEEK(o.createdAt, 1) = YEARWEEK(CURDATE(), 1)")
    List<SalesOrder> findAllWeeklyOrders();
}