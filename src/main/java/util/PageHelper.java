package util;

import factory.BrowserFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.logging.Logger;

public class PageHelper {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static Wait<WebDriver> WAIT = null;

    private PageHelper() {
    }

    public static WebElement waitVisibilityOfElement(WebElement element, String description) {
        try {
            getWait().until(ExpectedConditions.visibilityOf(element));
            LOGGER.finest("Successfully found the element: " + description);
            return element;
        } catch (Exception e) {
            LOGGER.severe("Error while trying to find: " + element + " description: " + description + " message:" + e.getMessage());
            throw new InvalidArgumentException("Error while trying to find: " + element + " description: " + description + " message: " + e.getMessage());
        }
    }

    private static Wait<WebDriver> getWait() {
        if (WAIT == null) {
            WAIT = new FluentWait<>(BrowserFactory.getInstance().getCurrentBrowser())
                    .withTimeout(Duration.ofSeconds(10))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(ElementClickInterceptedException.class);
        }
        return WAIT;
    }
}