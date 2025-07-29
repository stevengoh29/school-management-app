package io.stevengoh.portfolio.school_management_app.components.seeders;

import io.stevengoh.portfolio.school_management_app.common.enums.ActorType;
import io.stevengoh.portfolio.school_management_app.modules.institutions.InstitutionRepository;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import io.stevengoh.portfolio.school_management_app.modules.users.UserRepository;
import io.stevengoh.portfolio.school_management_app.modules.users.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Order(2)
@RequiredArgsConstructor
public class UserSeeder implements CommandLineRunner {
    private final UserRepository userRepository;
    private final InstitutionRepository institutionRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count() > 0) {
            System.out.println("ℹ️ User table already has data, skipping seed");
            return;
        }

        Institution institution = institutionRepository.findAll().getFirst();

        User user = new User();
        user.setUsername("john_wick");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setType(ActorType.STAFF);
        user.setInstitution(institution);
        userRepository.save(user);

        System.out.println("✅ User seed data inserted");
    }
}
