package com.pragmatic.selenium.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

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

    @BeforeMethod
    public void refreshBrowser(){

        driver.navigate().refresh();    //Refresh Page before next Test

    }

    @Test
    public void testLoginInvalidUsernameValidPassword(){

        driver.findElement(By.id("user-name")).sendKeys("standard_users");  //Type Invalid Username
        driver.findElement(By.id("password")).sendKeys("secret_sauce");     //Type Valid Password
        driver.findElement(By.id("login-button")).click();//Click Login Button

        //Validation
        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='error']")).getText(),"Epic sadface: Username and password do not match any user in this service");

    }

    @Test
    public void testLoginValidUsernameInvalidPassword(){

        driver.findElement(By.id("user-name")).sendKeys("standard_user");       //Type Valid Username
        driver.findElement(By.id("password")).sendKeys("secret1");              //Type Invalid Password
        driver.findElement(By.id("login-button")).click();                                  //Button Click

        //Validation
        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='error']")).getText(),"Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void invalidUserNamePassword(){

        driver.findElement(By.id("user-name")).sendKeys("user23");            //Type Invalid Username
        driver.findElement(By.id("password")).sendKeys("secret1");            //Type Invalid Password
        driver.findElement(By.id("login-button")).click();                                //Button Click

        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='error']")).getText(),"Epic sadface: Username and password do not match any user in this service");

    }

    @Test
    public void blankUserNameCorrectPassword(){

        driver.findElement(By.id("user-name")).sendKeys("");                    //Blank Username
        driver.findElement(By.id("password")).sendKeys("secret_sauce");         //Type Valid Password
        driver.findElement(By.id("login-button")).click();                                  //Button Click

        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='error']")).getText(),"Epic sadface: Username is required");

    }

    @Test
    public void correctUserNameBlankPassword(){

        driver.findElement(By.id("user-name")).sendKeys("standard_user");         //Type Username
        driver.findElement(By.id("password")).sendKeys("");                       //Blank Password
        driver.findElement(By.id("login-button")).click();                                    //Button Click

        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='error']")).getText(),"Epic sadface: Password is required");

    }

    @Test
    public void blankUserNamePassword(){

        driver.findElement(By.id("user-name")).sendKeys("");              //Blank Username
        driver.findElement(By.id("password")).sendKeys("");               //Blank Password
        driver.findElement(By.id("login-button")).click();                            //Button Click

        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='error']")).getText(),"Epic sadface: Username is required");

    }

    @Test
    public void lockedOutUserAttempt(){

        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");         //Locked out Username
        driver.findElement(By.id("password")).sendKeys("secret_sauce");             //Password
        driver.findElement(By.id("login-button")).click();                                      //Button Click

        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='error']")).getText(),"Epic sadface: Sorry, this user has been locked out.");

    }

    @Test
    public void specialCharactersInUserNamePassword(){

        driver.findElement(By.id("user-name")).sendKeys("userM@#$1");               //Special characters in Username
        driver.findElement(By.id("password")).sendKeys("secret&@#14_0");            //Special characters in Password
        driver.findElement(By.id("login-button")).click();                                      //Button Click

        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='error']")).getText(),"Epic sadface: Username and password do not match any user in this service");

    }

    @Test
    public void veryLongUserNamePassword(){

        driver.findElement(By.id("user-name")).sendKeys("16UQLq1HZ3CNwhvgrarV6pMoA2CDjb4tyFmsls");              //Long Username
        driver.findElement(By.id("password")).sendKeys("secret_sauce");                                         //Correct Password
        driver.findElement(By.id("login-button")).click();                                                                  //Button Click

        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='error']")).getText(),"Epic sadface: Username and password do not match any user in this service");

    }

    /*
    Data Provider Example
     */

    @DataProvider(name = "user-credentials")
    public Object[][] userCredentials(){
        return new Object[][]{
                {"","", "Epic sadface: Username is required" },
                {"","password","Epic sadface: Username is required"},
                {"standard_user","","Epic sadface: Password is required"}

        };
    }

    @Test(dataProvider = "user-credentials")
    public void testInvalidScenariosWithDDT(String username, String password, String expectedError){

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='error']")).getText(),expectedError);
    }


    @AfterTest
    public void quitBrowser(){

        driver.quit();

    }


}
