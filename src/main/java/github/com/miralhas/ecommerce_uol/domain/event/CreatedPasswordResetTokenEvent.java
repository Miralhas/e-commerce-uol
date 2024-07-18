package github.com.miralhas.ecommerce_uol.domain.event;

import github.com.miralhas.ecommerce_uol.domain.model.PasswordResetToken;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreatedPasswordResetTokenEvent {
    public PasswordResetToken passwordResetToken;
}
