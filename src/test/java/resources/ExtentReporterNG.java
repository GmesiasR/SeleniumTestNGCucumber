package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import testcomponents.BaseTest;

/**
 * Clase encargada de configurar y generar el reporte de los resultados de las pruebas
 * utilizando la librería ExtentReports, que crea un reporte en formato HTML.
 * Extiende la clase BaseTest para heredar funcionalidades comunes en las pruebas.
 */
public class ExtentReporterNG extends BaseTest {

    /**
     * Ruta donde se almacenará el reporte generado.
     * Este path se combina con el directorio de los reportes definido previamente.
     */
    static String reporterPath = reportsPath + "//index.html";

    /**
     * METODO estático que crea y configura el objeto ExtentReports.
     * Este objeto se utiliza para generar el reporte HTML con los resultados de las pruebas.
     *
     * @return Un objeto ExtentReports que contiene la configuración del reporte y la información del sistema.
     */
    public static ExtentReports getReportObject() {
        // Crear el reportador con la ruta especificada
        ExtentSparkReporter reporter = new ExtentSparkReporter(reporterPath);

        // Configurar el nombre del reporte que aparecerá en el encabezado
        reporter.config().setReportName("WebAutomationResults");

        // Configurar el título del documento en el reporte
        reporter.config().setDocumentTitle("Test Results");

        // Crear el objeto ExtentReports
        ExtentReports extent = new ExtentReports();

        // Adjuntar el reportador al objeto ExtentReports
        extent.attachReporter(reporter);

        // Configurar la información del sistema, en este caso el nombre del tester
        extent.setSystemInfo("Tester", "Gonzalo Mesias");

        // Devolver el objeto ExtentReports configurado
        return extent;
    }
}

