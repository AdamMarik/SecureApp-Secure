package com.bmiapp.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTest {
    public static void main(String[] args) {
        // Set ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "C:/Users/adama/ZAP/webdriver/windows/64/chromedriver.exe");

        // Initialize ChromeDriver
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);

        try {
            // Open the BMI Calculator page
            driver.get("http://localhost:8080/BMIcalcApp/login.jsp");

            // Fill out the form
            driver.findElement(By.id("username")).sendKeys("adam");
            driver.findElement(By.id("password")).sendKeys("adam1234");

            // Submit the form
            driver.findElement(By.xpath("//button[text()='Login']")).click();

            // Wait for the results
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.urlToBe("http://localhost:8080/BMIcalcApp/index.jsp"));

            System.out.println("Logged in");
        }
        finally {
            // Close the browser
            driver.quit();
        }
    }
}

