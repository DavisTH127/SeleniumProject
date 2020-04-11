package com.platform.project.commons;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ReadPropertyFile {
    private static Logger logger = Logger.getLogger(ReadPropertyFile.class);
    private static String configFileLocation = "./src/test/resources/config.properties";

    private static String readFile(String file, String key){
        String value = null;
        try {
            Properties prop = new Properties();
            FileInputStream in = new FileInputStream(file);
            prop.load(in);

            //Get values from property file
            value = prop.getProperty(key);
            logger.info("Value in property file for" + key + " is " + value);
        } catch (IOException ioe){
            ioe.printStackTrace();
            logger.info("Couldn't locate the property file");
        }
        return value;
    }

    public static String getConfigPropertyVal(final String key){
        String configPropertyVal = readFile(configFileLocation, key);
        return configPropertyVal!=null ? configPropertyVal.trim() : configPropertyVal;
    }
}

