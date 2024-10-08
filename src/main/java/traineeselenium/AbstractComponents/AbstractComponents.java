package traineeselenium.AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponents {

    WebDriver driver;

    public AbstractComponents(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "[routerlink*='cart']")
    WebElement cartBtn;

    @FindBy(xpath = "//button[text()='Checkout']")
    WebElement checkoutBtn;

    @FindBy(xpath = "//a[text()='Place Order ']")
    WebElement orderBtn;



    public void waitElementToAppear(By finBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(finBy));

    }

    public void checkOut(){
        checkoutBtn.click();
    }

    public void goToCardPage(){
        cartBtn.click();
    }

    public void waitElementToDisappear(WebElement element) throws InterruptedException {
        Thread.sleep(1000);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
//        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void orderButton(){
        orderBtn.click();
    }






}
