package com.bmiapp;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

@WebServlet("/deleteInput")
public class deleteInput extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        int userId = SessionUtils.getLoggedInUserId(session);
        String inputDateParam = request.getParameter("inputDate");

        try {
            // Log the raw input
            System.out.println("Attempting to delete entry for User ID: " + userId + " on Date: " + inputDateParam);

            // Parse the date and log it again
            String inputDate = String.valueOf(inputDateParam);
            System.out.println("Parsed Date for Deletion: " + inputDate);

            InputDAO inputDAO = new InputDAO();
            inputDAO.deleteInput(userId, inputDate);
            response.sendRedirect("search?query=" + session.getAttribute("username"));
        } catch (SQLException | IllegalArgumentException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting the input.");
        }
    }
}