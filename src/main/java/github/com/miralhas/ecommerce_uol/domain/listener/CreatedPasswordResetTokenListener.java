package github.com.miralhas.ecommerce_uol.domain.listener;

import github.com.miralhas.ecommerce_uol.domain.event.CreatedPasswordResetTokenEvent;
import github.com.miralhas.ecommerce_uol.domain.service.SendEmailService;
import github.com.miralhas.ecommerce_uol.domain.service.SendEmailService.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreatedPasswordResetTokenListener {

    private final SendEmailService sendEmailService;

    @TransactionalEventListener
    public void onPasswordResetTokenCreated(CreatedPasswordResetTokenEvent event) {
        var user = event.getPasswordResetToken().getUser();
        var token = event.getPasswordResetToken().getToken();
        log.info("SENDING EMAIL TO USER: %s...".formatted(user.getEmail()));

        var message = Message.builder()
                .recipient(user.getEmail())
                .subject("%s - Account Verification".formatted(user.getUsername()))
                .body("created-reset-password-token.html")
                .model("user", user)
                .model("token", token)
                .build();

        sendEmailService.send(message);

        log.info("EMAIL SENT TO USER: %s...".formatted(user.getEmail()));
    }
}
