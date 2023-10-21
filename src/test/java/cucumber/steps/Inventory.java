package cucumber.steps;

import cucumber.hooks.TestHooks;
import io.cucumber.java.en.Then;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@Log4j2
public class Inventory  {

    private WebDriver driver;

    public Inventory() {
        driver = TestHooks.getInstance().getDriver();
    }

    @Then("user can view (.*) images$")
    public void user_can_view_images(String is_image) {
        log.info(driver.getCurrentUrl());
        List<WebElement> elements = driver.findElements(new By.ByXPath("//img[@class='inventory_item_img']"));
        for (WebElement imgElement : elements) {
            // You can access attributes or perform actions on each img element
            String imgSrc = imgElement.getAttribute("src");
            log.info("Image Source: " + imgSrc);
            if("correct".equalsIgnoreCase(is_image)) {
                Assert.assertNotEquals("https://www.saucedemo.com/static/media/sl-404.168b1cce.jpg", imgSrc);
            } else if("wrong".equalsIgnoreCase(is_image)) {
                Assert.assertEquals("https://www.saucedemo.com/static/media/sl-404.168b1cce.jpg", imgSrc);
            }
        }
    }

//    /html//div[@id='inventory_container']/div/div[@id='inventory_container']/div/div[1]/div[@class='inventory_item_img']/a[@href='#']/img[@alt='Sauce Labs Backpack']
    //
}
