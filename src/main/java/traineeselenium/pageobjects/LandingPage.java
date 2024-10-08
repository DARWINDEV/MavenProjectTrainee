package traineeselenium.pageobjects;

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

    public void loginApp(String email, String pass){
        getUserEmail.sendKeys(email);
        getUserPassword.sendKeys(pass);
        submit.click();
    }

    public void site(){
        driver.get("https://rahulshettyacademy.com/client");
    }
}
