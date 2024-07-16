package github.com.miralhas.ecommerce_uol.api.controller;

import github.com.miralhas.ecommerce_uol.api.dto.LoginDTO;
import github.com.miralhas.ecommerce_uol.api.dto.UserDTO;
import github.com.miralhas.ecommerce_uol.api.dto.input.ChangePasswordInput;
import github.com.miralhas.ecommerce_uol.api.dto.input.CreateUserInput;
import github.com.miralhas.ecommerce_uol.api.dto.input.LoginInput;
import github.com.miralhas.ecommerce_uol.api.dto_mapper.UserMapper;
import github.com.miralhas.ecommerce_uol.api.dto_mapper.UserUnmapper;
import github.com.miralhas.ecommerce_uol.domain.model.User;
import github.com.miralhas.ecommerce_uol.domain.service.AuthenticationService;
import github.com.miralhas.ecommerce_uol.domain.service.PasswordResetService;
import github.com.miralhas.ecommerce_uol.domain.service.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final UserUnmapper userUnmapper;
    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;
    private final PasswordResetService passwordResetService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody @Valid CreateUserInput createUserInput) {
        User user = userUnmapper.toDomainObject(createUserInput);
        user = authenticationService.create(user);
        return userMapper.toModel(user);
    }


    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginDTO login(@RequestBody @Valid LoginInput loginInput) {
        var jwt = authenticationService.authenticate(loginInput);
        return new LoginDTO(jwt.getTokenValue(), TokenService.TOKEN_EXPIRATION_TIME);
    }

    @PutMapping("/resetPassword")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createPasswordResetToken(JwtAuthenticationToken authToken) {
        passwordResetService.createOrUpdateToken(authToken);
    }


    @PutMapping("/resetPassword/{token}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void resetPassword(@PathVariable UUID token, @RequestBody @Valid ChangePasswordInput changePasswordInput, JwtAuthenticationToken authToken) {
        passwordResetService.resetPassword(token, changePasswordInput, authToken);
    }


    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUser(JwtAuthenticationToken authToken) {
        User user = authenticationService.findUserByEmailOrException(authToken.getName());
        return userMapper.toModel(user);
    }

}