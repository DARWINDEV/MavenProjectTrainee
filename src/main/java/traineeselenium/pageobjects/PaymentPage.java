package traineeselenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import traineeselenium.AbstractComponents.AbstractComponents;



public class PaymentPage extends AbstractComponents {

    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement country;

    @FindBy(xpath = "//button[contains(@class,'ng-star-inserted')]")
    WebElement selectCountry;

    By countries = By.cssSelector(".ta-results");

    public PaymentPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void selectCountry(WebDriver driver, String countryName){
        Actions a = new Actions(driver);
        a.sendKeys(country, countryName).build().perform();
        waitElementToAppear(countries);
        selectCountry.click();
    }

}
