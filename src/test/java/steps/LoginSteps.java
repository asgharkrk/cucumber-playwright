package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.CommonMethods;
import utils.ConfigReader;

import java.io.IOException;

public class LoginSteps extends CommonMethods {

    @Given("user is navigated to HRMS application")
    public void user_is_navigated_to_hrms_application() throws IOException {
        openBrowserAndLaunchApplication();
    }

    @When("user enters valid username and password")
    public void user_enters_valid_username_and_password() throws IOException {
        sendText(ConfigReader.read("userName"), loginPage.usernameTextField);
        sendText(ConfigReader.read("password"), loginPage.passwordTextField);
    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        click(loginPage.loginButton);
    }

    @Then("user is successfully logged in")
    public void user_is_successfully_logged_in() {
        // Check if welcome message is visible using Playwright's locator
        Assert.assertTrue(dashboardPage.welcomeMessage.isVisible());

        String expectedUrl = "http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/dashboard";
        String actualUrl = page.url();
        Assert.assertEquals(expectedUrl, actualUrl);
    }
}