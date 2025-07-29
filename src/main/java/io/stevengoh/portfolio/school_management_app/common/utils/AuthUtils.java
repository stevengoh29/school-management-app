package io.stevengoh.portfolio.school_management_app.common.utils;

import io.stevengoh.portfolio.school_management_app.core.auth.entities.CustomUserDetails;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

public class AuthUtils {
    private static CustomUserDetails getCustomUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetails)) {
            throw new IllegalStateException("No authenticated user found or invalid principal");
        }
        return (CustomUserDetails) authentication.getPrincipal();
    }

    public static Institution getUserInstitution() {
        return getCustomUserDetails().getInstitution();
    }

    public static String getUsername() {
        return getCustomUserDetails().getUsername();
    }

    public static UUID getUserUuid() {
        return getCustomUserDetails().getUuid();
    }
}
