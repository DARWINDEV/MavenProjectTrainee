package traineeselenium.pageobjects;

import TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidationsTest extends BaseTest {

    @Test
    public void LoginErrorValidation() throws IOException, InterruptedException {

        ProductCatalogPage productCatalogue = landingPage.loginApp("d12311203@gmail.com", "Prueb23");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    }
    @Test
    public void ProductErrorValidation() throws InterruptedException {
        String productName = "ZARA COAT 3";

        ProductCatalogPage productCatalogue = landingPage.loginApp("d12311203@gmail.com", "Prueba123");

        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCard(productName);
        CartPage cartPage = productCatalogue.goToCardPage();

        Boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);

    }
}
