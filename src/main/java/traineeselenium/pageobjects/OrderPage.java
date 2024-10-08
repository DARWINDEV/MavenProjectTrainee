package traineeselenium.pageobjects;


import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import traineeselenium.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents {

    WebDriver driver;

    public OrderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".hero-primary")
    WebElement hero;


    public void confirmMessage(){
        String message = hero.getText();
        Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }
}

