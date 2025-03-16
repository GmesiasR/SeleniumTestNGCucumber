package testcomponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Implementación de la interfaz IRetryAnalyzer de TestNG.
 * Esta clase permite reintentar un test en caso de que falle,
 * con un número máximo de intentos configurado.
 */
public class Retry implements IRetryAnalyzer {

    /**
     * Contador de intentos realizados. Se incrementa cada vez que un test falla
     * y se vuelve a ejecutar.
     */
    int count = 0;

    /**
     * Número máximo de intentos permitidos. En este caso, el test se reintentará
     * hasta 2 veces en caso de fallo.
     */
    int maxTry = 2;

    /**
     * Este METODO es invocado por TestNG para determinar si se debe reintentar la ejecución de un test.
     *
     * @param result El objeto ITestResult que contiene información sobre el test actual.
     * @return {@code true} si el test debe ser reintentado, {@code false} si no debe reintentarse.
     *         El test se reintentará si el número de intentos no ha alcanzado el máximo permitido.
     */
    @Override
    public boolean retry(ITestResult result) {
        // Si el número de intentos es menor que el máximo permitido
        if (count < maxTry) {
            // Incrementar el contador de intentos
            count++;
            // Indicar que el test debe reintentarse
            return true;
        }
        // Si se ha alcanzado el número máximo de intentos, no reintentar
        return false;
    }
}