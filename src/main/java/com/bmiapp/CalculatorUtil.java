package com.bmiapp;

public class CalculatorUtil {

    public static double calculateBMI(double weight, double height) {
        height = height / 100.0; // Convert height from cm to meters
        return weight / (height * height); // BMI formula
    }

    public static double calculateBodyFatPercentage(double bmi, int age, String gender) {
        if ("male".equalsIgnoreCase(gender)) {
            return 1.20 * bmi + 0.23 * age - 16.2; // For males
        } else if ("female".equalsIgnoreCase(gender)) {
            return 1.20 * bmi + 0.23 * age - 5.4; // For females
        }
        throw new IllegalArgumentException("Invalid gender: " + gender);
    }
}
