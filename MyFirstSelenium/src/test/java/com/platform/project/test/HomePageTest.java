package com.platform.project.test;

import com.platform.project.commons.Commons;
import com.platform.project.commons.ReadPropertyFile;
import com.platform.project.commons.WebDriverManager;
import com.platform.project.pageObjects.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest {
    WebDriver driver;
    HomePage homeP;
    WebDriverManager webDriverManager;

    @BeforeMethod
    public void setUp(){
        //Open Browser <=> initializing the webdriver
        webDriverManager = new WebDriverManager();
        //driver = webDriverManager.getDriver( ReadPropertyFile.getConfigPropertyVal("broswer"));
        driver = webDriverManager.getDriver( Commons.createEnvVariable("browser", ReadPropertyFile.getConfigPropertyVal("browser")));
        homeP = new HomePage(driver);
        //Open HomePage
        //homeP.openHomePage();
    }

    @Test
    public void openHomePage(){
        //Open homePage
        homeP.openHomePage();
        //Check HomePage Loaded
        Commons.check(driver,homeP.getTitle().equals("Welcome to iBusiness"),"Home Page did not match ");
        //Assert.assertEquals(homeP.getTitle(), "Welcome to iBusiness");
    }

//    @Test
//    public void openHomePage2(){
//        //Open homePage
//        homeP.openHomePage();
//        //Check HomePage Loaded
//        Commons.check(driver,homeP.getTitle().equals("Welcome to iBusiness2"),"Home Page did not match");
//        //Assert.assertEquals(homeP.getTitle(), "Welcome to iBusiness2");
//    }


    @AfterMethod
    public void cleanUp(){
        driver.quit();
    }

}
