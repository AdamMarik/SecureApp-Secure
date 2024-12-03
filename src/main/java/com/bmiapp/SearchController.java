package com.bmiapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/search")
public class SearchController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("query");
        List<Input> results = new ArrayList<>();

        try {
            int userId = UserDAO.getUserIdByUsername(username); // Get user ID
            if (userId != -1) {
                results = new InputDAO().getUserInputs(userId); // Fetch inputs by user ID
            } else {
                request.setAttribute("error", "User not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while fetching data");
        }

        request.setAttribute("results", results);
        request.getRequestDispatcher("/searchResults.jsp").forward(request, response);
    }
}