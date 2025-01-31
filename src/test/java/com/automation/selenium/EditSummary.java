package com.automation.selenium;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        openApplication();
        changeRequestedAmount(10000);
        changeUpdatedAmount(20000);
        changeCalculatedAmount(30000);
        changeAgreedAmount(40000);
        changePaidAmount(50000);
        changeCommissionPremium(100, 150);
        changeReassignmentAndReleaseDate();
        changeStartDateTenorEndDate();
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");

    static By startLoanEdit() {
        return By.cssSelector("div.application-details-loan .form-control-start-edit");
    }

    static By confirmLoanEdit() {
        return By.cssSelector("div.application-details-loan .form-control-confirm-edit");
    }

    static By spinner() {
        return By.xpath("//div[@class='loading-spinner animated fadeIn']");
    }

    static By pickCurrentDate() {
        return By.xpath("//button[@class='btn btn-default btn-sm active']");
    }

    static void openApplication() {
        By applicationElement = By.xpath("(//div[@class='applications-list-row ng-scope'])[4]");
        wait.until(ExpectedConditions.presenceOfElementLocated(applicationElement));
        webDriver.findElement(applicationElement).click();
        By summaryHeader = By.xpath("//span[contains(.,'Application summary')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(summaryHeader));
        assertTrue(webDriver.findElement(summaryHeader).isDisplayed());
    }

    static void changeRequestedAmount(int amount) {
        wait.until(ExpectedConditions.presenceOfElementLocated(startLoanEdit()));
        webDriver.findElement(startLoanEdit()).click();
        webDriver.findElement(By.xpath("//i[@class='fa fa-lock']")).click();
        WebElement requestedAmountInput = webDriver.findElement(By.xpath("//input[@ng-model='applicationModified.loanAmount']"));
        requestedAmountInput.clear();
        requestedAmountInput.sendKeys(String.valueOf(amount));
        webDriver.findElement(confirmLoanEdit()).click();
        assertEquals(String.valueOf(amount), requestedAmountInput.getAttribute("value"));
    }

    static void changeUpdatedAmount(int amount) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner()));
        webDriver.findElement(startLoanEdit()).click();
        WebElement updatedAmountInput = webDriver.findElement(By.xpath("//input[@ng-model='applicationModified.updatedAmount']"));
        updatedAmountInput.clear();
        updatedAmountInput.sendKeys(String.valueOf(amount));
        webDriver.findElement(confirmLoanEdit()).click();
        assertEquals(String.valueOf(amount), updatedAmountInput.getAttribute("value"));
    }

    static void changeCalculatedAmount(int amount) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner()));
        webDriver.findElement(startLoanEdit()).click();
        WebElement calculatedAmountInput = webDriver.findElement(By.xpath("//input[@ng-model='applicationModified.autoAmount']"));
        calculatedAmountInput.clear();
        calculatedAmountInput.sendKeys(String.valueOf(amount));
        webDriver.findElement(confirmLoanEdit()).click();
        assertEquals(String.valueOf(amount), calculatedAmountInput.getAttribute("value"));
    }

    static void changeAgreedAmount(int amount) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner()));
        webDriver.findElement(startLoanEdit()).click();
        WebElement agreedAmountInput = webDriver.findElement(By.xpath("//input[@ng-model='applicationModified.agreedAmount']"));
        agreedAmountInput.clear();
        agreedAmountInput.sendKeys(String.valueOf(amount));
        webDriver.findElement(confirmLoanEdit()).click();
        assertEquals(String.valueOf(amount), agreedAmountInput.getAttribute("value"));
    }

    static void changePaidAmount(int amount) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner()));
        webDriver.findElement(startLoanEdit()).click();
        WebElement paidAmountInput = webDriver.findElement(By.xpath("//input[@ng-model='applicationModified.contractAmount']"));
        paidAmountInput.clear();
        paidAmountInput.sendKeys(String.valueOf(amount));
        webDriver.findElement(confirmLoanEdit()).click();
        assertEquals(String.valueOf(amount), paidAmountInput.getAttribute("value"));
    }

    static void changeCommissionPremium(int commission, int premium) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner()));
        webDriver.findElement(startLoanEdit()).click();
        WebElement commissionField = webDriver.findElement(By.xpath("//input[@id='euroCommission']"));
        commissionField.clear();
        commissionField.sendKeys(String.valueOf(commission));
        WebElement premiumField = webDriver.findElement(By.xpath("//input[@id='euroPremium']"));
        premiumField.clear();
        premiumField.sendKeys(String.valueOf(premium));
        webDriver.findElement(confirmLoanEdit()).click();
        assertEquals(String.valueOf(commission), commissionField.getAttribute("value"));
        assertEquals(String.valueOf(premium), premiumField.getAttribute("value"));
    }

    static void changeReassignmentAndReleaseDate() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner()));
        webDriver.findElement(startLoanEdit()).click();
        WebElement reassignmentAndReleaseDateDateField = webDriver.findElement(By.xpath("//input[contains(@ng-model,'applicationReassignmentAndReleaseDate')]"));
        reassignmentAndReleaseDateDateField.clear();
        By reassignmentAndReleaseDateDateButton = By.xpath("//button[contains(@ng-click,'reassignmentAndReleaseDateIsOpened = !reassignmentAndReleaseDateIsOpened')]");
        webDriver.findElement(reassignmentAndReleaseDateDateButton).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(pickCurrentDate()));
        webDriver.findElement(pickCurrentDate()).click();
        webDriver.findElement(confirmLoanEdit()).click();
        assertEquals(LocalDate.now(), LocalDate.parse(reassignmentAndReleaseDateDateField.getAttribute("value"), formatter));
    }

    static void changeStartDateTenorEndDate() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner()));
        webDriver.findElement(startLoanEdit()).click();
        WebElement startDateField = webDriver.findElement(By.xpath("//input[contains(@ng-model,'applicationStartDate')]"));
        startDateField.clear();
        By startDateButton = By.xpath("//button[contains(@ng-click,'applicationStartDateDob.opened = !applicationStartDateDob.opened')]");
        webDriver.findElement(startDateButton).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(pickCurrentDate()));
        webDriver.findElement(pickCurrentDate()).click();
        WebElement tenor = webDriver.findElement(By.xpath("//input[contains(@ng-model,'applicationModified.loanDuration')]"));
        tenor.clear();
        tenor.sendKeys("3");
        webDriver.findElement(confirmLoanEdit()).click();
        assertEquals(LocalDate.now(), LocalDate.parse(startDateField.getAttribute("value"), formatter));
        assertEquals("3", tenor.getAttribute("value"));
    }


    @AfterAll
    static void tearDown() {
        webDriver.quit();
    }

}
