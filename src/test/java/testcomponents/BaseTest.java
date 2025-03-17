package testcomponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageobjects.LandingPage;
import pageobjects.SolotodoPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 * Clase base para la configuración y gestión de pruebas automatizadas.
 * Proporciona la inicialización del navegador, la captura de pantallas,
 * la lectura de datos JSON y el control del ciclo de vida del WebDriver.
 */
public class BaseTest {

    // Opciones para los navegadores Chrome, Edge y Firefox
    ChromeOptions chromeOptions = new ChromeOptions();
    EdgeOptions edgeOptions = new EdgeOptions();
    FirefoxOptions firefoxOptions = new FirefoxOptions();

    // Objeto WebDriver utilizado para controlar el navegador
    public WebDriver driver;

    // Página de aterrizaje (LandingPage) que se utiliza como punto de entrada
    public LandingPage landingPage;
    public SolotodoPage solotodoPage;

    // Rutas del sistema y del directorio de informes
    protected static String systemPath = System.getProperty("user.dir");
    protected static String reportsPath = System.getProperty("user.dir") + "//reports//";

    // Extensión de las imágenes de las capturas de pantalla
    protected static String screenshotImageExtention = ".png";

    /**
     * Inicializa el WebDriver según el navegador especificado en las propiedades.
     *
     * @return Una instancia de WebDriver configurada para el navegador deseado.
     * @throws IOException Si ocurre un error al leer el archivo de configuración.
     */
    public WebDriver InitializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/resources/GlobalData.properties");
        prop.load(fis);

        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");

        switch (browserName) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                chromeOptions.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver(edgeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(firefoxOptions);
                break;

            default:
                // Si no se especifica un navegador válido, usar Chrome por defecto
                WebDriverManager.chromedriver().setup();
                chromeOptions.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(chromeOptions);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        return driver;
    }

    /**
     * Lee un archivo JSON y lo convierte en una lista de mapas (HashMap).
     *
     * @param filepath Ruta del archivo JSON a leer.
     * @return Una lista de mapas con los datos del JSON.
     * @throws IOException Si ocurre un error al leer el archivo JSON.
     */
    public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {

        // Leer el contenido del archivo JSON como una cadena de texto
        String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);

        // Convertir la cadena JSON en una lista de mapas (HashMap) utilizando Jackson
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });

        return data;
    }

    /**
     * Toma una captura de pantalla del estado actual del navegador.
     *
     * @param testCaseName Nombre del caso de prueba (para nombrar el archivo de la captura).
     * @param driver       Instancia del WebDriver utilizada para capturar la pantalla.
     * @return La ruta donde se guardó la captura de pantalla.
     * @throws IOException Si ocurre un error al guardar la imagen.
     */
    public String getScreenshoot(String testCaseName, WebDriver driver) throws IOException {

        // Capturar la pantalla como un archivo de imagen
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        // Definir la ruta y el nombre del archivo donde se guardará la captura
        File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
        FileUtils.copyFile(source, file);

        return reportsPath + testCaseName + screenshotImageExtention;
    }

    /**
     * METODO que se ejecuta antes de cada METODO de prueba.
     * Inicializa el navegador y navega a la página de inicio (LandingPage).
     *
     * @return Una instancia de LandingPage.
     * @throws IOException Si ocurre un error durante la inicialización del navegador.
     */
    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        driver = InitializeDriver(); // Inicializar el WebDriver
        landingPage = new LandingPage(driver); // Crear una instancia de LandingPage
        landingPage.goTo(); // Navegar a la página de inicio
        return landingPage;
    }
    public SolotodoPage launchApplicationSolotodo() throws IOException {
        driver = InitializeDriver(); // Inicializar el WebDriver
        solotodoPage = new SolotodoPage(driver); // Crear una instancia de LandingPage
        solotodoPage.goTo(); // Navegar a la página de inicio
        return solotodoPage;
    }

    /**
     * METODO que se ejecuta después de cada METODO de prueba.
     * Cierra el navegador y libera los recursos asociados.
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit(); // Cerrar el navegador
    }

}