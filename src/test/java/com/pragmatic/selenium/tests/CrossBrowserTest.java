package com.pragmatic.selenium.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CrossBrowserTest {

    public WebDriver driver;
    public String urlSauceDemo = "https://www.saucedemo.com/";

    @BeforeMethod
    public void setUp(){

    }
    @AfterMethod
    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }

    @Test
    public void testLoginWithChrome(){

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(urlSauceDemo);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");     //Type Valid Password
        driver.findElement(By.id("login-button")).click();//Click Login Button
        String actualTitle = driver.findElement(By.cssSelector(".title")).getText();
        Assert.assertEquals(actualTitle,"Products","Login failed - Page title mismatch.");

    }

    @Test
    public void testLoginWithChromeHeadless(){

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(urlSauceDemo);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");     //Type Valid Password
        driver.findElement(By.id("login-button")).click();//Click Login Button
        String actualTitle = driver.findElement(By.cssSelector(".title")).getText();
        Assert.assertEquals(actualTitle,"Products","Login failed - Page title mismatch.");

    }

    @Test
    public void testLoginWithFirefox(){

        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(urlSauceDemo);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");  //Type Invalid Username
        driver.findElement(By.id("password")).sendKeys("secret_sauce");     //Type Valid Password
        driver.findElement(By.id("login-button")).click();//Click Login Button
        String actualTitle = driver.findElement(By.cssSelector(".title")).getText();
        Assert.assertEquals(actualTitle,"Products","Login failed - Page title mismatch.");
    }

    @Test
    public void testLoginWithFirefoxHeadless(){

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");

        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        driver.get(urlSauceDemo);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");  //Type Invalid Username
        driver.findElement(By.id("password")).sendKeys("secret_sauce");     //Type Valid Password
        driver.findElement(By.id("login-button")).click();//Click Login Button
        String actualTitle = driver.findElement(By.cssSelector(".title")).getText();
        Assert.assertEquals(actualTitle,"Products","Login failed - Page title mismatch.");
    }

    @Test
    public void testLoginWithEdge(){
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get(urlSauceDemo);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");  //Type Invalid Username
        driver.findElement(By.id("password")).sendKeys("secret_sauce");     //Type Valid Password
        driver.findElement(By.id("login-button")).click();//Click Login Button
        String actualTitle = driver.findElement(By.cssSelector(".title")).getText();
        Assert.assertEquals(actualTitle,"Products","Login failed - Page title mismatch.");
    }
}
