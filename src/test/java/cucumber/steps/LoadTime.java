package cucumber.steps;

import cucumber.hooks.TestHooks;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Log4j2
public class LoadTime {

    private WebDriver driver;
    long loadStartTime;
    long loadEndTime;

    public LoadTime() {
        driver = TestHooks.getInstance().getDriver();
    }

    @When("count total loadtime")
    public void count_total_loadtime() {
//        start_timer();
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        ExpectedCondition<WebElement> footerCondition = ExpectedConditions.presenceOfElementLocated(By.xpath("//footer[@class='footer']//ul[@class='social']"));
        webDriverWait.until(footerCondition);
        stop_timer();
    }

    @When("start timer")
    public void start_timer() {
        loadStartTime = System.currentTimeMillis();
    }

    public void stop_timer() {
        loadEndTime = System.currentTimeMillis();
    }

    @When("total load time after login is under (.*)$")
    public void start_timer(int threshold) {
        long loadTime = loadEndTime - loadStartTime;
        log.info("StartTime : " + loadStartTime);
        log.info("EndTime : " + loadEndTime);
        Assert.assertTrue(loadTime < threshold*1000);
    }
}
