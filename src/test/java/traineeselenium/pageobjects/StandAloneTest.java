package traineeselenium.pageobjects;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args) {

        String productName = "ZARA COAT 3";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/client");

        LandingPage ladingPage = new LandingPage(driver);
//        driver.findElement(By.id("userEmail")).sendKeys("d12311203@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Prueba123");
        driver.findElement(By.id("login")).click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        List<WebElement> cards = driver.findElements(By.cssSelector(".mb-3"));

        for (WebElement card : cards){
            if (card.getText().contains(productName)){
                card.findElement(By.cssSelector("[class='btn w-10 rounded']")).click();
                break;
            }
        }


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        //ng-animating
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

//        Verificar si el elemento seleccionado esta en el carrito
        List<WebElement> cartProducts = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
        Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);
        driver.findElement(By.xpath("//button[text()='Checkout']")).click();

//        Lista de paises


    }
}
