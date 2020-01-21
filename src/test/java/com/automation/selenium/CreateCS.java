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
        By applicationsList = By.xpath("//*[contains(text(),'Applications')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(applicationsList));
        assertTrue(webDriver.findElement(applicationsList).isDisplayed());
    }


    @Test
    void call() {
//        openApplication();
        createApplication();
        openCSTab();
        linearCSValidationCommissionPremium();
        openSummary();
        fixCommissionPremiumValidation();
        openCSTab();
        linearCSValidationDate();
        openSummary();
        fixDateValidation();
        openCSTab();
        saveLinearCS();
    }


  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");

    static By summaryHeaderInit() {
        return By.xpath("//span[contains(.,'Application summary')]");
    }

    static By linearCSButtonInit() {
        return By.xpath("//button[contains(text(),'Preview linear collection schedule')]");
    }


    static void createApplication() {
        By newLoan = By.xpath("//a[@title = 'New loan']");
        wait.until(ExpectedConditions.presenceOfElementLocated(newLoan));
        webDriver.findElement(newLoan).click();
        By summaryHeader = summaryHeaderInit();
        wait.until(ExpectedConditions.presenceOfElementLocated(summaryHeader));
        assertTrue(webDriver.findElement(summaryHeader).isDisplayed());

    }

    static void openApplication() {
        By applicationElement = By.xpath("(//div[@class='applications-list-row ng-scope'])[1]");
        wait.until(ExpectedConditions.presenceOfElementLocated(applicationElement));
        webDriver.findElement(applicationElement).click();
        By summaryHeader = summaryHeaderInit();
        wait.until(ExpectedConditions.presenceOfElementLocated(summaryHeader));
        assertTrue(webDriver.findElement(summaryHeader).isDisplayed());
    }

    static void openCSTab() {
        By CSTab = By.xpath("//div[@title='Collection Schedule']");
        wait.until(ExpectedConditions.presenceOfElementLocated(CSTab));
        webDriver.findElement(CSTab).click();
        By loanStartDate = By.xpath("//td[contains(.,'Loan Start Date')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(loanStartDate));
        assertTrue(webDriver.findElement(loanStartDate).isDisplayed());
    }

/*    static void previewLinearCS() {
        By linearCSButton = By.xpath("//button[contains(text(),'Preview linear collection schedule')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(linearCSButton));
        webDriver.findElement(linearCSButton).click();
        By loanStartDate = By.xpath("//td[contains(.,'Loan Start Date')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(loanStartDate));
        assertTrue(webDriver.findElement(loanStartDate).isDisplayed());
    }*/

    static void linearCSValidationCommissionPremium() {
        By linearCSButton = linearCSButtonInit();
        wait.until(ExpectedConditions.presenceOfElementLocated(linearCSButton));
        webDriver.findElement(linearCSButton).click();
        By commissionPremiumValidation = By.xpath("//p[contains(.,'Commission and Premium fields should be set')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(commissionPremiumValidation));
        assertTrue(webDriver.findElement(commissionPremiumValidation).isDisplayed());
    }

    static void openSummary() {
        By clickID = By.xpath("(//div[@ui-sref='applications.details.overview'])[1]");
        wait.until(ExpectedConditions.presenceOfElementLocated(clickID));
        webDriver.findElement(clickID).click();
        By summaryHeader = By.xpath("//span[contains(.,'Application summary')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(summaryHeader));
        assertTrue(webDriver.findElement(summaryHeader).isDisplayed());
    }

    static void fixCommissionPremiumValidation(){
        By startLoanEdit = By.cssSelector("div.application-details-loan .form-control-start-edit");
        wait.until(ExpectedConditions.presenceOfElementLocated(startLoanEdit));
        webDriver.findElement(startLoanEdit).click();
        By commissionFieldActive = By.xpath("//input[@id='euroCommission']");
        webDriver.findElement(commissionFieldActive).sendKeys("100");
        By premiumFieldActive = By.xpath("//input[@id='euroPremium']");
        webDriver.findElement(premiumFieldActive).sendKeys("150");
        webDriver.findElement(By.cssSelector("div.application-details-loan .form-control-confirm-edit")).click();
        By commissionFieldInactive = By.xpath("(//div[@class='fake-input-field width1of3 ng-binding'])[2]");
        By premiumFieldInactive = By.xpath("(//div[@class='fake-input-field width1of3 ng-binding'])[4]");
        assertEquals(webDriver.findElement(commissionFieldInactive).getText(), "100.00");
        assertEquals(webDriver.findElement(premiumFieldInactive).getText(), "150.00");

    }

    static void linearCSValidationDate() {
        By linearCSButton = linearCSButtonInit();
        wait.until(ExpectedConditions.presenceOfElementLocated(linearCSButton));
        webDriver.findElement(linearCSButton).click();
        By dateValidation = By.xpath("//p[contains(.,'Incorrect start and(or) end dates')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(dateValidation));
        assertTrue(webDriver.findElement(dateValidation).isDisplayed());
    }

    static void fixDateValidation() {
        By startLoanEdit = By.cssSelector("div.application-details-loan .form-control-start-edit");
        wait.until(ExpectedConditions.presenceOfElementLocated(startLoanEdit));
        webDriver.findElement(startLoanEdit).click();
        By startDateButton = By.xpath("//button[contains(@ng-click,'applicationStartDateDob.opened = !applicationStartDateDob.opened')]");
        webDriver.findElement(startDateButton).click();
        By currentDate = By.xpath("//button[@class = 'btn btn-default btn-sm active']");
        wait.until(ExpectedConditions.presenceOfElementLocated(currentDate));
        webDriver.findElement(currentDate).click();
        By tenor = By.xpath("//input[contains(@ng-model,'applicationModified.loanDuration')]");
        webDriver.findElement(tenor).sendKeys("3");
        webDriver.findElement(By.cssSelector("div.application-details-loan .form-control-confirm-edit")).click();
        WebElement startDateField = webDriver.findElement(By.xpath("//input[contains(@ng-model,'applicationStartDate')]"));
        assertEquals(LocalDate.now(), LocalDate.parse(startDateField.getAttribute("value"), formatter));
        assertEquals("3", webDriver.findElement(tenor).getAttribute("value"));
    }

    static void saveLinearCS() {
        By linearCSButton = linearCSButtonInit();
        wait.until(ExpectedConditions.presenceOfElementLocated(linearCSButton));
        webDriver.findElement(linearCSButton).click();
        By saveLinearCSButton = By.xpath("//button[contains(text(),'Save this preview data')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(saveLinearCSButton));
        webDriver.findElement(saveLinearCSButton).click();
        By clearLinearCSButton = By.xpath("//button[contains(text(),'Clear collection schedule & forecast')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(clearLinearCSButton));
        assertTrue(webDriver.findElement(clearLinearCSButton).isDisplayed());
    }


   // By validationMessages = By.cssSelector("div.application-details-tabbed>div>div>p.text-danger");

    @AfterAll
    static void tearDown() {
        webDriver.quit();
    }

}

