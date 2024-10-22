package traineeselenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import traineeselenium.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

    WebDriver driver;

    public LandingPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

//    PageFactorty

    @FindBy(id="userEmail")
    WebElement getUserEmail;

    @FindBy(id="userPassword")
    WebElement getUserPassword;

    @FindBy(id = "login")
    WebElement submit;

    @FindBy(css = ".toast-container")
    WebElement errorText;

    public ProductCatalogPage loginApp(String email, String pass){
        getUserEmail.sendKeys(email);
        getUserPassword.sendKeys(pass);
        submit.click();
        ProductCatalogPage productCatalogPage = new ProductCatalogPage(driver);
        return productCatalogPage;
    }

    public String getErrorMessage(){
        waitForWebElementToAppear(errorText);
        return errorText.getText();
    }

    public void site(){
        driver.get("https://rahulshettyacademy.com/client");
    }
}
