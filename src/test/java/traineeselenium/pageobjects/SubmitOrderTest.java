package traineeselenium.pageobjects;

import TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SubmitOrderTest extends BaseTest {

    @Test
    public void submitOrder() throws InterruptedException {
        String productName = "ZARA COAT 3";

        ProductCatalogPage productCatalogue = landingPage.loginApp("d12311203@gmail.com", "Prueba123");

        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCard(productName);
        CartPage cartPage = productCatalogue.goToCardPage();

        Boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckOutPage checkOutPage = cartPage.goToCheckout();
        checkOutPage.selectCountry("Mexico");
        ConfirmationPage confirmationPage = checkOutPage.submitOrder();

        String confirmMessage = confirmationPage.getConfirmationMsg();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

    }
}
