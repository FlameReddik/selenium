package com.automation.selenium;

import org.junit.jupiter.api.Test;

    class TestSummary extends TestClass {



/*    static ChromeDriver webDriver;
    static WebDriverWait wait;*/
/*
    @BeforeAll
    public static void startBrowser() {
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
    }*/

    @Test
    private void editApplicationSummary() {


/*
        EditSummary editSummary = new EditSummary(webDriver);

        EditSummary.openApplication();
        EditSummary.changeRequestedAmount(10000);
        EditSummary.changeUpdatedAmount(20000);
        EditSummary.changeCalculatedAmount(30000);
        EditSummary.changeAgreedAmount(40000);*/



    }


/*
    @AfterAll
    static void tearDown() {
        webDriver.quit();
    }*/
}
