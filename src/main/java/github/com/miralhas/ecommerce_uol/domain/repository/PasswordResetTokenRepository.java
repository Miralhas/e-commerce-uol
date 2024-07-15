package github.com.miralhas.ecommerce_uol.domain.repository;

import github.com.miralhas.ecommerce_uol.domain.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
}