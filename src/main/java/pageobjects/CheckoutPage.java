package pageobjects;

import abstractcomponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * Esta clase representa la página de checkout (compra) en una aplicación web.
 * Extiende la clase base {@link AbstractComponents}, lo que sugiere que sigue el patrón de diseño Page Object.
 * Proporciona métodos para seleccionar un país y enviar un pedido.
 */
public class CheckoutPage extends AbstractComponents {

    WebDriver driver;

    /**
     * Constructor de la clase CheckoutPage.
     * Inicializa el WebDriver y la clase base. También inicializa los elementos de la página utilizando la clase {@link PageFactory}.
     *
     * @param driver El controlador WebDriver utilizado para interactuar con la página.
     */
    public CheckoutPage(WebDriver driver) {
        super(driver); // Llama al constructor de la clase base
        this.driver = driver; // Asigna el WebDriver desde la prueba
        PageFactory.initElements(driver, this); // Inicializa los elementos de la página
    }

    // Elementos de la página mapeados con la anotación @FindBy
    @FindBy(css = ".action__submit")
    private WebElement submit; // Botón para enviar el pedido
    @FindBy(css = "[placeholder='Select Country']")
    private WebElement country; // Campo de selección de país
    @FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
    private WebElement selectCountry; // Botón para seleccionar el país
    private By results = By.cssSelector(".ta-results"); // Resultados de búsqueda de país

    /**
     * Selecciona un país en el campo de selección de país.
     * Este metodo realiza una búsqueda del país utilizando el nombre proporcionado y selecciona el país de los resultados.
     *
     * @param countryName El nombre del país que se desea seleccionar.
     * @throws InterruptedException Si la operación de selección se interrumpe.
     */
    public void selectCountry(String countryName) throws InterruptedException {
        Actions a = new Actions(driver);
        a.sendKeys(country, countryName).build().perform(); // Escribe el nombre del país en el campo de búsqueda

        waitForElementToAppear(results); // Espera a que aparezcan los resultados de la búsqueda
        Thread.sleep(1000); // Pausa para permitir que los resultados se carguen
        boolean xd = selectCountry.isDisplayed(); // Verifica si el botón de selección de país está visible
        selectCountry.click(); // Haz clic en el botón para seleccionar el país
    }

    /**
     * Envia el pedido y redirige al usuario a la página de confirmación.
     * Este metodo hace clic en el botón de enviar pedido y retorna una instancia de la página de confirmación.
     *
     * @return Una nueva instancia de {@link ConfirmationPage}, que representa la página de confirmación del pedido.
     */
    public ConfirmationPage submitOrder() {
        submit.click(); // Haz clic en el botón para enviar el pedido
        return new ConfirmationPage(driver); // Devuelve una nueva instancia de la página de confirmación
    }
}