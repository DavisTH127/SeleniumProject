package com.platform.project.commons;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverManager {

    private WebDriver driver;
    private String osName = System.getProperty("os.name");
    private Logger logger = Logger.getLogger(WebDriverManager.class);

    public WebDriver createDriver(String browser){
            if(osName.toLowerCase().contains("windows")){
                if(browser.equalsIgnoreCase("chrome")){
                    logger.info("Chrome browser deteted");
                    System.setProperty("webdriver.chrome.driver","src/test/resources/driver/chromedriver");
                    driver = new ChromeDriver();
                }else if(browser.equalsIgnoreCase("firefox")){
                    logger.info("firefox browser deteted");
                    System.setProperty("webdriver.gecko.driver","src/test/resources/driver/gecko");
                    driver = new FirefoxDriver();
                }else if(browser.equalsIgnoreCase("ie") || browser.equalsIgnoreCase("internet explorer")){
                    logger.info("Internet Explorer browser deteted");
                    System.setProperty("webdriver.ie.driver","src/test/resources/driver/ie");
                    driver = new InternetExplorerDriver();
                }else{
                    logger.info("Default browser deteted");
                    System.setProperty("webdriver.chrome.driver","src/test/resources/driver/chromedriver");
                    driver = new ChromeDriver();
                }
            } else if(osName.toLowerCase().contains("mac")){
                if(browser.equalsIgnoreCase("chrome")){
//                    System.setProperty("webdriver.chrome.driver","src/test/resources/driver/chromedriver");
                    driver = new ChromeDriver();
                }else if(browser.equalsIgnoreCase("firefox")){
//                    System.setProperty("webdriver.gecko.driver","src/test/resources/driver/gecko");
                    driver = new FirefoxDriver();
                }else if(browser.equalsIgnoreCase("safari") || browser.equalsIgnoreCase("safari")){
//                    System.setProperty("webdriver.safari.driver","src/test/resources/driver/safari");
                    driver = new SafariDriver();
                }else{
                    logger.info("Default browser detected");
//                    System.setProperty("webdriver.chrome.driver","src/test/resources/driver/chromedriver");
                    driver = new ChromeDriver();
                }
            }else if(osName.toLowerCase().contains("linux")){
                //fill appropriatelly
            }

            //Implicit wait
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

            return driver;
    }

    public WebDriver getDriver(){
        return getDriver(Commons.createEnvVariable("browser",
                ReadPropertyFile.getConfigPropertyVal("browser")));
    }

    public WebDriver getDriver(String browser) {
        if(driver == null){
            try{
                driver = createDriver(browser);
                System.out.println("Driver initialization Successful");
            }catch(Exception e){
                System.out.println("Driver initialization Failed");
                e.printStackTrace();
            }
        }else{
            System.out.println("Driver Already Exist");
        }
        return driver;
    }
}
