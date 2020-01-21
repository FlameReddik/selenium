package com.automation.selenium;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        EditSummary.changeUpdatedAmount(20000);
    }
    static By startLoanEditInit() {
        return By.cssSelector("div.application-details-loan .form-control-start-edit");
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
        By startLoanEdit = startLoanEditInit();
        wait.until(ExpectedConditions.presenceOfElementLocated(startLoanEdit));
        webDriver.findElement(startLoanEdit).click();
        webDriver.findElement(By.xpath("//i[@class='fa fa-lock']")).click();
        WebElement requestedAmountInput = webDriver.findElement(By.xpath("//input[@ng-model='applicationModified.loanAmount']"));
        requestedAmountInput.clear();
        Actions actions = new Actions(webDriver);
        actions
                .click(requestedAmountInput)
                .sendKeys(String.valueOf(amount))
                .perform();
        webDriver.findElement(By.cssSelector("div.application-details-loan .form-control-confirm-edit")).click();
        assertEquals(String.valueOf(amount), requestedAmountInput.getAttribute("value"));
    }


    static void changeUpdatedAmount(int amount) {
        By startLoanEdit = startLoanEditInit();
        wait.until(ExpectedConditions.presenceOfElementLocated(startLoanEdit));
        webDriver.findElement(startLoanEdit).click();
        WebElement updatedAmountInput = webDriver.findElement(By.xpath("//input[@ng-model='applicationModified.updatedAmount']"));
        Actions actions = new Actions(webDriver);
        actions
                .click(updatedAmountInput)
                .sendKeys(String.valueOf(amount))
                .perform();
        webDriver.findElement(By.cssSelector("div.application-details-loan .form-control-confirm-edit")).click();
        assertEquals(String.valueOf(amount), updatedAmountInput.getAttribute("value"));
    }



    @AfterAll
    static void tearDown() {
        webDriver.quit();
    }

}
