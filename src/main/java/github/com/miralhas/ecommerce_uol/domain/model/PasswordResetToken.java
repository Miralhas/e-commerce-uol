package github.com.miralhas.ecommerce_uol.domain.model;

import github.com.miralhas.ecommerce_uol.domain.event.CreatedPasswordResetTokenEvent;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Objects;

@Getter
@Setter
@Entity
public class PasswordResetToken extends AbstractAggregateRoot<PasswordResetToken> {

    @Id
    private Long id;

    @Column(nullable = false)
    private String token;

    @MapsId
    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User user;

    public void publishCreatedPasswordResetTokenEvent() {
        registerEvent(new CreatedPasswordResetTokenEvent(this));
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        PasswordResetToken that = (PasswordResetToken) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
