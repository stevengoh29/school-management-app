package io.stevengoh.portfolio.school_management_app.modules.user_institution_roles.entities;

import io.stevengoh.portfolio.school_management_app.common.entities.BaseEntity;
import io.stevengoh.portfolio.school_management_app.modules.institution_roles.entities.InstitutionRole;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import io.stevengoh.portfolio.school_management_app.modules.roles.entities.Role;
import io.stevengoh.portfolio.school_management_app.modules.users.entities.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "user_institution_roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInstitutionRole extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "institution_role_id")
    private InstitutionRole institutionRole;

    @ManyToOne(optional = false)
    @JoinColumn(name = "institution_id")
    private Institution institution;

    public UserInstitutionRole(User user, InstitutionRole institutionRole) {
        this.user = user;
        this.institutionRole = institutionRole;
    }
}