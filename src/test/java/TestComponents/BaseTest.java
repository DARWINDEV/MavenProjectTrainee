package TestComponents;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import traineeselenium.pageobjects.LandingPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public LandingPage landingPage;

    public WebDriver initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream file = new FileInputStream("src/main/java/traineeselenium/resources/GlobalData.properties");
        prop.load(file);
        String browserName = prop.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            //Firefox
        } else if (browserName.equalsIgnoreCase("else")) {
            //Edge

        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;

    }
    @BeforeMethod
    public LandingPage launchApp() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.site();
        return landingPage;
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
