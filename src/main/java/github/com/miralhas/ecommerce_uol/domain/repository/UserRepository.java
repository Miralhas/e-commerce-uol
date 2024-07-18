package github.com.miralhas.ecommerce_uol.domain.repository;

import github.com.miralhas.ecommerce_uol.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("from User u where u.email = :email")
    Optional<User> findUserByEmail(String email);

    @Query("from User u where u.username = :username")
    Optional<User> findUserByUsername(String username);

}
