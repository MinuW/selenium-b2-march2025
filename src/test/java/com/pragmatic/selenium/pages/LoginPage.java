package com.pragmatic.selenium.pages;

import com.pragmatic.selenium.util.ActionBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver driver;
    private ActionBot actionBot;
    //Define elements with By
    private final By byUsername = By.id("user-name");
    private final By byPassword = By.id("password");
    private final By byLoginButton = By.id("login-button");
    private final By byError = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;

    }
    public LoginPage typeUsername(String username) {
        type(byUsername, username);
        return this;
    }

    private void type(By byUsername, String username) {
        driver.findElement(this.byUsername).sendKeys(username);
        //actionBot.clearAndType(eleUsername,username);
    }

    public LoginPage typePassword(String password) {
        type(byPassword, password);
        return this;
    }

    private void type(String password) {
        driver.findElement(byPassword).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(byLoginButton).click();
    }

    public String getError() {
        return driver.findElement(byError).getText();
    }
}
