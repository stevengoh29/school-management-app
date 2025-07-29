package io.stevengoh.portfolio.school_management_app.modules.users.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.stevengoh.portfolio.school_management_app.common.entities.WithSoftDeleteBaseEntity;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import io.stevengoh.portfolio.school_management_app.modules.user_roles.entities.UserRole;
import io.stevengoh.portfolio.school_management_app.modules.users.enums.UserStatus;
import io.stevengoh.portfolio.school_management_app.common.enums.ActorType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "users")
@Getter
@Setter
@SQLRestriction("deleted_at IS NULL")
public class User extends WithSoftDeleteBaseEntity {
    private String username;

    @JsonIgnore
    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    private ActorType type;

    @ManyToOne
    private Institution institution;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> userRoles = new ArrayList<>();
}