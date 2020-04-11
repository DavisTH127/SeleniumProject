package com.platform.project.test;

import com.platform.project.commons.Commons;
import com.platform.project.commons.ReadPropertyFile;
import com.platform.project.commons.WebDriverManager;
import com.platform.project.pageObjects.LoginPage2;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginPageTest2 {
    WebDriver driver;
    LoginPage2 homeP;
    WebDriverManager webDriverManager;

    @BeforeMethod
    public void setUp(){
        //Open Browser <=> initializing the webdriver
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver( Commons.createEnvVariable("browser", ReadPropertyFile.getConfigPropertyVal("browser")));
        homeP = new LoginPage2(driver);
    }

    @Test
    public void login(){
        homeP.openHomePage2();
        Commons.check(driver,homeP.getLogin2().equals("Welcome to iBusiness"),"Home Page did not match ");

    }

    @AfterMethod
    public void cleanUp(){
        driver.quit();
    }
}