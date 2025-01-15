package traineeselenium.pageobjects;

import org.testng.annotations.DataProvider;
import traineeselenium.TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import traineeselenium.TestComponents.Retry;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ErrorValidationsTest extends BaseTest {

    @Test( dataProvider = "getData", groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void LoginErrorValidation(HashMap<String, String>input) throws IOException, InterruptedException {


        landingPage.loginApp(input.get("email"), input.get("incorrectPass"));
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
        tearDown();
    }
    @Test(dataProvider = "getData")
    public void ProductErrorValidation(HashMap<String,String> input) throws InterruptedException {
//        String productName = "IPHONE 13 PRO";

        ProductCatalogPage productCatalogue = landingPage.loginApp(input.get("email"), input.get("password"));

        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCard(input.get("product"));
        CartPage cartPage = productCatalogue.goToCardPage();

        Boolean match = cartPage.verifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);
        tearDown();
    }

    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String, String>> data = getJsonDataToMap("src/test/java/data/PurchaseOrder.json");
        return new Object[][]{{data.get(0)}};
    }
}
