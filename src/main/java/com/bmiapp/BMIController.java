package com.bmiapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/calculate")
public class BMIController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Debugging: Check if the servlet is reached
        System.out.println("BMIController: Servlet reached.");

        try {
            // Retrieve inputs
            double weight = Double.parseDouble(request.getParameter("weight"));
            double height = Double.parseDouble(request.getParameter("height"));
            int age = Integer.parseInt(request.getParameter("age"));
            String gender = request.getParameter("gender");

            // Debugging: Check input values
            System.out.println("Inputs: Weight=" + weight + ", Height=" + height + ", Age=" + age + ", Gender=" + gender);

            // Perform calculations
            double bmi = CalculatorUtil.calculateBMI(weight, height);
            double bodyFatPercentage = CalculatorUtil.calculateBodyFatPercentage(bmi, age, gender);

            // Debugging: Check calculated values
            System.out.println("Calculated BMI: " + bmi);
            System.out.println("Calculated Body Fat Percentage: " + bodyFatPercentage);

            // Set attributes for the JSP
            request.setAttribute("bmi", bmi);
            request.setAttribute("bodyFatPercentage", bodyFatPercentage);

            // Debugging: Confirm attributes are set
            System.out.println("Attributes set: BMI=" + request.getAttribute("bmi") +
                    ", BodyFatPercentage=" + request.getAttribute("bodyFatPercentage"));

            // Forward to the result page
            request.getRequestDispatcher("result.jsp").forward(request, response);
        } catch (Exception e) {
            // Debugging: Log errors
            System.out.println("Error in BMIController: " + e.getMessage());
            e.printStackTrace();

            // Forward to an error page or display error message
            response.getWriter().println("Error processing the request. Please check the inputs.");
        }
    }
}
