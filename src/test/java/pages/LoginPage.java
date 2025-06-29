package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utils.CommonMethods;
import utils.ConfigReader;

import java.io.IOException;

public class LoginPage extends CommonMethods {

    public Locator usernameTextField;
    public Locator passwordTextField;
    public Locator loginButton;

    public void enterUsername() throws IOException {
        sendText(ConfigReader.read("userName"), loginPage.usernameTextField);
    }

    public LoginPage() {
        this.usernameTextField = page.locator("xpath=//*[@id='txtUsername']");
        this.passwordTextField = page.locator("#txtPassword");
        this.loginButton = page.locator("#btnLogin");
    }
}