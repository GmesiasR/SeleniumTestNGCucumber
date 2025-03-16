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

public class StandAloneTestDataHashMap extends BaseTest {
    // Definición del nombre del producto para las pruebas
    String productName = "IPHONE 13 PRO";

    /**
     * Prueba que realiza el flujo de compra de un producto y verifica la confirmación de la orden.
     * Este metodo inicia sesión en la aplicación, selecciona un producto, lo agrega al carrito,
     * realiza el checkout y verifica el mensaje de confirmación de la orden.
     *
     * @param input HashMap que contiene los datos para la prueba (email, password, productName).
     * @throws InterruptedException Si la prueba es interrumpida.
     * @throws IOException          Si ocurre un error durante la ejecución.
     */
    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {
        // Realiza el inicio de sesión con los datos proporcionados
        ProductCataloguePage productCataloguePage = landingPage.loginApplication(input.get("email"), input.get("password"));

        // Obtiene la lista de productos disponibles y agrega el producto al carrito
        List<WebElement> products = productCataloguePage.getProductList();
        productCataloguePage.addProductToCart(input.get("productName"));

        // Verifica que el producto agregado se encuentre en el carrito
        CartPage cartPage = productCataloguePage.goToCartPage();
        Boolean match = cartPage.VerifyProductsDisplay(input.get("productName"));
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
     * Este metodo depende de la ejecución exitosa de la prueba `submitOrder`. Después de realizar un pedido,
     * verifica si el producto aparece en el historial de órdenes.
     */
    @Test(dependsOnMethods = {"submitOrder"})
    public void OrderHistoryTest() {
        // Inicia sesión en la aplicación
        ProductCataloguePage productCataloguePage = landingPage.loginApplication("anshika@gmail.com", "Iamking@000");

        // Navega a la página de órdenes y verifica que el producto esté en el historial
        OrderPage orderPage = productCataloguePage.goToOrdersPage();
        Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
    }

    /**
     * Proporciona los datos necesarios para ejecutar la prueba `submitOrder`.
     * Este metodo proporciona dos conjuntos de datos diferentes que contienen el correo electrónico, la contraseña
     * y el nombre del producto para cada ejecución de la prueba.
     *
     * @return Un array de objetos que contiene los datos necesarios para las pruebas.
     */
    @DataProvider
    public Object[][] getData() {
        // Primer conjunto de datos
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("email", "anshika@gmail.com");
        map.put("password", "Iamking@000");
        map.put("productName", "IPHONE 13 PRO");

        // Segundo conjunto de datos
        HashMap<String, String> map1 = new HashMap<String, String>();
        map1.put("email", "shetty@gmail.com");
        map1.put("password", "Iamking@000");
        map1.put("productName", "IPHONE 13 PRO");

        // Retorna los datos como un array de objetos
        return new Object[][]{{map}, {map1}};
    }
}

