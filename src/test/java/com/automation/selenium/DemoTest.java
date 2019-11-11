package com.automation.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DemoTest {
    private WebDriver webDriver;

    @BeforeEach
    public void startBrowser() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        webDriver = new ChromeDriver();
    }

    @Test
    public void demo() {
        webDriver.get("https://www.facebook.com/login/");
        WebElement email = webDriver.findElement(By.name("email"));
        WebElement password = webDriver.findElement(By.name("pass"));
        email.sendKeys("test@google.com");
        password.sendKeys("password");
        webDriver.findElement(By.name("login")).click();
        assertTrue(webDriver.findElement(By.xpath("//*[@id=\"error_box\"]")).isDisplayed());
    }

    @AfterEach
    public void tearDown() {
        webDriver.quit();
    }
}
