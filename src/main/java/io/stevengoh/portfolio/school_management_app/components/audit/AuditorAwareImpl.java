package io.stevengoh.portfolio.school_management_app.components.audit;

import io.stevengoh.portfolio.school_management_app.core.auth.entities.CustomUserDetails;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditorAware")
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof CustomUserDetails customUser) {
            return Optional.of(customUser.getUsername());
        } else if (principal instanceof String strPrincipal) {
            return Optional.of(strPrincipal); // fallback for basic auth
        }

        return Optional.empty();
    }
}