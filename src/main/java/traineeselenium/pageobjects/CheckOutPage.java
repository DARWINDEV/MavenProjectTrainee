package traineeselenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import traineeselenium.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents {

    WebDriver driver;

    public CheckOutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".actions a")
    WebElement submit;

    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement country;

    @FindBy(xpath = "//button[contains(@class,'ng-star-inserted')]")
    WebElement selectCountry;

    By countries = By.cssSelector(".ta-results");

    public void selectCountry(String countryName){
        Actions a = new Actions(driver);
        a.sendKeys(country, countryName).build().perform();
        waitElementToAppear(countries);
        selectCountry.click();

    }

    public ConfirmationPage submitOrder(){
        submit.click();
        return new ConfirmationPage(driver);
    }
}
