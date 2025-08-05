package io.stevengoh.portfolio.school_management_app.common.utils;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CookiesUtils {
    private final HttpServletResponse httpServletResponse;

    public void setCookies(String path , String accessToken, String refreshToken) {
        String refreshCookieHeader = "REFRESH_KEY=" + refreshToken
                + "; Path=" + path + "; Max-Age=3600; HttpOnly; SameSite=None; Secure; Domain=.bankmestika.co.id;";

        String accessCookieHeader = "ACCESS_KEY=" + accessToken
                + "; Path="+ path + "; Max-Age=900; HttpOnly; SameSite=None; Secure; Domain=.bankmestika.co.id;";

        httpServletResponse.addHeader("Set-Cookie", refreshCookieHeader);
        httpServletResponse.addHeader("Set-Cookie", accessCookieHeader);
    }

    public void deleteCookies( String path ) {
        String refreshCookieHeader = "REFRESH_KEY=" + null
                + "; Path=" + path + "; Max-Age=0; HttpOnly; SameSite=None; Secure;";

        String accessCookieHeader = "ACCESS_KEY=" + null
                + "; Path="+ path + "; Max-Age=0; HttpOnly; SameSite=None; Secure;";

        httpServletResponse.addHeader("Set-Cookie", refreshCookieHeader);
        httpServletResponse.addHeader("Set-Cookie", accessCookieHeader);
    }
}
