package com.platform.project.commons;

import com.google.common.base.Function;
import junit.framework.Assert;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

public class Commons {
    private static Logger logger = Logger.getLogger(Commons.class);

    public static void screenShot(WebDriver driver, String javaClass, String methodName){
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss_").format(new Date());
        String fileName = timeStamp + javaClass + "_" + methodName + ".png";
        File ss = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(ss, new File("./screenshots/" + fileName + ".pgn"));
            logger.info("Screenshot " + fileName + " taken");
        }catch (IOException ioe){
            ioe.printStackTrace();
            logger.info("Unable to take screenshot " + fileName);

        }
    }

    public static void check(WebDriver driver, boolean condition, String failMessage){
        if(condition){
            logger.info("Check condition is True") ;
            Assert.assertTrue(true);
        }else{
            logger.info(failMessage);
            screenShot(driver, currentThread().getStackTrace()[2].getClassName(),
                    currentThread().getStackTrace()[2].getMethodName());

            Assert.assertTrue(false);
            //Assert.assertFalse(true);
            // Assert.fail();
        }
    }

    public static String createEnvVariable(String envVariableName, String defaultValue){
        String variableValue = System.getProperty(envVariableName);
        logger.info("Enviorment value for " + envVariableName + " is equal to " + variableValue);
        return variableValue!=null ? variableValue : defaultValue;
    };

    //Explicit Wait
    public static boolean waitForElement(WebDriver driver, WebElement el, int seconds){
        WebDriverWait wait = new WebDriverWait(driver,seconds);
        try{
            wait.until(ExpectedConditions.visibilityOf(el));
            logger.info(el.getText() + " is visible");
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            logger.info("Element is not Visable");
            return false;
        }
    }

    //Explicit Wait - Fluent wait
    public static boolean isElementVisible(WebDriver driver, WebElement el, int seconds){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(seconds, TimeUnit.SECONDS).pollingEvery(10,TimeUnit.MILLISECONDS);

        Function<WebDriver,Boolean> isElementFound = arg0 ->{
            try{
                driver.manage().timeouts().implicitlyWait(1,TimeUnit.MILLISECONDS);
                el.getSize();
                return true;
            }catch (NoSuchElementException nsee){
                return false;
            }
        };


        try{
            wait.until(isElementFound);
            logger.info(el.getText() + " is visible");
            return true;
        }catch(Exception ex) {
            ex.printStackTrace();
            logger.info("Element is not Visable");
            return false;
        }
    }

    public static String getElementText(WebDriver driver,WebElement el){
        return getElementText(driver,el,3);
    }

    public static String getElementText(WebDriver driver,WebElement el, int seconds){
        if(Commons.isElementVisible(driver, el, seconds)){
            String elementText = el.getText();
            System.out.println("Element Text is: " + elementText );
            return elementText;
        }else{
            return "";
        }
    }
}
