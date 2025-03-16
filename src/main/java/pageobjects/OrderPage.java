package pageobjects;

import abstractcomponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Esta clase representa la página de un pedido en una aplicación de comercio electrónico.
 * Extiende la clase base {@link AbstractComponents}, lo que sugiere que se utiliza el patrón de diseño Page Object.
 * Contiene métodos para interactuar con la lista de productos en el pedido, específicamente para verificar si un producto aparece en el pedido.
 */
public class OrderPage extends AbstractComponents {

    WebDriver driver;

    /**
     * Constructor de la clase OrderPage.
     * Inicializa el controlador WebDriver y la clase base.
     *
     * @param driver El controlador WebDriver utilizado para interactuar con la página.
     */
    public OrderPage(WebDriver driver) {
        super(driver); // Llama al constructor de la clase base
        this.driver = driver;
        PageFactory.initElements(driver, this); // Inicializa los elementos de la página
    }

    // Elemento mapeado con la anotación @FindBy
    @FindBy(css = "tr td:nth-child(3)")
    private List<WebElement> producNames; // Lista de nombres de productos en la tabla de pedido

    /**
     * Verifica si un producto aparece en el pedido.
     * <p>
     * Este metodo recorre la lista de productos en el pedido y comprueba si el nombre del producto proporcionado
     * coincide con alguno de los productos listados. La comparación se realiza de manera insensible a mayúsculas y minúsculas.
     *
     * @param productName El nombre del producto que se desea verificar en el pedido.
     * @return true si el producto aparece en el pedido, false si no se encuentra.
     */
    public boolean VerifyOrderDisplay(String productName) {
        // Filtra la lista de productos y verifica si alguno coincide con el nombre proporcionado, ignorando las mayúsculas/minúsculas
        return producNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
    }
}