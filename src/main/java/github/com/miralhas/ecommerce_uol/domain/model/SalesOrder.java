package github.com.miralhas.ecommerce_uol.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import github.com.miralhas.ecommerce_uol.domain.exception.BusinessException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
public class SalesOrder {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal totalValue;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.CREATED;

    @OneToMany(mappedBy = "salesOrder", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("salesOrder")
    private List<OrderItem> items;

    public void calculateTotalPrice() {
        items.forEach(OrderItem::calculateTotalValue);
        this.totalValue = items.stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public void confirm() {
        validateStatus();
        validateItemsLength();
        this.status = OrderStatus.CONFIRMED;
    }


    public void cancel() {
        validateStatus();
        this.status = OrderStatus.CANCELED;
    }


    private void validateItemsLength() {
        if (items.isEmpty()) {
            throw new BusinessException("Uma venda tem que ter no mínimo 1 produto para ser concluída");
        }
    }


    private void validateStatus() {
        if (status != OrderStatus.CREATED) {
            throw new BusinessException("Status desse pedido já foi alterado anteriormente");
        }
    }


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        SalesOrder order = (SalesOrder) o;
        return getId() != null && Objects.equals(getId(), order.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return "SalesOrder{" +
                "id=" + id +
                ", totalValue=" + totalValue +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
