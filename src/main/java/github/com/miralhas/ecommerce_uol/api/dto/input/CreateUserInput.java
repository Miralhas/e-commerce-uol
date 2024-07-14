package github.com.miralhas.ecommerce_uol.api.dto.input;

import github.com.miralhas.ecommerce_uol.config.validation.PhoneNumberValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class CreateUserInput {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @PhoneNumberValidator
    private String telefone;

    @NotBlank
    @Size(min = 3)
    private String password;
}
