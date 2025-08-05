package io.stevengoh.portfolio.school_management_app.core.auth.services;

import io.stevengoh.portfolio.school_management_app.core.auth.AuthService;
import io.stevengoh.portfolio.school_management_app.core.auth.dtos.request.ReqLoginDto;
import io.stevengoh.portfolio.school_management_app.core.auth.dtos.request.ReqRefreshTokenDto;
import io.stevengoh.portfolio.school_management_app.core.auth.dtos.response.ResLoginDto;
import io.stevengoh.portfolio.school_management_app.core.auth.entities.CustomUserDetails;
import io.stevengoh.portfolio.school_management_app.modules.users.UserRepository;
import io.stevengoh.portfolio.school_management_app.modules.users.dtos.response.DetailedResUserDto;
import io.stevengoh.portfolio.school_management_app.modules.users.entities.User;
import io.stevengoh.portfolio.school_management_app.modules.users.mappers.UserResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserResponseMapper userResponseMapper;

    @Override
    public ResLoginDto login(ReqLoginDto request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String accessToken = jwtService.generateAccessToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        return new ResLoginDto(accessToken, refreshToken);
    }

    @Override
    public ResLoginDto refreshToken(ReqRefreshTokenDto request) {
        String refreshToken = request.getRefreshToken();
        String username = jwtService.getUsernameFromToken(refreshToken);

        User user = userRepository.findByUuid(UUID.fromString(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

//        if (!jwtService.isTokenValid(refreshToken, user)) {
//            throw new RuntimeException("Invalid refresh token");
//        }
//
//        String newAccessToken = jwtService.generateToken(user);
//        String newRefreshToken = jwtService.generateRefreshToken(user);

        return new ResLoginDto("newAccessToken", "newRefreshToken");
    }

    @Override
    public DetailedResUserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElse(null);

        return userResponseMapper.toDetailedDto(user);
    }
}
