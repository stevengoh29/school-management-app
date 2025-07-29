package io.stevengoh.portfolio.school_management_app.core.auth;

import io.stevengoh.portfolio.school_management_app.core.auth.dtos.request.ReqRefreshTokenDto;
import io.stevengoh.portfolio.school_management_app.core.auth.dtos.request.ReqLoginDto;
import io.stevengoh.portfolio.school_management_app.core.auth.dtos.response.ResLoginDto;

public interface AuthService {
    ResLoginDto login(ReqLoginDto reqLoginDto);
    ResLoginDto refreshToken(ReqRefreshTokenDto reqRefreshTokenDto);
}