package pages;

import factory.BrowserFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.PageHelper;

import java.util.logging.Logger;

public class LoginPage {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @FindBy(id = "email")
    public WebElement inputEmail;

    @FindBy(id = "passwd")
    public WebElement inputPassword;

    @FindBy(xpath = "//span[contains(.,'Sign in')]")
    public WebElement btnSignIn;

    public LoginPage() {
        PageFactory.initElements(BrowserFactory.getInstance().getCurrentBrowser(), this);
    }

    public Boolean signIn(String username, String password) {
        try {
            LOGGER.severe("Trying to sign");
            PageHelper.waitVisibilityOfElement(inputEmail, "inputEmail").sendKeys(username);
            PageHelper.waitVisibilityOfElement(inputPassword, "inputPassword").sendKeys(password);
            PageHelper.waitVisibilityOfElement(btnSignIn, "btnSignIn").click();
            return true;
        } catch (Exception ignore) {
            return false;
        }
    }
}
