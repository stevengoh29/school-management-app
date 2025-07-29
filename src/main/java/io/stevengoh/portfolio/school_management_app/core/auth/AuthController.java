package io.stevengoh.portfolio.school_management_app.core.auth;

import io.stevengoh.portfolio.school_management_app.common.dtos.BaseResponse;
import io.stevengoh.portfolio.school_management_app.core.auth.dtos.request.ReqLoginDto;
import io.stevengoh.portfolio.school_management_app.core.auth.dtos.request.ReqRefreshTokenDto;
import io.stevengoh.portfolio.school_management_app.core.auth.dtos.response.ResLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<ResLoginDto>> login(@RequestBody ReqLoginDto request) {
        ResLoginDto response = authService.login(request);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @PostMapping("/refresh")
    public ResponseEntity<BaseResponse<ResLoginDto>> refresh(
            @RequestBody ReqRefreshTokenDto request
    ) {
        ResLoginDto resLoginDto = authService.refreshToken(request);
        return ResponseEntity.ok(new BaseResponse<>(resLoginDto));
    }
}