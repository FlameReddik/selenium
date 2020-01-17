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


class EditSummary {
    private static ChromeDriver webDriver;
    private static WebDriverWait wait;
    private static int amount;

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
        By applicationsList = By.xpath("//*[contains(text(),'Applications')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(applicationsList));
        assertTrue(webDriver.findElement(applicationsList).isDisplayed());
    }

    @Test
    void callAll() {
        EditSummary.openApplication();
        EditSummary.changeRequestedAmount(10000);
    }

    static void openApplication() {
        By applicationElement = By.xpath("(//div[@class='applications-list-row ng-scope'])[1]");
        wait.until(ExpectedConditions.presenceOfElementLocated(applicationElement));
        webDriver.findElement(applicationElement).click();
        By summaryHeader = By.xpath("//span[contains(.,'Application summary')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(summaryHeader));
        assertTrue(webDriver.findElement(summaryHeader).isDisplayed());
    }

    static void changeRequestedAmount(int amount) {
        EditSummary.amount = amount;
        By startLoanEdit = By.cssSelector("div.application-details-loan .form-control-start-edit");
        wait.until(ExpectedConditions.presenceOfElementLocated(startLoanEdit));
        webDriver.findElement(startLoanEdit).click();
        webDriver.findElement(By.xpath("//i[@class='fa fa-lock']")).click();
        By requestedAmountActive = By.xpath("//input[@ng-model='applicationModified.loanAmount']");
        webDriver.findElement(requestedAmountActive).sendKeys(String.valueOf(10000));
        webDriver.findElement(By.cssSelector("div.application-details-loan .form-control-confirm-edit")).click();
/*        By requestedAmountInactive = By.xpath("");
        assertEquals(webDriver.findElement(requestedAmountInactive).getText(), String.valueOf(amount));*/


    }

    @AfterAll
    static void tearDown() {
        webDriver.quit();
    }

}
