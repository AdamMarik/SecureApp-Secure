package com.bmiapp.test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BMICalculatorTest {
    public static void main(String[] args) {
        // Set ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "C:/Users/adama/ZAP/webdriver/windows/64/chromedriver.exe");

        // Initialize ChromeDriver
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);

        try {
            // Open the BMI Calculator page
            driver.get("http://localhost:8080/BMIcalcApp/index.jsp");

            // Fill out the form
            driver.findElement(By.id("weight")).sendKeys("70");
            driver.findElement(By.id("height")).sendKeys("175");
            driver.findElement(By.id("age")).sendKeys("25");
            driver.findElement(By.id("gender")).sendKeys("male");

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Submit the form
            driver.findElement(By.xpath("//button[text()='Calculate']")).click();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            WebElement bmiResult = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("bmi")));
            WebElement bodyFatResult = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("bodyFatPercentage")));

            System.out.println("BMI: " + bmiResult.getText());
            System.out.println("Body Fat Percentage: " + bodyFatResult.getText());
        }
        finally {
            // Close the browser
            driver.quit();
        }
    }
}

