package io.stevengoh.portfolio.school_management_app.components.seeders;

import io.stevengoh.portfolio.school_management_app.modules.institutions.InstitutionRepository;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import io.stevengoh.portfolio.school_management_app.modules.institutions.enums.InstitutionStatus;
import io.stevengoh.portfolio.school_management_app.modules.institutions.enums.InstitutionType;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(1)
@RequiredArgsConstructor
public class InstitutionSeeder implements CommandLineRunner {

    private final InstitutionRepository institutionRepository;

    @Override
    public void run(String... args) throws Exception {
        if (institutionRepository.count() > 0) {
            System.out.println("ℹ️ Institution table already has data, skipping seed");
            return;
        }

        Institution institution = new Institution();
        institution.setCode("CB");
        institution.setName("Sekolah Cinta Budaya");
        institution.setStatus(InstitutionStatus.ACTIVE);
        institution.setType(InstitutionType.SCHOOL);
        institution.setEmail("admin@smk123.sch.id");
        institution.setPhoneNumber("+62-812-3456-7890");
        institution.setAddress("Jl. Pendidikan No.1, Jakarta");

        institutionRepository.save(institution);
        System.out.println("✅ Institution seed data inserted");
    }
}
