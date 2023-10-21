package cucumber.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestHooks {

    private WebDriver driver;
    public static String BASE_URL = "https://www.saucedemo.com/";

    public TestHooks() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions opts = new ChromeOptions();
        opts.addArguments("--headless");

        driver = new ChromeDriver(opts);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        // Perform teardown actions after scenarios
        if (driver != null) {
            driver.quit(); // Close the WebDriver instance
        }
    }

    private static TestHooks instance;

    // Singleton pattern: Public method to provide or create the single instance
    public static synchronized TestHooks getInstance() {
        if (instance == null) {
            instance = new TestHooks();
        }
        return instance;
    }

    public WebDriver getDriver() {
        return driver; // Return the WebDriver instance for use in step definitions
    }
}
