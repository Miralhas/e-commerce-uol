package github.com.miralhas.ecommerce_uol.domain.service;

import github.com.miralhas.ecommerce_uol.api.dto.input.ChangePasswordInput;
import github.com.miralhas.ecommerce_uol.domain.exception.BusinessException;
import github.com.miralhas.ecommerce_uol.domain.model.PasswordResetToken;
import github.com.miralhas.ecommerce_uol.domain.model.User;
import github.com.miralhas.ecommerce_uol.domain.repository.PasswordResetTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PasswordResetService {

    private final AuthenticationService authenticationService;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final PasswordEncoder passwordEncoder;


    public PasswordResetToken findResetPasswordTokenById(Long id) {
        return passwordResetTokenRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Usuário não possui nenhum token de troca de senha"));
    }


    @Transactional
    public void resetPassword(UUID resetPasswordToken, ChangePasswordInput changePasswordInput, JwtAuthenticationToken authToken) {
        var user = authenticationService.findUserByEmailOrException(authToken.getName());
        var userPasswordResetToken = findResetPasswordTokenById(user.getId());
        validateToken(resetPasswordToken.toString(), userPasswordResetToken.getToken());
        user.setPassword(passwordEncoder.encode(changePasswordInput.getNewPassword()));
        passwordResetTokenRepository.delete(userPasswordResetToken);
    }


    private void validateToken(String firstToken, String secondToken) {
        if (!Objects.equals(firstToken, secondToken)) throw new BusinessException("Token inválido");
    }


    @Transactional
    public PasswordResetToken create(JwtAuthenticationToken authToken) {
        User user = authenticationService.findUserByEmailOrException(authToken.getName());
        var tokenOptional = passwordResetTokenRepository.findById(user.getId());
        if (tokenOptional.isPresent()) {
            var token = tokenOptional.get();
            token.setToken(UUID.randomUUID().toString());
            return passwordResetTokenRepository.save(token);
        }
        PasswordResetToken token = createResetTokenObject(user);
        return passwordResetTokenRepository.save(token);
    }

    private PasswordResetToken createResetTokenObject(User user) {
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(UUID.randomUUID().toString());
        resetToken.setUser(user);
        return resetToken;
    }
}
