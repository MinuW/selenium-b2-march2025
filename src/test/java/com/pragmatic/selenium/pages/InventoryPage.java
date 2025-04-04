package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {

    private final WebDriver driver;
    private final By byTitle = By.cssSelector(".title");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }
    public String getTitle(){
        return driver.findElement(byTitle).getText();
    }
}
