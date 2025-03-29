package com.pragmatic.selenium.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ButtonTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.get("https://pragmatictesters.github.io/selenium-synchronization/buttons.html");
    }
    @AfterMethod
    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }
    @Test
    public void testButtonWithImplicitWait() throws InterruptedException{
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.id("easy00")).click();
        driver.findElement(By.id("easy01")).click();
        driver.findElement(By.id("easy02")).click();
        driver.findElement(By.id("easy03")).click();
        String message = driver.findElement(By.id("easybuttonmessage")).getText();
        Assert.assertEquals(message,"All Buttons Clicked");
        Thread.sleep(2000);
    }

    @Test
    public void testButtonWithExplicitWaitsVisibilityOfElementLocated() throws InterruptedException{
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("easy00"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("easy01"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("easy02"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("easy03"))).click();
        wait.until(ExpectedConditions.textToBe(By.id("easybuttonmessage"),"All Buttons Clicked"));
        Thread.sleep(2000);
    }

    @Test
    public void testButtonWithFluentWaits() {
        FluentWait<WebDriver> wait =  new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .withMessage("Elements is not visible within the timeout period")
                .ignoring(ElementNotInteractableException.class)
                .pollingEvery(Duration.ofMillis(1000));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("easy00"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("easy01"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("easy02"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("easy03"))).click();
        wait.until(ExpectedConditions.textToBe(By.id("easybuttonmessage"),"All Buttons Clicked"));
    }
}
