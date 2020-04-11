package com.platform.project.test;

import com.platform.project.commons.Commons;
import com.platform.project.commons.ReadPropertyFile;
import com.platform.project.commons.WebDriverManager;
import com.platform.project.pageObjects.LinkPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LinkPageTest {
    WebDriver driver;
    LinkPage homeP;
    WebDriverManager webDriverManager;

    @BeforeMethod
    public void setUp(){
        //Open Browser <=> initializing the webdriver
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver( Commons.createEnvVariable("browser", ReadPropertyFile.getConfigPropertyVal("browser")));
        homeP = new LinkPage(driver);
    }

    @Test
    public void login(){
        homeP.openLoginPage();
        homeP.getLinks();
    }


    @AfterMethod
    public void cleanUp(){
        driver.quit();
    }
}