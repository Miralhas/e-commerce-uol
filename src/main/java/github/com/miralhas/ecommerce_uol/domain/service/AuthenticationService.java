package github.com.miralhas.ecommerce_uol.domain.service;

import github.com.miralhas.ecommerce_uol.domain.exception.UserAlreadyExistsException;
import github.com.miralhas.ecommerce_uol.domain.model.Role;
import github.com.miralhas.ecommerce_uol.domain.model.User;
import github.com.miralhas.ecommerce_uol.domain.repository.RoleRepository;
import github.com.miralhas.ecommerce_uol.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public User findByEmailOrException(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> {
                    var message = "Usuário de email '%s' não foi encontrado".formatted(email);
                    return new UsernameNotFoundException(message);
                });
    }

    public User findUserByIdOrException(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    var message = "Usuário de código '%s' não foi encontrado".formatted(id);
                    return new UsernameNotFoundException(message);
                });
    }


    @Transactional
    public User create(User user) {
        checkIfUsernameOrEmailAreAvailiable(user);
        var userRole = roleRepository.findRoleByName(Role.Value.USER);
        user.setRoles(Set.of(userRole));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    private void checkIfUsernameOrEmailAreAvailiable(User user) {
        userRepository.findUserByEmail(user.getEmail())
                .map(u -> {
                    throw new UserAlreadyExistsException(String.format("E-mail '%s' já está em uso", user.getEmail()));
                });
        userRepository.findUserByUsername(user.getUsername())
                .map(u -> {
                    throw new UserAlreadyExistsException(String.format("Username '%s' já está em uso", user.getUsername()));
                });
    }
}