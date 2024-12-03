package com.bmiapp;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@WebServlet("/csrfToken")
public class CSRFUtil {

    public static String generateToken(HttpServletRequest request) {
        String csrfToken = UUID.randomUUID().toString();
        HttpSession session = request.getSession();
        session.setAttribute("csrfToken", csrfToken);
        return csrfToken;
    }

    public static boolean validateToken(HttpSession session, String token) {
        String sessionToken = (String) session.getAttribute("csrfToken");
        return sessionToken != null && sessionToken.equals(token);
    }
}
