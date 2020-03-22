package ac03_uf3_scrapping;

/**
 *
 * @author Daniel Migales
 */

public class Peliculas {

    private String href;
    private String titulo;

    public Peliculas(String href, String titulo) {
       
        this.href = href; 
        this.titulo = titulo;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return  href + "\n" + titulo;
    }

}
