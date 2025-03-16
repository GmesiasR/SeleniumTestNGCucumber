package tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.*;
import testcomponents.BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class StandAloneTestObject extends BaseTest {
    // Definición del nombre del producto para las pruebas
    String productName = "IPHONE 13 PRO";

    /**
     * Prueba que realiza el flujo de compra de un producto y verifica la confirmación de la orden.
     * Este metodo inicia sesión en la aplicación, selecciona un producto, lo agrega al carrito,
     * realiza el checkout y verifica el mensaje de confirmación de la orden.
     * Los datos de la prueba se obtienen de un DataProvider que provee las credenciales y el nombre del producto.
     *
     * @param email Dirección de correo electrónico del usuario.
     * @param password Contraseña del usuario.
     * @param productName Nombre del producto a agregar al carrito.
     * @throws InterruptedException Si la prueba es interrumpida.
     * @throws IOException Si ocurre un error durante la ejecución.
     */
    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void SubmitOrder(String email, String password, String productName) throws InterruptedException, IOException {
        // Realiza el inicio de sesión con los datos proporcionados
        ProductCataloguePage productCataloguePage = landingPage.loginApplication(email, password);

        // Obtiene la lista de productos disponibles y agrega el producto al carrito
        List<WebElement> products = productCataloguePage.getProductList();
        productCataloguePage.addProductToCart(productName);

        // Verifica que el producto agregado se encuentre en el carrito
        CartPage cartPage = productCataloguePage.goToCartPage();
        Boolean match = cartPage.VerifyProductsDisplay(productName);
        Assert.assertTrue(match);

        // Realiza el checkout, selecciona el país y completa el pedido
        CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
        checkoutPage.selectCountry("Ind");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();

        // Verifica que el mensaje de confirmación de la orden sea correcto
        String confirmationMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    /**
     * Prueba que verifica si el pedido realizado aparece en el historial de órdenes.
     * Este metodo depende de la ejecución exitosa de la prueba `SubmitOrder`. Después de realizar un pedido,
     * verifica si el producto aparece en el historial de órdenes.
     */
    @Test(dependsOnMethods = {"SubmitOrder"})
    public void OrderHistoryTest() {
        // Inicia sesión en la aplicación con credenciales predeterminadas
        ProductCataloguePage productCataloguePage = landingPage.loginApplication("anshika@gmail.com", "Iamking@000");

        // Navega a la página de órdenes y verifica que el producto esté en el historial
        OrderPage orderPage = productCataloguePage.goToOrdersPage();
        Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
    }

    /**
     * Proporciona los datos necesarios para ejecutar la prueba `SubmitOrder`.
     * Este metodo devuelve un conjunto de datos que incluye las credenciales de usuario y el producto
     * que se utilizarán para realizar la compra.
     *
     * @return Un array de objetos que contiene las credenciales y el producto necesario para ejecutar la prueba.
     * @throws IOException Si ocurre un error durante la ejecución.
     */
    @DataProvider
    public Object[][] getData() throws IOException {
        // Retorna un conjunto de datos con las credenciales de dos usuarios y el nombre del producto
        return new Object[][] {{"shetty@gmail.com", "Iamking@000", "IPHONE 13 PRO"}, {"anshika@gmail.com", "Iamking@000", "IPHONE 13 PRO"}};
    }
}
