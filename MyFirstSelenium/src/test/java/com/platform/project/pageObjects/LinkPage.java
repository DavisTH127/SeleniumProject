package com.platform.project.pageObjects;

import com.platform.project.commons.Commons;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkPage {
    @FindBy(xpath = "//*[@id=\"bodyContent\"]/div[2]/div/form/table")

    private WebDriver driver;
    private Logger logger = Logger.getLogger(LoginPage.class);

    public LinkPage(WebDriver driver){
        this.driver = driver;
        //init page factory
        PageFactory.initElements(driver,this);
    }

    public void openLoginPage(){
        logger.info("Opening Login Page");
        driver.get("http://107.170.213.234/catalog/login.php");
    }

    public void getLinks(){
    java.util.List<WebElement> links = driver.findElements(By.tagName("a"));
    System.out.println(links.size());
        for(WebElement link:links){
            System.out.println(link.getText() + " - " + link.getAttribute("href"));
        }
    }

}
