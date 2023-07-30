package com.crm.qa.base;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {
   public static WebDriver driver;
    public static Properties prop;

    public static EventFiringWebDriver edriver;

    public static WebEventListener eventListener;

    public TestBase() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream("/Users/i331396/Documents/Automation/SeleniumTestAutomation/src/main/java/com/crm/qa/config/config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialization(){
        String browserName= prop.getProperty("browser");
        if(browserName.equals("chrome")){
          System.setProperty("webdriver.chrome.driver","/Users/i331396/Downloads/drivers/chromedriver");
//            driver = new ChromeDriver();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver= new ChromeDriver(options);
        }

        edriver=new EventFiringWebDriver(driver);
        //Now create object of EventListenerHandler to register it with EventFiringWebDriver
        eventListener = new WebEventListener();
        edriver.register(eventListener);
        driver=edriver;

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT,TimeUnit.SECONDS);
        driver.get(prop.getProperty("url"));
    }
}
