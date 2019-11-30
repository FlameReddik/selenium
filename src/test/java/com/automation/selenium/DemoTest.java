package com.automation.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DemoTest {
    private WebDriver webDriver;
    WebDriverWait wait;

    @BeforeEach
    public void startBrowser() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        webDriver = new ChromeDriver();
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        loginToApp();
    }

    private void loginToApp() {
        webDriver.get("https://admin.sf-rc.hysdev.com/#/login");
        By emailElement = By.xpath("//input[@type='email']");
        wait.until(ExpectedConditions.presenceOfElementLocated(emailElement));
        webDriver.findElement(emailElement).sendKeys("anna.stoyanova@hys-enterprise.com");
        webDriver.findElement(By.xpath("//input[@type='password']")).sendKeys("Reddik5");
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.findElement(By.id("login-button")).click();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        assertTrue(webDriver.findElement(By.xpath("//*[contains(text(),'Applications')]")).isDisplayed());
    }

    @Test
    public void demo() {

    }

    @AfterEach
    public void tearDown() {
        webDriver.quit();
    }
}
