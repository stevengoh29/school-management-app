package io.stevengoh.portfolio.school_management_app.core.auth;

import io.stevengoh.portfolio.school_management_app.common.constant.CookiesConstant;
import io.stevengoh.portfolio.school_management_app.common.dtos.BaseResponse;
import io.stevengoh.portfolio.school_management_app.core.auth.dtos.request.ReqLoginDto;
import io.stevengoh.portfolio.school_management_app.core.auth.dtos.request.ReqRefreshTokenDto;
import io.stevengoh.portfolio.school_management_app.core.auth.dtos.response.ResLoginDto;
import io.stevengoh.portfolio.school_management_app.core.auth.services.JwtTokenService;
import io.stevengoh.portfolio.school_management_app.modules.users.dtos.response.DetailedResUserDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtTokenService jwtTokenService;

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<String>> login(
            @RequestBody ReqLoginDto request,
            HttpServletResponse response
    ) {
        ResLoginDto loginResponse = authService.login(request);

        jwtTokenService.setAccessTokenAsCookie(response, loginResponse.getAccessToken());
        jwtTokenService.setRefreshTokenAsCookie(response, loginResponse.getRefreshToken());

        return ResponseEntity.ok(new BaseResponse<>("Login successful"));
//        return ResponseEntity.ok(new BaseResponse<>(loginResponse));
    }

    @PostMapping("/refresh")
    public ResponseEntity<BaseResponse<ResLoginDto>> refresh(
            @RequestBody ReqRefreshTokenDto request
    ) {
        ResLoginDto resLoginDto = authService.refreshToken(request);
        return ResponseEntity.ok(new BaseResponse<>(resLoginDto));
    }

    @GetMapping("/profile")
    public ResponseEntity<?> profile(
            @CookieValue(value = CookiesConstant.ACCESS_TOKEN_COOKIE, required = false) String accessToken,
            @CookieValue(value = CookiesConstant.REFRESH_TOKEN_COOKIE, required = false) String refreshToken,
            @AuthenticationPrincipal UserDetails userDetails,
            HttpServletResponse response) {
        DetailedResUserDto user = authService.getUserByUsername(userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}