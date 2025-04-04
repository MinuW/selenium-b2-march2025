package com.pragmatic.selenium.tests;

import com.pragmatic.selenium.pages.InventoryPage;
import com.pragmatic.selenium.pages.LoginFactoryPage;
import com.pragmatic.selenium.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SauceLoginTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
    }
    @AfterMethod
    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }
    @Test
    public void testLoginWithInvalidPassword(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeUsername("standard_user").typePassword("invalid").
                clickLogin();
        Assert.assertEquals(loginPage.getError(),"Epic sadface: Username and password do not match any user in this service",
                "Error for Invalid Password is incorrect");
    }
    @Test
    public void testLoginWithInvalidPasswordFactory(){
        LoginFactoryPage loginFactoryPage = new LoginFactoryPage(driver);
        loginFactoryPage.typeUsername("standard_user").typePassword("invalid").
                clickLogin();
        Assert.assertEquals(loginFactoryPage.getError(),"Epic sadface: Username and password do not match any user in this service",
                "Error for Invalid Password is incorrect");
    }

    @Test
    public void testLoginWithValidCredentials(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeUsername("standard_user").typePassword("secret_sauce").
                clickLogin();
        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertEquals(inventoryPage.getTitle(),"Products",
                "Inventory page title test failed");
    }

}
