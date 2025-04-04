package com.pragmatic.selenium.testng;

import org.testng.annotations.Test;

public class TestNGExamplePriority {
    @Test //priority = 0 when not set
    public void testMethodOne(){
        System.out.println("TestNGExamplesOne, testMethodOne");
    }
    @Test(priority = 1)
    public void testMethodTwo(){
        System.out.println("TestNGExamplesOne, testMethodTwo");
    }
    @Test//priority = 0 when not set
    public void testMethodThree(){
        System.out.println("TestNGExamplesOne, testMethodThree");
    }
    @Test(priority = 2)
    public void testMethodFour(){
        System.out.println("TestNGExamplesOne, testMethodFour");
    }
}
