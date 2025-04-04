package com.pragmatic.selenium.homework;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.datafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutProcessTestFirefox {

    public WebDriver driver;
    public String urlSauceDemo = "https://www.saucedemo.com/";

    @BeforeMethod
    public void setUp(){

        WebDriverManager.firefoxdriver().setup();//Manager
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(urlSauceDemo);//Open the Sauce Demo website

    }

    @AfterMethod
    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }

    /*
        Start of Utility Methods
     */

    private void userLogin(String username, String password) {
        clearAndTypeFName(By.id("user-name"), username);

        clearAndTypeFName(By.id("password"), password);

        WebElement clkLoginButton = driver.findElement(By.id("login-button"));
        clkLoginButton.click();//Click Login Button

    }

    private void generateUserDetails() {
        Faker faker = new Faker();
        String fakeFirstName = faker.name().firstName();
        String fakeLastName = faker.name().lastName();
        String zipCode = faker.number().digits(5);

        clearAndTypeFName(By.cssSelector("#first-name"), fakeFirstName);
        clearAndTypeLName(By.cssSelector("#last-name"), fakeLastName);
        clearAndTypePostalCode(By.cssSelector("#postal-code"), zipCode);
    }

    private void clearAndTypePostalCode(By by, String zipCode) {
        WebElement pCode = driver.findElement(by);
        pCode.clear();
        pCode.sendKeys(zipCode);
    }

    private void clearAndTypeLName(By by, String fakeLastName) {
        WebElement lName = driver.findElement(by);
        lName.clear();
        lName.sendKeys(fakeLastName);
    }

    private void clearAndTypeFName(By by, String fakeFirstName) {
        WebElement fName = driver.findElement(by);
        fName.clear();
        fName.sendKeys(fakeFirstName);
    }

    private void addItemsToCart() {
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
        driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-fleece-jacket")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();

    }

    private void checkBilledItems() {
        WebElement priceOfItemOne = driver.findElement(By.xpath("//a[@id ='item_4_title_link' ]//following-sibling::div[2]//child::div"));
        //String ItemOne = priceOfItemOne.getText().substring(1);
        String ItemOne = priceOfItemOne.getText();
        //System.out.println(ItemOne);
        Assert.assertEquals(ItemOne, "$29.99", "Item Price is Wrong! ");//Validate First Item Price

        WebElement priceOfItemTwo = driver.findElement(By.xpath("//a[@id ='item_0_title_link' ]//following-sibling::div[2]//child::div"));
        Assert.assertEquals(priceOfItemTwo.getText(),"$9.99","Item Price is Wrong! ");//Validate Second Item Price

        WebElement priceOfItemThree = driver.findElement(By.xpath("//a[@id ='item_5_title_link' ]//following-sibling::div[2]//child::div"));
        Assert.assertEquals(priceOfItemThree.getText(), "$49.99", "Item Price is Wrong! ");//Validate Thread Item Price

        WebElement itemTotal = driver.findElement(By.xpath("//div[@class='summary_subtotal_label']"));
        Assert.assertEquals(itemTotal.getText(),"Item total: $89.97","Item Total is Wrong! ");//Validate Item Total

        WebElement total = driver.findElement(By.xpath("//div[@class='summary_total_label']"));
        Assert.assertEquals(total.getText(),"Total: $97.17","Total Price is Wrong for the Order! ");//Validate Full Total of the Order

        driver.findElement(By.xpath("//button[@id='finish']")).click();

    }

    /*
        End of Utility Methods
     */

    @Test
    public void testValidCredentials(){

        userLogin("standard_user","secret_sauce");//Login to SauceDemo Website

        WebElement txtPageTitle = driver.findElement(By.cssSelector(".title"));
        Assert.assertEquals(txtPageTitle.getText(), "Products","Login failed - Page title mismatch.");//Products Page Validation

    }
    @Test
    public void verifyAddedProducts(){

        userLogin("standard_user","secret_sauce");//Login to SauceDemo Website
        addItemsToCart();//Add Products to the Cart

        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();//click Cart icon and navigate to Cart

        WebElement txtCartPageTitle = driver.findElement(By.cssSelector(".title"));
        Assert.assertEquals(txtCartPageTitle.getText(),"Your Cart", "Navigation Failed - Go to Your Cart Page ");//Cart Page Validation

        //Locate Added Products
        WebElement locateProductOne = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));
        WebElement locateProductTwo = driver.findElement(By.xpath("//div[text()='Sauce Labs Fleece Jacket']"));
        WebElement locateProductThree = driver.findElement(By.xpath("//div[text()='Sauce Labs Bike Light']"));

        //Verify added items
        Assert.assertEquals(locateProductOne.getText(), "Sauce Labs Backpack", "Wrong Product added to the Cart, Remove or Replace");
        Assert.assertEquals(locateProductTwo.getText(), "Sauce Labs Fleece Jacket", "Wrong Product added to the Cart, Remove or Replace");
        Assert.assertEquals(locateProductThree.getText(), "Sauce Labs Bike Light","Wrong Product added to the Cart, Remove or Replace" );

        driver.findElement(By.xpath("//button[@id='checkout']")).click();//Go to Checkout: Your Information Page

    }
    @Test
    public  void  verifyUserDetailsPage(){
        userLogin("standard_user","secret_sauce");//Login User
        addItemsToCart();//Add Products to the Cart
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();//click Cart icon and navigate to cart
        driver.findElement(By.xpath("//button[@id='checkout']")).click();//Go to checkout

        WebElement txtCheckoutInfoPageTitle = driver.findElement(By.cssSelector(".title"));
        Assert.assertEquals(txtCheckoutInfoPageTitle.getText(), "Checkout: Your Information","Navigation Failed - Go to Checkout: Your Information Page ");

        generateUserDetails();//Generate Random User details using DataFaker

        driver.findElement(By.xpath("//input[@id='continue']")).click();//Navigate to Checkout Overview Page

    }

    @Test
    public void verifyCheckoutOverviewPage(){

        userLogin("standard_user","secret_sauce");//Login User
        addItemsToCart();//Add Products to the Cart
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();//click Cart icon and navigate to cart
        driver.findElement(By.xpath("//button[@id='checkout']")).click();//Go to checkout
        generateUserDetails();//Generate Random User details using DataFaker
        driver.findElement(By.xpath("//input[@id='continue']")).click();//Navigate to Checkout Overview Page

        WebElement txtCheckoutOverviewTitle = driver.findElement(By.xpath("//span[@class='title']"));
        Assert.assertEquals(txtCheckoutOverviewTitle.getText(), "Checkout: Overview","Navigation Failed - Go to Checkout: Overview Page ");//Page Validation

        checkBilledItems();

    }


    @Test
    public void verifyOrderConfirmationMessage(){
        userLogin("standard_user","secret_sauce");//Login User
        addItemsToCart();//Add Products to the Cart
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();//click Cart icon and navigate to cart
        driver.findElement(By.xpath("//button[@id='checkout']")).click();//Go to checkout
        generateUserDetails();//Generate Random User details using DataFaker
        driver.findElement(By.cssSelector("#continue")).click();//Navigate to Checkout Overview Page
        driver.findElement(By.xpath("//button[@id='finish']")).click();

        //Verify Order Confirmation Message
        WebElement txtCheckoutCompletePage = driver.findElement(By.cssSelector(".title"));
        Assert.assertEquals(txtCheckoutCompletePage.getText(), "Checkout: Complete!","Oder Confirmation Failed - Incomplete Order! ");

        WebElement txtOrderMessage = driver.findElement(By.cssSelector(".complete-text"));
        Assert.assertEquals(txtOrderMessage.getText(),"Your order has been dispatched, and will arrive just as fast as the pony can get there!");

    }


}
