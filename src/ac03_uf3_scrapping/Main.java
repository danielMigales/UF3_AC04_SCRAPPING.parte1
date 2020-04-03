package ac03_uf3_scrapping;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 * @author Daniel Migales Puertas
 *
 */

//Ejercicio1:
//1.Descarga el html .https://es.wikipedia.org/wiki/Anexo:Pel%C3%ADculas_con_más_premios_Óscar
//2. Extrae los datos de manera secuencial de la tabla de datos del html.
//3.Genera el xml resultante utilizando la técnica que quieras.
//
public class Main {

    public static void main(String[] args) throws ParserConfigurationException, IOException, TransformerException {

        Gestor gestion = new Gestor();
        gestion.leerHtml("peliculas.html");
        gestion.crearXML();

    }

}
