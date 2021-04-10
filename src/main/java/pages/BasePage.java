package pages;

import factory.BrowserFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    @FindBy(xpath = "//a[@class='login'][contains(.,'Sign in')]")
    WebElement btnSignIn;

    public BasePage() {
        PageFactory.initElements(BrowserFactory.getInstance().getCurrentBrowser(), this);
    }


}
