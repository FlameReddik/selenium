package com.automation.selenium;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;


class CreateCS {
    private static ChromeDriver webDriver;
    private static WebDriverWait wait;

    @BeforeAll
    static void startBrowser() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        webDriver = new ChromeDriver();
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        loginToApp();
    }

    private static void loginToApp() {
        webDriver.get("https://admin.sf-rc.hysdev.com/#/login");
        By emailElement = By.xpath("//input[@type='email']");
        wait.until(ExpectedConditions.presenceOfElementLocated(emailElement));
        webDriver.findElement(emailElement).sendKeys("anna.stoyanova@hys-enterprise.com");
        webDriver.findElement(By.xpath("//input[@type='password']")).sendKeys("Reddik5");
        webDriver.findElement(By.id("login-button")).click();
        By applicationsElement = By.xpath("//*[contains(text(),'Applications')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(applicationsElement));
        assertTrue(webDriver.findElement(applicationsElement).isDisplayed());
    }

    @Test
    void createApplication() {
        By newLoan = By.xpath("//a[contains(@title,'New loan')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(newLoan));
        webDriver.findElement(newLoan).click();
        By summaryElement = By.xpath("//div[@title='Summary']");
        wait.until(ExpectedConditions.presenceOfElementLocated(summaryElement));
        assertTrue(webDriver.findElement(summaryElement).isDisplayed());
    }

    @Test
    void openCS() {
        By CSTab = By.xpath("//div[@title='Collection Schedule']");
        wait.until(ExpectedConditions.presenceOfElementLocated(CSTab));
        webDriver.findElement(CSTab).click();
        By linearCSButton = By.xpath("//button[contains(.,'Preview linear collection schedule')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(linearCSButton));
        assertTrue(webDriver.findElement(linearCSButton).isDisplayed());
    }

    @Test
    void previewLinearCS() {
        By linearCSButton = By.xpath("//button[contains(.,'Preview linear collection schedule')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(linearCSButton));
        webDriver.findElement(linearCSButton).click();
        By saveButton = By.xpath("//button[contains(.,'Save this preview data')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(saveButton));
        assertTrue(webDriver.findElement(saveButton).isDisplayed());
        // webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterAll
    static void tearDown() {
        webDriver.quit();
    }

}

