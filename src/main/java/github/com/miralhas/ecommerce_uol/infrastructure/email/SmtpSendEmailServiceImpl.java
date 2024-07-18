package github.com.miralhas.ecommerce_uol.infrastructure.email;

import freemarker.template.Configuration;
import freemarker.template.Template;
import github.com.miralhas.ecommerce_uol.domain.service.SendEmailService;
import github.com.miralhas.ecommerce_uol.infrastructure.exception.EmailException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

@Service
@RequiredArgsConstructor
public class SmtpSendEmailServiceImpl implements SendEmailService {

    @Value("${compass.mail.sender}")
    private String emailProperties;
    private final JavaMailSender javaMailSender;
    private final Configuration freeMarkerConfig;

    @Override
    public void send(Message message){
        try {
            String body = processTemplate(message);

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setFrom(emailProperties);
            helper.setTo(message.getRecipients().toArray(new String[]{}));
            helper.setSubject(message.getSubject());
            helper.setText(body, true);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new EmailException("Não foi possível enviar e-mail", e);
        }
    }

    private String processTemplate(Message message) {
        try {
            Template template = freeMarkerConfig.getTemplate(message.getBody());
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, message.getModels());
        } catch (Exception e) {
            throw new EmailException("Não foi possível processar o template do e-mail", e);
        }
    }
}
