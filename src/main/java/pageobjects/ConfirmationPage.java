package pageobjects;

import abstractcomponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Esta clase representa la página de confirmación en una aplicación web.
 * Extiende la clase base {@link AbstractComponents}, lo que sugiere que utiliza el patrón de diseño Page Object.
 * Contiene un metodo para obtener el mensaje de confirmación mostrado en la página después de una acción exitosa.
 */
public class ConfirmationPage extends AbstractComponents {

    WebDriver driver;

    /**
     * Constructor de la clase ConfirmationPage.
     * Inicializa el controlador WebDriver y la clase base.
     * Además, inicializa los elementos de la página utilizando la clase {@link PageFactory}.
     *
     * @param driver El controlador WebDriver utilizado para interactuar con la página.
     */
    public ConfirmationPage(WebDriver driver) {
        super(driver); // Llama al constructor de la clase base
        this.driver = driver; // Asigna el WebDriver desde la prueba
        PageFactory.initElements(driver, this); // Inicializa los elementos de la página
    }

    // Elemento de la página mapeado con la anotación @FindBy
    @FindBy(css = ".hero-primary")
    private WebElement confirmationMessage; // Mensaje de confirmación

    /**
     * Obtiene el mensaje de confirmación mostrado en la página.
     * <p>
     * Este metodo devuelve el texto del mensaje de confirmación que se muestra al usuario después de completar una acción.
     *
     * @return El mensaje de confirmación mostrado en la página.
     */
    public String getConfirmationMessage() {
        return confirmationMessage.getText(); // Devuelve el texto del mensaje de confirmación
    }
}