package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import util.PropertyReader;

import java.util.logging.Logger;

public class BrowserFactory {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static BrowserFactory instance = null;
    private WebDriver currentBrowser = null;

    private BrowserFactory() {
    }

    public static BrowserFactory getInstance() {
        if (instance == null) {
            instance = new BrowserFactory();
        }
        return instance;
    }

    public void setBrowser(BrowserEnum browser) {
        LOGGER.info("Trying to create the following browser: " + browser);
        switch (browser) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                currentBrowser = new ChromeDriver();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                currentBrowser = new FirefoxDriver();
                break;
            case OPERA:
                WebDriverManager.operadriver().setup();
                currentBrowser = new OperaDriver();
                break;
        }
        currentBrowser.get(PropertyReader.getProperty("prod.website"));
        LOGGER.config("Successfully created new browser: " + browser);
    }

    public WebDriver getCurrentBrowser() {
        if (currentBrowser == null) {
            LOGGER.config("Error while trying to get the current browser, verify if you set browser after using it");
            throw new WebDriverException("Error while trying to get the current browser, verify if you set browser after using it");
        }
        return currentBrowser;
    }

    public void closeCurrentBrowser() {
        try {
            currentBrowser.quit();
        } catch (Exception ignore) {
        }
    }

}
