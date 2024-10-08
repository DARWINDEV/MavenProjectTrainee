package traineeselenium.pageobjects;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class SubmitOrderTest {
    public static void main(String[] args) throws InterruptedException {

        String productName = "ZARA COAT 3";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        LandingPage ladingPage = new LandingPage(driver);
        ladingPage.site();
        ladingPage.loginApp("d12311203@gmail.com", "Prueba123");
        CatalogPage productCatalog = new CatalogPage(driver);
        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCard(productName);
        productCatalog.goToCardPage();

        CartPage productCard = new CartPage(driver);
        productCard.verifyProductDisplay(productName);
        productCard.checkOut();

        PaymentPage payment = new PaymentPage(driver);
        payment.selectCountry(driver, "Mexico");
        payment.orderButton();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.confirmMessage();
        driver.quit();

    }
}
