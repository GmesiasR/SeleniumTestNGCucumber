package abstractcomponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.CartPage;
import pageobjects.OrderPage;

import java.time.Duration;

/**
 * Esta es una clase base abstracta que proporciona funcionalidades comunes para interactuar con las páginas de la aplicación web.
 * Es utilizada por otras clases de página para reutilizar métodos comunes como la navegación a páginas específicas,
 * y la espera de que los elementos aparezcan o desaparezcan.
 */
public class AbstractComponents {

    WebDriver driver;

    /**
     * Constructor de la clase AbstractComponents.
     * Inicializa el WebDriver y utiliza la clase {@link PageFactory} para inicializar los elementos de la página.
     *
     * @param driver El controlador WebDriver utilizado para interactuar con la página.
     */
    public AbstractComponents(WebDriver driver) {
        this.driver = driver; // Asigna el WebDriver a la clase
        PageFactory.initElements(driver, this); // Inicializa los elementos de la página utilizando PageFactory
    }

    // Elementos de la página mapeados con la anotación @FindBy
    @FindBy(css = "[routerlink*='cart']")
    WebElement cartHeader; // Enlace al carrito de compras
    @FindBy(css = "[routerlink*='myorders']")
    WebElement orderHeader; // Enlace a la página de pedidos

    /**
     * Navega a la página del carrito de compras.
     * Hace clic en el encabezado del carrito y devuelve una instancia de la página {@link CartPage}.
     *
     * @return Una nueva instancia de la página {@link CartPage}.
     */
    public CartPage goToCartPage() {
        cartHeader.click(); // Haz clic en el encabezado del carrito
        return new CartPage(driver); // Devuelve la instancia de la página del carrito
    }

    /**
     * Navega a la página de pedidos.
     * Hace clic en el encabezado de pedidos y devuelve una instancia de la página {@link OrderPage}.
     *
     * @return Una nueva instancia de la página {@link OrderPage}.
     */
    public OrderPage goToOrdersPage() {
        orderHeader.click(); // Haz clic en el encabezado de pedidos
        return new OrderPage(driver); // Devuelve la instancia de la página de pedidos
    }

    /**
     * Espera hasta que un elemento sea visible en la página.
     * Utiliza un {@link WebDriverWait} para esperar hasta que el elemento sea localizado y visible.
     *
     * @param findBy El localizador del elemento que se debe esperar.
     */
    public void waitForElementToAppear(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Espera de hasta 10 segundos
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy)); // Espera hasta que el elemento sea visible
    }

    /**
     * Espera hasta que un WebElement sea visible en la página.
     * Utiliza un {@link WebDriverWait} para esperar hasta que el elemento WebElement sea visible.
     *
     * @param element El elemento WebElement que se debe esperar.
     */
    public void waitForWebElementToAppear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Espera de hasta 10 segundos
        wait.until(ExpectedConditions.visibilityOf(element)); // Espera hasta que el WebElement sea visible
    }

    /**
     * Espera hasta que un WebElement se vuelva invisible en la página.
     * Utiliza un {@link WebDriverWait} para esperar hasta que el elemento se vuelva invisible.
     *
     * @param element El elemento WebElement que se debe esperar.
     * @throws InterruptedException Si la espera se interrumpe.
     */
    public void waitForWebElementToDissapear(WebElement element) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Espera de hasta 10 segundos
        wait.until(ExpectedConditions.invisibilityOf(element)); // Espera hasta que el WebElement sea invisible
        Thread.sleep(5000); // Pausa adicional de 5 segundos para asegurar la desaparición del elemento
    }

}