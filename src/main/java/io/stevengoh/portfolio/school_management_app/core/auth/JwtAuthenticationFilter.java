package io.stevengoh.portfolio.school_management_app.core.auth;

import io.stevengoh.portfolio.school_management_app.common.constant.CookiesConstant;
import io.stevengoh.portfolio.school_management_app.common.exceptions.UnauthorizedException;
import io.stevengoh.portfolio.school_management_app.core.auth.entities.CustomUserDetails;
import io.stevengoh.portfolio.school_management_app.core.auth.services.JwtTokenService;
import io.stevengoh.portfolio.school_management_app.core.auth.services.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;
    private final UserDetailsServiceImpl userDetailsService;

    public JwtAuthenticationFilter(JwtTokenService jwtTokenService, UserDetailsServiceImpl userDetailsService) {
        this.jwtTokenService = jwtTokenService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String accessToken = jwtTokenService.extractAccessTokenFromCookie(request);
        String refreshToken = jwtTokenService.extractRefreshTokenFromCookie(request);

        System.out.println("Access token: " + accessToken);
        System.out.println("Refresh token: " + refreshToken);

        if (Objects.equals(request.getMethod(), "OPTIONS")) {
            filterChain.doFilter(request, response);
            return;
        }

        String path = request.getRequestURI();
        if (path.equals("/auth/login") || path.equals("/auth/register") || path.equals("/auth/logout")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            if (accessToken == null && refreshToken == null) throw new UnauthorizedException("You are not authorized to access this resource");

            // ✅ Try Access Token
            if (accessToken != null && jwtTokenService.validateToken(accessToken)) {
                String username = jwtTokenService.getUsernameFromToken(accessToken);

                UserDetails user = userDetailsService.loadUserByUsername(username);
                // Credential NULL because at this point, password is not needed as we are mainly working with the token already
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(auth);
            } else if (refreshToken != null && jwtTokenService.validateToken(refreshToken)) {
                // 🔄 Fallback: Try Refresh Token
                String username = jwtTokenService.getUsernameFromToken(refreshToken);
                UserDetails user = userDetailsService.loadUserByUsername(username);

                // Optional: regenerate new access token and set it as a cookie
                String newAccessToken = jwtTokenService.generateAccessToken(user);
                jwtTokenService.setAccessTokenAsCookie(response, newAccessToken);

                String newRefreshToken = jwtTokenService.generateRefreshToken(user);
                jwtTokenService.setRefreshTokenAsCookie(response, newRefreshToken);

                // Credential NULL because at this point, password is not needed as we are mainly working with the token already
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            } else {
                // ❌ No valid token at all
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Unauthorized: Invalid or missing token");
                return;
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: " + e.getMessage());
            return;
        }

        filterChain.doFilter(request, response);
    }
}