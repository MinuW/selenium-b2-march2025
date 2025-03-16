package com.pragmatic.selenium.tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JavaScriptPopupTest {

    public WebDriver driver;
    public String urlSauceDemo = "https://pragmatictesters.github.io/selenium-webdriver-examples/javascript-popups.html";

    @BeforeMethod
    public void setUp(){

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(urlSauceDemo);

    }
    @AfterMethod
    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }

    @Test
    public void testJavascriptAlert(){

        driver.findElement(By.xpath("//button[text()='Show Alert']")).click();

        Alert alert = driver.switchTo().alert();
        String alertMessage = alert.getText();
        Assert.assertEquals(alertMessage,"This is a simple alert!","Alert message is incorrect");
        alert.accept();
        String message = driver.findElement(By.cssSelector("#outputMessage")).getText();
        Assert.assertEquals(message,"Alert displayed.");
    }



    @Test
    public void testJavascriptConfirmationOK(){

    }

    @Test
    public void testJavascriptConfirmationCancel(){

    }

    @Test
    public void testJavascriptPrompt(){

    }
}

