package com.bmiapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/signup")
public class SignupController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String csrfToken = request.getParameter("csrfToken");
        HttpSession session = request.getSession();

        if (!CSRFUtil.validateToken(session, csrfToken)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid CSRF Token");
            return;
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match!");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        try {
            UserDAO.registerUser(username, password);
            request.setAttribute("success", "Account created successfully. Please login.");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Username already exists. Please try a different one.");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
    }
}
