package com.pragmatic.selenium.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SelectItemsDropDownExampleTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.get("https://pragmatictesters.github.io/selenium-webdriver-examples/dropdowns.html");
    }
    @AfterMethod
    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }

    @Test
    public void testSingleSelect(){
        //Locate the Element
        WebElement eleSelect =driver.findElement(By.id("singleSelect"));
        //Cast the Element to Select
        Select selFruits = new Select(eleSelect);
        Assert.assertEquals(selFruits.getFirstSelectedOption().getText(),"Select a fruit");

        List<WebElement> options = selFruits.getOptions();
        for (WebElement option : options){
            System.out.println(option.getText());
        }

        //Select Items by Visible Text
        selFruits.selectByVisibleText("Apple");
        Assert.assertEquals(selFruits.getFirstSelectedOption().getText(),"Apple");
        //Select Item by Value
        selFruits.selectByValue("banana");
        Assert.assertEquals(selFruits.getFirstSelectedOption().getText(),"Banana");
        //Select Item by Index
        selFruits.selectByIndex(4);
        Assert.assertEquals(selFruits.getFirstSelectedOption().getText(),"Date");
    }
    @Test
    public void testMultiSelect(){
        WebElement eleMulti = driver.findElement(By.id("multiSelect"));

        //Compete multi select as homework-watch the recording
        Select selMulti = new Select(eleMulti);

        selMulti.selectByIndex(2);
        Assert.assertEquals(selMulti.getFirstSelectedOption().getText(),"Blue");
        selMulti.deselectByIndex(2);

        selMulti.selectByVisibleText("Red");
        Assert.assertEquals(selMulti.getFirstSelectedOption().getText(),"Red");
        selMulti.deselectByVisibleText("Red");

        selMulti.selectByValue("yellow");
        Assert.assertEquals(selMulti.getFirstSelectedOption().getText(),"Yellow");

        //De-select
//        selMulti.deselectByIndex(2);
//        selMulti.deselectByVisibleText("Red");
        selMulti.deselectByValue("yellow");

//        selMulti.deselectAll();
    }
}
