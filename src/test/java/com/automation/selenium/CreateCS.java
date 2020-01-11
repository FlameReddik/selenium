package com.automation.selenium;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

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

    /*   @Test
      void createApplication() {
          By newLoan = By.xpath("//a[contains(@title,'New loan')]");
          wait.until(ExpectedConditions.presenceOfElementLocated(newLoan));
          webDriver.findElement(newLoan).click();
          By summaryHeader = By.xpath("//span[contains(.,'Application summary')]");
          wait.until(ExpectedConditions.presenceOfElementLocated(summaryHeader));
          assertTrue(webDriver.findElement(summaryHeader).isDisplayed());
      }*/

    @Test
    void openApplication() {
        By applicationElement = By.xpath("(//div[@class='applications-list-row ng-scope'])[1]");
        wait.until(ExpectedConditions.presenceOfElementLocated(applicationElement));
        webDriver.findElement(applicationElement).click();
        By summaryHeader = By.xpath("//span[contains(.,'Application summary')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(summaryHeader));
        assertTrue(webDriver.findElement(summaryHeader).isDisplayed());
    }

    @Test
    void openCSTab() {
        By CSTab = By.xpath("//div[@title='Collection Schedule']");
        wait.until(ExpectedConditions.presenceOfElementLocated(CSTab));
        webDriver.findElement(CSTab).click();
        By loanStartDate = By.xpath("//td[contains(.,'Loan Start Date')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(loanStartDate));
        assertTrue(webDriver.findElement(loanStartDate).isDisplayed());
    }

    @Test
    static void previewLinearCS() {
        By linearCSButton = By.xpath("//button[contains(text(),'Preview linear collection schedule')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(linearCSButton));
        webDriver.findElement(linearCSButton).click();
        By loanStartDate = By.xpath("//td[contains(.,'Loan Start Date')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(loanStartDate));
        assertTrue(webDriver.findElement(loanStartDate).isDisplayed());
    }
    @Test
      static void linearCSValidationCommissionPremium() {
        CreateCS.previewLinearCS();
        By commissionPremiumValidation = By.xpath("//p[contains(.,'Commission and Premium fields should be set')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(commissionPremiumValidation));
        assertTrue(webDriver.findElement(commissionPremiumValidation).isDisplayed());
    }

    @Test
    static void openSummary() {
        By clickID = By.xpath("(//div[@ui-sref='applications.details.overview'])[1]");
        wait.until(ExpectedConditions.presenceOfElementLocated(clickID));
        webDriver.findElement(clickID).click();
        By summaryHeader = By.xpath("//span[contains(.,'Application summary')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(summaryHeader));
        assertTrue(webDriver.findElement(summaryHeader).isDisplayed());
    }

        @Test
    void fixCommissionPremiumValidation(){
        CreateCS.linearCSValidationCommissionPremium();
        CreateCS.openSummary();
        By leftEdit = By.xpath("(//span[@class='form-control-start-edit ng-scope'])[1]");
        wait.until(ExpectedConditions.presenceOfElementLocated(leftEdit));
        webDriver.findElement(leftEdit).click();
        By commissionField = By.xpath("//input[@id='euroCommission']");
        webDriver.findElement(commissionField).sendKeys("100");
        By premiumField = By.xpath("//input[@id='euroPremium']");
        webDriver.findElement(premiumField).sendKeys("150");
        webDriver.findElement(leftEdit).click();
        webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
          //  assertSame(100, webDriver.findElement(commissionField).getAttribute("value"));
         //   assertSame(150, webDriver.findElement(premiumField).getAttribute("value"));
    }

  /*  @Test
    void linearCSValidationDate() {
        CreateCS.previewLinearCS();
    }
*/
   // By validationMessages = By.cssSelector("div.application-details-tabbed>div>div>p.text-danger.text()");

  /*  @Test
    void previewSuccessLinearCS() {
        By linearCSButton = By.xpath("//button[contains(.,'Preview linear collection schedule')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(linearCSButton));
        webDriver.findElement(linearCSButton).click();
        By saveButton = By.xpath("//button[contains(.,'Save this preview data')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(saveButton));
        assertTrue(webDriver.findElement(saveButton).isDisplayed());
    }*/

    @AfterAll
    static void tearDown() {
        webDriver.quit();
    }

}

