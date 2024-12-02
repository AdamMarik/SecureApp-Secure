package com.bmiapp;

import javax.servlet.http.HttpSession;
import java.util.UUID;

public class CSRFUtil {
    public static String generateToken(HttpSession session) {
        String csrfToken = UUID.randomUUID().toString();
        session.setAttribute("csrfToken", csrfToken);
        return csrfToken;
    }

    public static boolean validateToken(HttpSession session, String csrfToken) {
        String sessionToken = (String) session.getAttribute("csrfToken");
        return sessionToken != null && sessionToken.equals(csrfToken);
    }
}
