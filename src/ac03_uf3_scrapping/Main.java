package ac03_uf3_scrapping;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 * @author Daniel Migales
 */

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, IOException, TransformerException {

            Gestor gestion = new Gestor();
            gestion.leerHtml("peliculas.html");
            gestion.crearXML();

    }

}
