package com.platform.project.pageObjects;

import com.platform.project.commons.Commons;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    @FindBy(xpath = "//*[@id=\"bodyContent\"]/h1")
    WebElement pageTitle;

    private WebDriver driver;
    private Logger logger = Logger.getLogger(HomePage.class);

    public HomePage(WebDriver driver){
        this.driver = driver;
        //init page facotory
        PageFactory.initElements(driver,this);
    }

    public void openHomePage(){
        logger.info("Opening Home Page");
        driver.get("http://107.170.213.234/catalog/");
    }

    public String getTitle(){
        WebElement pageTitle = driver.findElement(By.xpath("//*[@id=\"bodyContent\"]/h1"));
        return Commons.getElementText(driver,pageTitle,3);
    }

}
