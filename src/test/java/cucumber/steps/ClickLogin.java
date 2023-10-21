package cucumber.steps;

import cucumber.hooks.TestHooks;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * ClickLogin class
 */

public class ClickLogin {
    private WebDriver driver;

    public ClickLogin() {
        driver = TestHooks.getInstance().getDriver();
    }

    @When("user click login")
    public void user_click_login() {
        driver.findElement(new By.ByXPath("/html//input[@id='login-button']")).click();
    }
}
