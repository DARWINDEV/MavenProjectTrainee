package traineeselenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import traineeselenium.AbstractComponents.AbstractComponents;

import java.util.List;

public class CartPage extends AbstractComponents {

    @FindBy(xpath = "//div[@class='cartSection']/h3")
    List<WebElement> products;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public Boolean verifyProductDisplay(String productName){
        Boolean match = products.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
        return match;
    }

}
