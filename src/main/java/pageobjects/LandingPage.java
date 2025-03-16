package pageobjects;


import abstractcomponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Esta clase representa la página de aterrizaje (Landing Page) en una aplicación web.
 * Extiende la clase base {@link AbstractComponents}, lo que sugiere que utiliza el patrón de diseño Page Object.
 * Contiene métodos para interactuar con los elementos de la página, como el formulario de inicio de sesión y los mensajes de error.
 */
public class LandingPage extends AbstractComponents {
    WebDriver driver;

    /**
     * Constructor de la clase LandingPage.
     * Inicializa el controlador WebDriver y la clase base.
     * Además, inicializa los elementos de la página utilizando la clase {@link PageFactory}.
     *
     * @param driver El controlador WebDriver utilizado para interactuar con la página.
     */
    public LandingPage(WebDriver driver) {
        super(driver); // Llama al constructor de la clase base
        this.driver = driver; // Asigna el WebDriver desde la prueba
        PageFactory.initElements(driver, this); // Inicializa los elementos de la página
    }

    // Elementos de la página mapeados con la anotación @FindBy
    @FindBy(id = "userEmail")
    private WebElement userEmail; // Campo de entrada para el correo electrónico del usuario
    @FindBy(id = "userPassword")
    private WebElement userPassword; // Campo de entrada para la contraseña del usuario
    @FindBy(id = "login")
    private WebElement submit; // Botón de inicio de sesión
    @FindBy(css = "[class*='flyInOut']")
    private WebElement errorMessage; // Mensaje de error de inicio de sesión

    /**
     * Realiza el inicio de sesión en la aplicación.
     * <p>
     * Este metodo llena los campos de correo electrónico y contraseña, hace clic en el botón de inicio de sesión
     * y luego redirige a la página de catálogo de productos.
     *
     * @param email    El correo electrónico del usuario.
     * @param password La contraseña del usuario.
     * @return Un objeto de la clase {@link ProductCataloguePage}, que representa la página del catálogo de productos.
     */
    public ProductCataloguePage loginApplication(String email, String password) {
        userEmail.sendKeys(email); // Ingresa el correo electrónico
        userPassword.sendKeys(password); // Ingresa la contraseña
        submit.click(); // Hace clic en el botón de inicio de sesión
        return new ProductCataloguePage(driver); // Devuelve una instancia de la página del catálogo de productos
    }

    /**
     * Obtiene el mensaje de error que aparece en caso de que las credenciales de inicio de sesión sean incorrectas.
     * <p>
     * Este metodo espera a que el mensaje de error aparezca y luego lo retorna como texto.
     *
     * @return El mensaje de error mostrado en la página.
     */
    public String getErrorMessage() {
        waitForWebElementToAppear(errorMessage); // Espera a que el mensaje de error aparezca
        return errorMessage.getText(); // Devuelve el texto del mensaje de error
    }

    /**
     * Navega a la página de aterrizaje de la aplicación.
     * <p>
     * Este metodo abre la URL de la página de aterrizaje de la aplicación.
     */
    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client"); // Navega a la página de aterrizaje
    }
}