package traineeselenium.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import traineeselenium.AbstractComponents.AbstractComponents;

import java.util.List;

public class OrderPage extends AbstractComponents {
    WebDriver driver;

    @FindBy(css = ".totalRow button")
    WebElement checkoutEle;

    @FindBy(css = "tr td:nth-child(3)")
    private List<WebElement> productNames;

    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean verifyOrderDisplay(String productName){
        Boolean match = productNames.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
        return match;
    }



}