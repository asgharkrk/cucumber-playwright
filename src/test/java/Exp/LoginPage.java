import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;

public class LoginPage {
  private final Page page;
  public Locator userNameField;
  public Locator userPasswordField;
  public Locator loginButton;
  public Locator welcomeMessage;

  public LoginPage(Page page) {
    this.page = page;
    this.userNameField = page.locator("#loginForm\\:username");
    this.userPasswordField = page.locator("#loginForm\\:passwd");
    this.loginButton = page.locator("xpath=//span[@class='ui-button-text ui-c']");
    this.welcomeMessage = page.locator(".card-title");
  }
}