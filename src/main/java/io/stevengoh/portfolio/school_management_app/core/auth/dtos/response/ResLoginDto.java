package io.stevengoh.portfolio.school_management_app.core.auth.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResLoginDto {
    private String accessToken;
    private String refreshToken;
}
