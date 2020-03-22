package ac03_uf3_scrapping;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author Daniel Migales
 */

public class Gestor {

    private ArrayList<String> href = new ArrayList<String>();
    private ArrayList<String> title = new ArrayList<String>();
    private ArrayList<Peliculas> listadoPeliculas = new ArrayList<Peliculas>();

    Peliculas pelicula;

    public void leerHtml(String archivo) throws FileNotFoundException, IOException {

        String cadena;
        FileReader file = new FileReader(archivo);
        System.out.println("Resultado extraido del html: \n" );
        try (BufferedReader buffer = new BufferedReader(file)) {
            while ((cadena = buffer.readLine()) != null) {
                if (cadena.contains("<td><i>")) {
                    String[] arraySplit = cadena.split("\"");
                    href.add(arraySplit[1]);
                    title.add(arraySplit[3]);
                    pelicula = new Peliculas(arraySplit[1], arraySplit[3]);                   
                    System.out.println(pelicula.toString() + "\n");
                    listadoPeliculas.add(pelicula);
                }
            }
        }
    }

    public void crearXML() throws IOException, ParserConfigurationException, TransformerException {

        String nombreDocumento = "peliculas_oscarizadas";

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementation = builder.getDOMImplementation();
        Document document = implementation.createDocument(null, nombreDocumento, null);
        document.setXmlVersion("1.0");

        for (Peliculas listadoPelicula : listadoPeliculas) {

            Element raiz = document.createElement("pelicula");
            document.getDocumentElement().appendChild(raiz);

            String hipervinculo = listadoPelicula.getHref();
            CrearElemento("hipervinculo", hipervinculo, raiz, document);
            String titulo = listadoPelicula.getTitulo();           
            CrearElemento("titulo", titulo, raiz, document);

            Source source = new DOMSource(document);
            Result result = new StreamResult(new java.io.File(nombreDocumento + ".xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        }
    }

    public static void CrearElemento(String dato, String valor, Element raiz, Document document) {

        Element elem = document.createElement(dato);
        Text text = document.createTextNode(valor);
        raiz.appendChild(elem);
        elem.appendChild(text);
    }

}
