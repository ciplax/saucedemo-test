package cucumber.steps;

import cucumber.hooks.TestHooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.StringUtil;

@Log4j2
public class Login {
    private WebDriver driver;

    public Login() {
        driver = TestHooks.getInstance().getDriver();
    }

    @Given("user is on saucedemo login page")
    public void user_is_on_login_page() {
        driver.get(TestHooks.BASE_URL);
    }

    @When("user input (.*) as username$")
    public void user_input_username(String username) {
        WebElement elUsernameTF = driver.findElement(new By.ByXPath("/html//input[@id='user-name']"));
        elUsernameTF.sendKeys(username);
    }

    @When("user input (.*) as password$")
    public void user_input_password(String password) {
        WebElement elPasswordTF = driver.findElement(new By.ByXPath("/html//input[@id='password']"));
        elPasswordTF.sendKeys(password);
    }

    @Then("user verify (.*) login result$")
    public void user_verify_login_result(String status) {
        if ("success".equalsIgnoreCase(status)) {
            //on success user will be redirected to inventory
            driver.getCurrentUrl();
            Assert.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
            log.info("Current URL : " + driver.getCurrentUrl());

        } else if ("failed".equalsIgnoreCase(status)) {
            try {
                Assert.assertEquals(TestHooks.BASE_URL, driver.getCurrentUrl());
                WebElement elErrorMessage = driver.findElement(new By.ByXPath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3"));
                log.info("errorMessage : " + elErrorMessage.getText());
                Assert.assertTrue(!StringUtil.isEmptyString(elErrorMessage.getText()));
            } catch (NoSuchElementException e) {
                Assert.fail();
            }

        }
    }
}
