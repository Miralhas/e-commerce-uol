package github.com.miralhas.ecommerce_uol.api.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordInput {

    @NotBlank
    @Size(min = 3)
    private String newPassword;
}
