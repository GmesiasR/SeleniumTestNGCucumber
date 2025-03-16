package pageobjects;

import abstractcomponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Esta clase representa la página del carrito de compras en la aplicación web.
 * Extiende la clase base {@link AbstractComponents}, siguiendo el patrón de diseño Page Object.
 * Proporciona métodos para verificar la presencia de productos en el carrito y navegar a la página de checkout.
 */
public class CartPage extends AbstractComponents {

    WebDriver driver;

    /**
     * Constructor de la clase CartPage.
     * Inicializa el WebDriver y la clase base. También inicializa los elementos de la página utilizando la clase {@link AbstractComponents}.
     *
     * @param driver El controlador WebDriver utilizado para interactuar con la página.
     */
    public CartPage(WebDriver driver) {
        super(driver); // Llama al constructor de la clase base
        this.driver = driver; // Asigna el WebDriver desde la prueba
        PageFactory.initElements(driver, this); // Inicializa los elementos de la página
    }

    // Elementos de la página mapeados con la anotación @FindBy
    @FindBy(css = ".totalRow Button")
    private WebElement checkoutEle; // Botón para ir a la página de checkout
    @FindBy(css = ".cartSection h3")
    private List<WebElement> productTitles; // Lista de títulos de los productos en el carrito

    /**
     * Verifica si un producto está presente en el carrito.
     * Este metodo busca un producto en la lista de productos del carrito comparando su nombre.
     *
     * @param productName El nombre del producto que se desea verificar.
     * @return true si el producto está presente en el carrito, false en caso contrario.
     */
    public boolean VerifyProductsDisplay(String productName) {
        return productTitles.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName)); // Busca el producto por nombre
    }

    /**
     * Navega a la página de checkout.
     * Este metodo hace clic en el botón de checkout para redirigir al usuario a la página de checkout.
     *
     * @return Una nueva instancia de {@link CheckoutPage}, que representa la página de checkout.
     */
    public CheckoutPage goToCheckoutPage() {
        checkoutEle.click(); // Haz clic en el botón de checkout
        return new CheckoutPage(driver); // Devuelve una nueva instancia de la página de checkout
    }
}
