package github.com.miralhas.ecommerce_uol.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.util.Objects;

//@Data
@Getter
@Setter
@Entity
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

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        OrderItem orderItem = (OrderItem) o;
        return getId() != null && Objects.equals(getId(), orderItem.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }


    @Override
    public String toString() {
        return "OrderItem{" +
                "product=" + product +
                ", totalPrice=" + totalPrice +
                ", quantity=" + quantity +
                ", id=" + id +
                '}';
    }
}
