package com.pragmatic.selenium.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionBot {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public ActionBot(WebDriver driver) {
        this.driver =driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    /**
     Wait and click on the element located by a given locator
     @param by locator of the element to be clicked
     */
    public void waitAndClick(By by){
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }
    /**
     Clear field located by a given locator
     @param by
     */
    public void clear(By by){
        driver.findElement(by).clear();
    }
    /**
     type located by a given locator
     @param by inputText
     */
    public void type(By by, String inputText){
        driver.findElement(by).sendKeys(inputText);
    }
    /**
     type located by a given locator
     @param by inputText
     */
    public void clearAndType(By by, String inputText){
        clear(by);
        type(by,inputText);
    }

}
