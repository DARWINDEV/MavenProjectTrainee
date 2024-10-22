package traineeselenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import traineeselenium.AbstractComponents.AbstractComponents;

import java.util.List;

public class ProductCatalogPage extends AbstractComponents {

    WebDriver driver;

    public ProductCatalogPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

//    PageFactory

    @FindBy(css = ".mb-3")
    List<WebElement> products;

    @FindBy(css = ".ng-animating")
    WebElement spinner;



    By productList = By.cssSelector(".mb-3");
    By addToCard = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");

    public List<WebElement>getProductList(){
        waitElementToAppear(productList);
        return products;
    }

    public WebElement getProductByName(String productName){
        WebElement prod = getProductList().stream().filter(product ->
                product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        return prod;
    }

    public void addProductToCard(String productName) throws InterruptedException {
        WebElement prod = getProductByName(productName);
        prod.findElement(addToCard).click();
        waitElementToAppear(toastMessage);
        waitElementToDisappear(spinner);
    }





}
