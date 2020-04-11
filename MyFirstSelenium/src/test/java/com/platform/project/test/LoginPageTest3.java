package com.platform.project.test;

import com.platform.project.commons.Commons;
import com.platform.project.commons.ReadPropertyFile;
import com.platform.project.commons.WebDriverManager;
import com.platform.project.pageObjects.LoginPage3;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginPageTest3 {
    WebDriver driver;
    LoginPage3 homeP;
    WebDriverManager webDriverManager;

    @BeforeMethod
    public void setUp(){
        //Open Browser <=> initializing the webdriver
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver( Commons.createEnvVariable("browser", ReadPropertyFile.getConfigPropertyVal("browser")));
        homeP = new LoginPage3(driver);
    }

    @Test
    public void login(){
        homeP.openHomePage2();
        Commons.check(driver,homeP.getLogin2().equals(""),"Error Message did not match ");
    }



    @AfterMethod
    public void cleanUp(){
        driver.quit();
    }
}