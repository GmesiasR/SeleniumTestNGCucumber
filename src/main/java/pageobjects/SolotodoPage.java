package pageobjects;

import abstractcomponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Map;

public class SolotodoPage extends AbstractComponents{


    WebDriver driver;

    /**
     * Constructor de la clase CartPage.
     * Inicializa el WebDriver y la clase base. También inicializa los elementos de la página utilizando la clase {@link AbstractComponents}.
     *
     * @param driver El controlador WebDriver utilizado para interactuar con la página.
     */
    public SolotodoPage(WebDriver driver) {
        super(driver); // Llama al constructor de la clase base
        this.driver = driver; // Asigna el WebDriver desde la prueba
        PageFactory.initElements(driver, this); // Inicializa los elementos de la página
    }

    // Elementos de la página mapeados con la anotación @FindBy
    @FindBy(xpath = "//button[contains(text(),'Tecnología')]")
    private WebElement headerTecnologiabtn;
    @FindBy(xpath = "//button[contains(text(),'Hardware')]")
    private WebElement headerHardwarebtn;
    @FindBy(xpath = "//button[contains(text(),'Electro')]")
    private WebElement headerElectrobtn;
    @FindBy(xpath = "//button[contains(text(),'Periféricos')]")
    private WebElement headerPerifericosbtn;
    @FindBy(css = ".MuiButton-textSecondary")
    WebElement headerPerfilBtn;
    @FindBy(xpath = "(//button[contains(@class, 'MuiButtonBase-root') and contains(@class, 'MuiIconButton-root')])[2]")
    WebElement headerConfigButton;


    public void goTo() {
        driver.get("https://www.solotodo.cl/");
    }

    public boolean searchMainElementsInHeader(String elementName) {
        // Mapeamos los nombres de los elementos con sus respectivos WebElements
        Map<String, WebElement> elementsMap = Map.of(
                "Tecnologia", headerTecnologiabtn,
                "Electro", headerElectrobtn,
                "Hardware", headerHardwarebtn,
                "Perifericos", headerPerifericosbtn,
                "boton de perfil", headerPerfilBtn,
                "boton de configuracion", headerConfigButton
        );
        // Verificamos si el elemento existe en el mapa y está visible
        return elementsMap.containsKey(elementName) && elementsMap.get(elementName).isDisplayed();
    }
}
