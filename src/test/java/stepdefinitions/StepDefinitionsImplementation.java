package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageobjects.*;
import testcomponents.BaseTest;

import java.io.IOException;
import java.util.List;

public class StepDefinitionsImplementation extends BaseTest {


    public LandingPage landingPage;
    public ProductCataloguePage productCataloguePage;
    public ConfirmationPage confirmationPage;

    @Given("I land on Ecommerce Page")
    public void IlandonEcommercePage() throws IOException {
        landingPage=launchApplication();
    }
    @Given("Logged in with username {} and password {}")
    public void loggedInWithUsernameAndPassword(String username, String password) {
        productCataloguePage = landingPage.loginApplication(username, password);
    }

    @When("I add the product {} from cart")
    public void iAddTheProductFromCart(String productName) throws InterruptedException {
        List<WebElement> products = productCataloguePage.getProductList();
        productCataloguePage.addProductToCart(productName);

    }

    @And("Checkout {} and submit the order")
    public void checkoutAndSubmitTheOrder(String productName) throws InterruptedException {
        // Verifica que el producto agregado se encuentre en el carrito
        CartPage cartPage = productCataloguePage.goToCartPage();
        Boolean match = cartPage.VerifyProductsDisplay(productName);
        Assert.assertTrue(match);

        // Realiza el checkout, selecciona el país y completa el pedido
        CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
        checkoutPage.selectCountry("Ind");
        confirmationPage = checkoutPage.submitOrder();
    }

    @Then("Message {} is displayed on confirmation page")
    public void messageIsDisplayedOnConfirmationPage(String message) {
        // Verifica que el mensaje de confirmación de la orden sea correcto
        String confirmationMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.equalsIgnoreCase(message));
        driver.close();
    }


    @Then("{} error message is displayed")
    public void errorMessageIsDisplayed(String message) {
        Assert.assertEquals(message, landingPage.getErrorMessage());
        driver.close();
    }


}
