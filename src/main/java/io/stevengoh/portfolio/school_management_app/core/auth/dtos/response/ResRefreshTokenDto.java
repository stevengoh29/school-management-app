package io.stevengoh.portfolio.school_management_app.core.auth.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResRefreshTokenDto {
    private String accessToken;
    private String refreshToken;
}
