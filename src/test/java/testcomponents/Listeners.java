package testcomponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import resources.ExtentReporterNG;

import java.io.IOException;

/**
 * Esta clase representa los listeners del plugin TESTNG
 * con esta se crean metodos para cada test o flujo que se ejecute
 *
 * @author GonzaloMesias
 * @version 1.0
 */

public class Listeners extends BaseTest implements ITestListener {
    ExtentTest test;
    ExtentReports extent = ExtentReporterNG.getReportObject();
    ThreadLocal <ExtentTest>extentTest = new ThreadLocal(); //ThreadSafe


    /**
     * Metodo de inicia para cada testse ejecuta
     * antes de inicializar cualquier metodo
     * En este caso se crea el EXTENT que es para
     * la generacion del reporte, est[a detallada
     * una linea especifica que te permite correr
     * test en paralelo
     */
    @Override
    public void onTestStart(ITestResult result) {
       test =  extent.createTest(result.getMethod().getMethodName());//trae el resultado del nombre del metodo ( o en este caso el test)
       extentTest.set(test);// Crea un ID unico por cada test, no se ua el TEST directamente porque si usamos solo el Test en si se sobreescribiria
    }


    /**
     * Metodo para cuando los test son aprobados correctamente
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS,"Test Passed Succesfully");
    }

    /**
     * METODO que se ejecuta automáticamente cuando un test falla.
     * Se encarga de registrar el error en el reporte ExtentReports,
     * tomar un screenshot del estado del navegador y adjuntarlo al informe.
     *
     * @param result Objeto ITestResult que contiene detalles del test fallido.
     */
    @Override
    public void onTestFailure(ITestResult result) {
        // Obtener el objeto 'extentTest' del ThreadLocal actual y registrar la excepción.
        extentTest.get() // Recupera el ID del Thread para asociarlo con el reporte.
                .fail(result.getThrowable()); // Registra la causa de la falla en el informe.

        // Imprimir el nombre del method que falló.
        System.out.println("Aquí falla el test: " + result.getMethod().getMethodName());

        try {
            System.out.println("Intentando crear el driver");

            /**
             * Obtener el objeto WebDriver reflejando el campo "driver" de la clase de prueba.
             * Esto permite acceder al driver utilizado en el test fallido.
             */
            driver = (WebDriver) result.getTestClass() // Obtener la clase del test.
                    .getRealClass()                    // Obtener la clase real (no el proxy).
                    .getField("driver")                // Obtener el campo 'driver'.
                    .get(result.getInstance());        // Obtener el valor del campo 'driver' de la instancia del test.

        } catch (Exception e) {
            e.printStackTrace(); // Imprime el error si no se puede acceder al driver.
        }

        String filePath = null; // Ruta donde se almacenará el screenshot.

        try {
            System.out.println("Creando el screenshot");

            /**
             * Llama al method 'getScreenshoot' para capturar una imagen del estado actual del navegador.
             *
             * @param result.getMethod().getMethodName() Nombre del method de prueba fallido (para nombrar el archivo).
             * @param driver Instancia de WebDriver que representa el navegador activo.
             * @return Ruta del archivo de imagen capturado.
             */
            filePath = getScreenshoot(result.getMethod().getMethodName(), driver);

            System.out.println("Screenshot creado");
        } catch (Exception e) {
            e.printStackTrace(); // Imprime el error si no se pudo capturar el screenshot.
            System.out.println("Screenshot no creado");
        }

        /**
         * Adjunta el screenshot capturado al reporte de ExtentReports.
         *
         * @param filePath Ruta del screenshot capturado.
         * @param result.getMethod().getMethodName() Nombre del method fallido.
         */
        extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
    }

    /**
     * METODO que se ejecuta al finalizar la ejecución de todos los tests dentro del contexto.
     * Se encarga de limpiar y guardar el reporte generado por ExtentReports.
     *
     * @param context Objeto ITestContext que proporciona información sobre el entorno de ejecución de las pruebas.
     */
    @Override
    public void onFinish(ITestContext context) {
        // Guarda y limpia el reporte de ExtentReports, generando el archivo final.
        extent.flush();

        // Mensaje de confirmación en la consola al finalizar el proceso.
        System.out.println("Extent creado al finalizar");
    }

}
