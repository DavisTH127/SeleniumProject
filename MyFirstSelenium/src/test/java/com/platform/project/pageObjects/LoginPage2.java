package com.platform.project.pageObjects;

import com.platform.project.commons.Commons;
import com.platform.project.commons.ReadPropertyFile;
import com.platform.project.commons.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage2 {
    @FindBy(xpath = "//*[@id=\"bodyContent\"]/h1")
    WebDriverManager drivers;
    private WebDriver driver;
    private Logger logger = Logger.getLogger(LoginPage2.class);

    public LoginPage2(WebDriver driver){
        this.driver = driver;
        //init page facotory
        PageFactory.initElements(driver,this);
    }

    public void openHomePage2(){
        logger.info("Opening Home Page");
        driver.get(ReadPropertyFile.getConfigPropertyVal("homePageURL"));
    }

    public String getLogin2(){
        driver.findElement(By.xpath("//*[@id=\"bodyContent\"]/div/div[1]/a[1]")).click();
        driver.findElement(By.name("email_address")).sendKeys(ReadPropertyFile.getConfigPropertyVal("username"));
        driver.findElement(By.name("password")).sendKeys(ReadPropertyFile.getConfigPropertyVal("password"));
        driver.findElement(By.id("tdb5")).click();
        WebElement pageTitle = driver.findElement(By.xpath("//*[@id=\"bodyContent\"]/h1"));
        return Commons.getElementText(driver,pageTitle,3);
    }
}