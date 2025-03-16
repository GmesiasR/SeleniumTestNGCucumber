package data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

/**
 * Clase encargada de leer un archivo JSON y convertir su contenido en una lista de mapas (HashMap).
 * Cada mapa representa una fila de datos, donde las claves y valores son de tipo String.
 */
public class DataReader {

    /**
     * METODO que lee un archivo JSON y convierte su contenido en una lista de mapas (HashMap).
     * El archivo JSON debe tener una estructura que permita ser mapeada a una lista de objetos
     * de tipo HashMap, donde las claves y valores sean de tipo String.
     *
     * @param filePath La ruta del archivo JSON que contiene los datos.
     * @return Una lista de objetos HashMap, donde cada HashMap contiene pares clave-valor
     * correspondientes a los datos en el archivo JSON.
     * @throws IOException Si ocurre un error al leer el archivo o procesar su contenido.
     */
    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

        // Leer el contenido del archivo JSON como una cadena de texto
        String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

        // Crear un objeto ObjectMapper para convertir la cadena JSON en objetos Java
        ObjectMapper mapper = new ObjectMapper();

        // Convertir el contenido JSON a una lista de HashMaps usando la librería Jackson
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });

        // Retornar la lista de HashMaps con los datos leídos del archivo JSON
        return data;
    }
}

