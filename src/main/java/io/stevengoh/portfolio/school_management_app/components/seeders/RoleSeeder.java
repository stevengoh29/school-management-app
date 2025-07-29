package io.stevengoh.portfolio.school_management_app.components.seeders;

import io.stevengoh.portfolio.school_management_app.common.enums.ActorType;
import io.stevengoh.portfolio.school_management_app.modules.roles.RoleRepository;
import io.stevengoh.portfolio.school_management_app.modules.roles.entities.Role;
import io.stevengoh.portfolio.school_management_app.modules.roles.enums.RoleStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(3)
@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner {
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if(roleRepository.count() > 0) {
            System.out.println("ℹ️ Role table already has data, skipping seed");
            return;
        }

        Role role = new Role();
        role.setName("ROLE_ADMIN");
        role.setStatus(RoleStatus.ACTIVE);
        role.setIsDefaultRole(true);
        role.setActorType(ActorType.STAFF);

        Role role2 = new Role();
        role2.setName("ROLE_ADMIN_2");
        role2.setStatus(RoleStatus.ACTIVE);
        role2.setIsDefaultRole(true);
        role2.setActorType(ActorType.STAFF);

        roleRepository.save(role);
        roleRepository.save(role2);

        System.out.println("✅ Role seed data inserted");
    }
}
