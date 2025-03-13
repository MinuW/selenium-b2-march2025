package com.pragmatic.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginNegativeTests {

    public String urlSauceDemo = "https://www.saucedemo.com/";

    public WebDriver driver;

    @BeforeTest
    public void launchBrowser(){

        //Navigate to the Login Page
        driver = new ChromeDriver();
        driver.get(urlSauceDemo);
        driver.manage().window().maximize();

    }

    @Test
    public void testLoginInvalidUsernameValidPassword(){

        driver.findElement(By.id("user-name")).sendKeys("standard_users");  //Type Invalid Username
        driver.findElement(By.id("password")).sendKeys("secret_sauce");     //Type Valid Password
        driver.findElement(By.id("login-button")).click();//Click Login Button

        //Validation
        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='error']")).getText(),"Epic sadface: Username and password do not match any user in this service");

        driver.navigate().refresh();    //Refresh Page before next Test



    }

    @Test
    public void testLoginValidUsernameInvalidPassword(){

        driver.findElement(By.id("user-name")).sendKeys("standard_user");       //Type Valid Username
        driver.findElement(By.id("password")).sendKeys("secret1");              //Type Invalid Password
        driver.findElement(By.id("login-button")).click();                                  //Button Click

        //Validation
        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='error']")).getText(),"Epic sadface: Username and password do not match any user in this service");
        driver.navigate().refresh();        //Refresh Page before next Test

    }

    @Test
    public void invalidUserNamePassword(){

        driver.findElement(By.id("user-name")).sendKeys("user23");            //Type Invalid Username
        driver.findElement(By.id("password")).sendKeys("secret1");            //Type Invalid Password
        driver.findElement(By.id("login-button")).click();                                //Button Click

        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='error']")).getText(),"Epic sadface: Username and password do not match any user in this service");
        driver.navigate().refresh();       //Refresh Page before next Test

    }

    @Test
    public void blankUserNameCorrectPassword(){

        driver.findElement(By.id("user-name")).sendKeys("");                    //Blank Username
        driver.findElement(By.id("password")).sendKeys("secret_sauce");         //Type Valid Password
        driver.findElement(By.id("login-button")).click();                                  //Button Click

        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='error']")).getText(),"Epic sadface: Username is required");
        driver.navigate().refresh();       //Refresh Page before next Test

    }

    @Test
    public void correctUserNameBlankPassword(){

        driver.findElement(By.id("user-name")).sendKeys("standard_user");         //Type Username
        driver.findElement(By.id("password")).sendKeys("");                       //Blank Password
        driver.findElement(By.id("login-button")).click();                                    //Button Click

        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='error']")).getText(),"Epic sadface: Password is required");
        driver.navigate().refresh();          //Refresh Page before next Test
    }

    @Test
    public void blankUserNamePassword(){

        driver.findElement(By.id("user-name")).sendKeys("");              //Blank Username
        driver.findElement(By.id("password")).sendKeys("");               //Blank Password
        driver.findElement(By.id("login-button")).click();                            //Button Click

        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='error']")).getText(),"Epic sadface: Username is required");
        driver.navigate().refresh();        //Refresh Page before next Test

    }

    @Test
    public void lockedOutUserAttempt(){

        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");         //Locked out Username
        driver.findElement(By.id("password")).sendKeys("secret_sauce");             //Password
        driver.findElement(By.id("login-button")).click();                                      //Button Click

        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='error']")).getText(),"Epic sadface: Sorry, this user has been locked out.");
        driver.navigate().refresh();        //Refresh Page before next Test
    }

    @Test
    public void specialCharactersInUserNamePassword(){

        driver.findElement(By.id("user-name")).sendKeys("userM@#$1");               //Special characters in Username
        driver.findElement(By.id("password")).sendKeys("secret&@#14_0");            //Special characters in Password
        driver.findElement(By.id("login-button")).click();                                      //Button Click

        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='error']")).getText(),"Epic sadface: Username and password do not match any user in this service");
        driver.navigate().refresh();        //Refresh Page before next Test
    }

    @Test
    public void veryLongUserNamePassword(){

        driver.findElement(By.id("user-name")).sendKeys("16UQLq1HZ3CNwhvgrarV6pMoA2CDjb4tyFmsls");              //Long Username
        driver.findElement(By.id("password")).sendKeys("secret_sauce");                                         //Correct Password
        driver.findElement(By.id("login-button")).click();                                                                  //Button Click

        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='error']")).getText(),"Epic sadface: Username and password do not match any user in this service");
        driver.navigate().refresh();        //Refresh Page before next Test

    }


    @AfterTest
    public void quitBrowser(){

        driver.quit();

    }


}
