import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;
import org.junit.After;
import org.junit.Before;


public class StartOptions {
    protected static WebDriver driver;


    @Before
    public void startBrowser() {
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
