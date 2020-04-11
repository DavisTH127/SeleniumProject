package com.platform.project.pageObjects;

import com.platform.project.commons.Commons;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(xpath = "//*[@id=\"bodyContent\"]/div[2]/div/form/table")

    private WebDriver driver;
    private Logger logger = Logger.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver){
        this.driver = driver;
        //init page factory
        PageFactory.initElements(driver,this);
    }

    public void openLoginPage(){
        logger.info("Opening Login Page");
        driver.get("http://107.170.213.234/catalog/login.php");
    }

    public String getLogin(){
        driver.findElement(By.name("email_address")).sendKeys("ecalix@test.com");
        driver.findElement(By.name("password")).sendKeys("test123");
        driver.findElement(By.id("tdb5")).click();
        WebElement pageTitle = driver.findElement(By.xpath("//*[@id=\"bodyContent\"]/h1"));
        return Commons.getElementText(driver,pageTitle,3);
    }


}
