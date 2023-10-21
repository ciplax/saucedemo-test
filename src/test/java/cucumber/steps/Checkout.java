package cucumber.steps;

import cucumber.hooks.TestHooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.StringUtil;

@Log4j2
public class Checkout {

    private WebDriver driver;

    public Checkout() {
        driver = TestHooks.getInstance().getDriver();
    }

    @Given("user is on logged in using standard_user")
    public void user_is_on_logged_in_using_standard_user() {
        driver.get(TestHooks.BASE_URL);
        driver.findElement(new By.ByXPath("/html//input[@id='user-name']")).sendKeys("standard_user");
        driver.findElement(new By.ByXPath("/html//input[@id='password']")).sendKeys("secret_sauce");
        driver.findElement(new By.ByXPath("/html//input[@id='login-button']")).click();
    }

    @When("user add cart sauce labs backpack to cart")
    public void user_add_cart_sauce_labs_backpack() {
        driver.findElement(new By.ByXPath("/html//button[@id='add-to-cart-sauce-labs-backpack']")).click();
    }

    @When("user click cart icon")
    public void user_click_cart_icon() {
        driver.findElement(new By.ByXPath("//div[@id='shopping_cart_container']/a[@class='shopping_cart_link']")).click();
    }

    @When("user click checkout button")
    public void user_click_checkout_button() {
        driver.findElement(new By.ByXPath("/html//button[@id='checkout']")).click();
    }

    @When("user fill {string} and {string} and {int}")
    public void user_click_checkout_button(String firstName, String lastName, int postalCode) {
        driver.findElement(new By.ByXPath("/html//input[@id='first-name']")).sendKeys(firstName);
        driver.findElement(new By.ByXPath("/html//input[@id='last-name']")).sendKeys(lastName);
        driver.findElement(new By.ByXPath("/html//input[@id='postal-code']")).sendKeys(""+postalCode);
    }

    @When("user click continue")
    public void user_click_continue() {
        driver.findElement(new By.ByXPath("/html//input[@id='continue']")).click();
    }

    @Then("user verify checkout result (.*)$")
    public void user_verify_checkout_result(String status) {
        if ("success".equalsIgnoreCase(status)) {
            Assert.assertEquals("https://www.saucedemo.com/checkout-step-two.html", driver.getCurrentUrl());
        } else if ("error".equalsIgnoreCase(status)) {
            Assert.assertEquals("https://www.saucedemo.com/checkout-step-one.html", driver.getCurrentUrl());
            WebElement elErrorMessage = driver.findElement(new By.ByXPath("/html//div[@id='checkout_info_container']//form//h3"));
            log.info("errorMessage : " + elErrorMessage.getText());
            Assert.assertTrue(!StringUtil.isEmptyString(elErrorMessage.getText()));
        }
    }

}
