package com.pragmatic.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HelloSeleniumTest {


    @Test
    public void testHelloSelenium(){

    //Launching browser instance
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

    //Navigate to the Login page
        driver.get("https://www.saucedemo.com/");


    //Type username
        WebElement txtUserName = driver.findElement(By.id("user-name"));
        txtUserName.clear();
        txtUserName.sendKeys("standard_user");

    //Type pw
        WebElement txtPassword = driver.findElement(By.id("password"));
        txtPassword.clear();
        txtPassword.sendKeys("secret_sauce");

    //Click Login button

        driver.findElement(By.id("login-button")).click();
    //Verification
        Assert.assertEquals(driver.findElement(By.cssSelector(".title")).getText(), "Products","Login failed - Page title mismatch.");

    //Close
        driver.quit();

    }
}
