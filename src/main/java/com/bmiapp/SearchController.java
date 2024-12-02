package com.bmiapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.text.StringEscapeUtils;
import java.io.IOException;

@WebServlet("/search")
public class SearchController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String query = request.getParameter("query");
        String sanitizedQuery = StringEscapeUtils.escapeHtml4(query);
        response.setContentType("text/html");
        response.getWriter().println("<h1>Search Results for: " + sanitizedQuery + "</h1>");
    }
}

