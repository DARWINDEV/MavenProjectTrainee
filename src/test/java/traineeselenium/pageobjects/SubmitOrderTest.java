package traineeselenium.pageobjects;

import TestComponents.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String productName = "ZARA COAT 3";
    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String, String> input) throws InterruptedException {

        ProductCatalogPage productCatalogue = landingPage.loginApp(input.get("email"), input.get("password"));
//        ProductCatalogPage productCatalogue = landingPage.loginApp(text, text);

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
        tearDown();

    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void OrderHistoryTest(){
        ProductCatalogPage productCatalogue = landingPage.loginApp("d12311203@gmail.com", "Prueba123");
        OrderPage orderPage = productCatalogue.goToOrdersPage();
        Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
        tearDown();
    }

    public String getScreeshot(String testCaseName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File("reports\\" + testCaseName + ".png");
        FileUtils.copyFile(source, file);
        return "reports" + testCaseName + ".png";

    }

    //Extent reports --

    @DataProvider
    public Object[][] getData() throws IOException {

//        HashMap<String, String> map = new HashMap<String, String>();
//        map.put("email", "d12311203@gmail.com");
//        map.put("password", "Prueba123");
//        map.put("product", "ZARA COAT 3");

        List<HashMap<String, String>> data = getJsonDataToMap("src/test/java/data/PurchaseOrder.json");
        return new Object[][]{{data.get(0)}};
    }
}
