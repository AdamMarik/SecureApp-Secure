package com.bmiapp.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SignupTest {
    public static void main(String[] args) {
        // Set ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "C:/Users/adama/ZAP/webdriver/windows/64/chromedriver.exe");

        // Initialize ChromeDriver
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);

        try {
            // Open the signup page
            driver.get("http://localhost:8080/BMIcalcApp/signup");

            // Fill out the form
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement csrfElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("csrfToken")));
            csrfElement.getAttribute("value");


            driver.findElement(By.id("username")).sendKeys("test");
            driver.findElement(By.id("password")).sendKeys("testing1234");
            driver.findElement(By.id("confirmPassword")).sendKeys("testing1234");

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Submit the form
            driver.findElement(By.xpath("//button[text()='Sign Up']")).click();

            wait.until(ExpectedConditions.urlToBe("http://localhost:8080/BMIcalcApp/signup"));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Signup test passed!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
