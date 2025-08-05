package io.stevengoh.portfolio.school_management_app.core.auth.services;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.stevengoh.portfolio.school_management_app.common.constant.CookiesConstant;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtTokenService {

    private static final Logger log = LoggerFactory.getLogger(JwtTokenService.class);
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.access-token-expiration-ms}")
    private long accessTokenExpirationMs;

    @Value("${jwt.refresh-token-expiration-ms}")
    private long refreshTokenExpirationMs;

    private static final String ACCESS_TOKEN_COOKIE = CookiesConstant.ACCESS_TOKEN_COOKIE;
    private static final String REFRESH_TOKEN_COOKIE = CookiesConstant.REFRESH_TOKEN_COOKIE;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateAccessToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpirationMs))
                .signWith(getSigningKey())
                .compact();
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpirationMs))
                .signWith(getSigningKey())
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            log.error(ex.getMessage(), ex);
            return false;
        }
    }

    private String extractTokenFromCookie(HttpServletRequest request, String cookieName) {
        if (request.getCookies() == null) return null;

        Optional<Cookie> cookie =
                java.util.Arrays.stream(request.getCookies())
                        .filter(c -> cookieName.equals(c.getName()))
                        .findFirst();

        return cookie.map(Cookie::getValue).orElse(null);
    }

    private void setTokenAsCookie(HttpServletResponse response, String name, String token, int maxAgeInSeconds) {
        Cookie cookie = new Cookie(name, token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(maxAgeInSeconds);
        response.addCookie(cookie);
    }

    public void setAccessTokenAsCookie(HttpServletResponse response, String token) {
        setTokenAsCookie(response, ACCESS_TOKEN_COOKIE, token, (int) (accessTokenExpirationMs / 1000));
    }

    public void setRefreshTokenAsCookie(HttpServletResponse response, String token) {
        setTokenAsCookie(response, REFRESH_TOKEN_COOKIE, token, (int) (refreshTokenExpirationMs / 1000));
    }

    private void clearCookie(HttpServletResponse response, String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    public String extractAccessTokenFromCookie(HttpServletRequest request) {
        return extractTokenFromCookie(request, ACCESS_TOKEN_COOKIE);
    }

    public String extractRefreshTokenFromCookie(HttpServletRequest request) {
        return extractTokenFromCookie(request, REFRESH_TOKEN_COOKIE);
    }
}