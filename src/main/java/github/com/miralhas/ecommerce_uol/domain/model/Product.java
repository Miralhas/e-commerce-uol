package github.com.miralhas.ecommerce_uol.domain.model;

import github.com.miralhas.ecommerce_uol.domain.exception.InsufficientProductStockException;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

    public enum Status {ACTIVE, INACTIVE}

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    public void subtractStock(Integer quantity) {
        var newStock = this.stock - quantity;
        if (newStock < 0) throw new InsufficientProductStockException(id);
        this.stock = newStock;
    }

    public boolean isInactive() {
        return status.equals(Status.INACTIVE);
    }

    public void activate() {
        this.status = Status.ACTIVE;
    }

    public void inactivate() {
        this.status = Status.INACTIVE;
    }
}
