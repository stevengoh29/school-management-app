package io.stevengoh.portfolio.school_management_app.modules.users;

import io.stevengoh.portfolio.school_management_app.modules.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findByUuid(UUID uuid);
    Optional<User> findByUsername(String username);
}
