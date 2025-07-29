package io.stevengoh.portfolio.school_management_app.core.auth.services;

import io.stevengoh.portfolio.school_management_app.core.auth.entities.CustomUserDetails;
import io.stevengoh.portfolio.school_management_app.modules.users.UserRepository;
import io.stevengoh.portfolio.school_management_app.modules.users.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return new CustomUserDetails(user);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        // You can map roles/permissions here. For now, return a dummy authority:
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
}