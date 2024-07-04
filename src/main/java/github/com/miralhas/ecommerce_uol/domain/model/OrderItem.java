package github.com.miralhas.ecommerce_uol.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

//@Data
@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderItem {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(nullable = false)
    private SalesOrder salesOrder;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Product product;

    public void calculateTotalValue() {
        var value = product.getPrice();
        this.totalPrice = value.multiply(BigDecimal.valueOf(quantity));
    }
}
