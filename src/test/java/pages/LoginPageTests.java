package pages;

import factory.BrowserEnum;
import factory.BrowserFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.LoggerConfig;
import util.PageHelper;
import util.PropertyReader;

public class LoginPageTests {

    private BasePage basePage = null;
    private LoginPage loginPage = null;

    @BeforeEach
    public void initialize() {
        LoggerConfig.setupLogger();
        BrowserFactory.getInstance().setBrowser(BrowserEnum.CHROME);
        basePage = new BasePage();
        loginPage = new LoginPage();
    }

    @AfterEach
    public void tearDown() {
        BrowserFactory.getInstance().closeCurrentBrowser();
    }

    @Test
    public void signInSuccessfully() {
        PageHelper.waitVisibilityOfElement(basePage.btnSignIn, "basePage.btnSignIn").click();
        Assertions.assertTrue(loginPage.signIn(PropertyReader.getProperty("prod.user.email"), PropertyReader.getProperty("prod.user.password")));
        Assertions.assertEquals("http://automationpractice.com/index.php?controller=my-account", BrowserFactory.getInstance().getCurrentBrowser().getCurrentUrl());
    }
}
