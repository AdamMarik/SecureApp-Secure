package com.bmiapp;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addResult")
public class addResults extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String weightParam = request.getParameter("weight");
            String heightParam = request.getParameter("height");
            String ageParam = request.getParameter("age");
            String gender = request.getParameter("gender");
            String bodyfatParam = request.getParameter("bodyFatPercentage");
            String bmiParam = request.getParameter("bmi");


            if (weightParam == null || weightParam.isEmpty() ||
                    heightParam == null || heightParam.isEmpty() ||
                    ageParam == null || ageParam.isEmpty()) {
                throw new IllegalArgumentException("Parameter values should not be empty");
            }

            double weight = Double.parseDouble(weightParam);
            double height = Double.parseDouble(heightParam);
            int age = Integer.parseInt(ageParam);
            double bodyfat = Double.parseDouble(bodyfatParam);
            double bmi = Double.parseDouble(bmiParam);

            HttpSession session = request.getSession();
            int userId = SessionUtils.getLoggedInUserId(session);

            Input input = new Input(null, userId, weight, height, age, gender, null, bodyfat, bmi);

            // Debugging output
            System.out.println("Weight: " + weightParam);
            System.out.println("Height: " + heightParam);
            System.out.println("Age: " + ageParam);
            System.out.println("Gender: " + gender);
            System.out.println("bodyfat: " + bodyfat);
            System.out.println("bmi: " + bmi);

            InputDAO inputDAO = new InputDAO();
            inputDAO.addInput(input);
            response.sendRedirect("result.jsp");

        } catch (IllegalArgumentException | SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input data.");
        }
    }
}