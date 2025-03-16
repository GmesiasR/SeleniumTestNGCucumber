package tests;


import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.CartPage;
import pageobjects.ProductCataloguePage;
import testcomponents.BaseTest;
import testcomponents.Retry;

import java.io.IOException;
import java.util.List;


/**
 * Esta clase contiene pruebas de validación de errores relacionadas con el inicio de sesión y la adición de productos al carrito.
 * Ambas pruebas están relacionadas con la validación de mensajes de error y comportamientos inesperados que puedan ocurrir
 * durante el flujo de la aplicación.
 * La clase extiende {@link BaseTest}, lo que permite reutilizar configuraciones comunes para las pruebas.
 */
public class ErrorValidationTest extends BaseTest {

    /**
     * Prueba para validar el mensaje de error cuando se ingresa un correo electrónico o contraseña incorrectos.
     * Esta prueba asegura que el sistema muestre el mensaje de error correcto en caso de un inicio de sesión fallido.
     *
     * @throws InterruptedException Si el hilo se interrumpe durante la ejecución de la prueba.
     * @throws IOException          Si ocurre un error de entrada/salida.
     */
    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void LoginErrorValidation() throws InterruptedException, IOException {
        // Datos de prueba
        String productName = "IPHONE 13 PRO";

        // Iniciar sesión con credenciales incorrectas
        landingPage.loginApplication("anshika@gmail.com", "Iamkig@000");

        // Verificar si el mensaje de error mostrado es el esperado
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    }

    /**
     * Prueba para validar el manejo de errores al agregar un producto al carrito y luego verificar su visibilidad en la página del carrito.
     * Esta prueba verifica que el producto no se agregue correctamente al carrito si ocurre un error inesperado.
     *
     * @throws InterruptedException Si el hilo se interrumpe durante la ejecución de la prueba.
     * @throws IOException          Si ocurre un error de entrada/salida.
     */
    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void ProductErrorValidation() throws InterruptedException, IOException {
        // Datos de prueba
        String productName = "IPHONE 13 PRO";

        // Iniciar sesión con credenciales correctas
        ProductCataloguePage productCataloguePage = landingPage.loginApplication("anshika@gmail.com", "Iamking@000");

        // Obtener la lista de productos disponibles
        List<WebElement> products = productCataloguePage.getProductList();

        // Intentar agregar el producto al carrito
        productCataloguePage.addProductToCart(productName);

        // Navegar al carrito
        CartPage cartPage = productCataloguePage.goToCartPage();

        // Verificar si el producto agregado aparece en la página del carrito
        Boolean match = cartPage.VerifyProductsDisplay(productName);

        // Comprobar que el producto no se haya agregado correctamente
        try {
            Assert.assertFalse(match); // Asegurarse de que el producto no esté en el carrito
        } catch (Exception e) {
            e.printStackTrace(); // Imprimir el error si ocurre una excepción
        }
    }
}
