package traineeselenium.pageobjects;

import traineeselenium.TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String, String> input) throws InterruptedException {

        ProductCatalogPage productCatalogue = landingPage.loginApp(input.get("email"), input.get("password"));
//        ProductCatalogPage productCatalogue = landingPage.loginApp(text, text);

        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCard(input.get("product"));
        CartPage cartPage = productCatalogue.goToCardPage();

        Boolean match = cartPage.verifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);
        CheckOutPage checkOutPage = cartPage.goToCheckout();
        checkOutPage.selectCountry("Mexico");
        ConfirmationPage confirmationPage = checkOutPage.submitOrder();

        String confirmMessage = confirmationPage.getConfirmationMsg();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        tearDown();

    }

    @Test(dependsOnMethods = {"submitOrder"}, dataProvider = "getData", groups = {"Purchase"})
    public void OrderHistoryTest(HashMap<String, String> input){
        ProductCatalogPage productCatalogue = landingPage.loginApp(input.get("email"), input.get("password"));
        OrderPage orderPage = productCatalogue.goToOrdersPage();
        Assert.assertTrue(orderPage.verifyOrderDisplay(input.get("product")));
        tearDown();
    }

    //Extent reports --

    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String, String>> data = getJsonDataToMap("src/test/java/data/PurchaseOrder.json");
        return new Object[][]{{data.get(0)}};
    }
}
