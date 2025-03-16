package pageobjects;


import abstractcomponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Esta clase representa la página del catálogo de productos en una aplicación de comercio electrónico.
 * Extiende la clase base {@link AbstractComponents}, lo que sugiere que se utiliza el patrón de diseño Page Object.
 * Contiene métodos para interactuar con los productos de la página, incluyendo obtener la lista de productos, buscar un producto por su nombre y añadir productos al carrito de compras.
 */
public class ProductCataloguePage extends AbstractComponents {

    WebDriver driver;

    /**
     * Constructor de la clase ProductCataloguePage.
     * Inicializa el controlador WebDriver y los elementos de la página utilizando PageFactory.
     *
     * @param driver El controlador WebDriver utilizado para interactuar con la página.
     */
    public ProductCataloguePage(WebDriver driver) {
        super(driver); // Llama al constructor de la clase base
        this.driver = driver;
        PageFactory.initElements(driver, this); // Inicializa los elementos de la página
    }

    // Elementos de la página mapeados con la anotación @FindBy
    @FindBy(css = ".mb-3")
    private List<WebElement> products; // Lista de productos en la página
    @FindBy(css = ".ng-animating")
    private WebElement spinner; // Indicador de carga

    // Selectores de los elementos, definidos con By
    private final By productsBy = By.cssSelector(".mb-3");
    private final By addToCart = By.cssSelector(".card-body button:last-of-type");
    private final By toastMessage = By.cssSelector("#toast-container");

    /**
     * Obtiene la lista de productos disponibles en la página.
     * <p>
     * Este metodo espera a que los elementos de los productos sean visibles y luego devuelve la lista de productos.
     *
     * @return Lista de WebElements que representan los productos en la página.
     */
    public List<WebElement> getProductList() {
        waitForElementToAppear(productsBy); // Espera a que los productos estén visibles
        return products; // Devuelve la lista de productos
    }

    /**
     * Busca un producto por su nombre en la lista de productos.
     * <p>
     * Este metodo recorre la lista de productos y devuelve el WebElement correspondiente al producto
     * cuyo nombre coincide con el nombre proporcionado.
     *
     * @param productName El nombre del producto que se busca.
     * @return El WebElement del producto si se encuentra, o null si no se encuentra.
     */
    public WebElement getProductByName(String productName) {
        // Filtra la lista de productos y busca el que tenga el nombre coincidente
        return getProductList().stream()
                .filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName))
                .findFirst()
                .orElse(null); // Devuelve el primer producto coincidente o null si no se encuentra
    }

    /**
     * Añade un producto al carrito de compras.
     * <p>
     * Este metodo busca el producto por su nombre y hace clic en el botón "Añadir al carrito" correspondiente.
     * Luego espera a que aparezca el mensaje emergente (toast) que indica que el producto ha sido añadido correctamente.
     *
     * @param productName El nombre del producto que se va a añadir al carrito.
     * @throws InterruptedException Si la ejecución del hilo es interrumpida durante el proceso de espera.
     */
    public void addProductToCart(String productName) throws InterruptedException {
        WebElement prod = getProductByName(productName); // Busca el producto por nombre
        prod.findElement(addToCart).click(); // Hace clic en el botón "Añadir al carrito"
        waitForElementToAppear(toastMessage); // Espera a que aparezca el mensaje emergente
        Thread.sleep(2000);
    }
}

