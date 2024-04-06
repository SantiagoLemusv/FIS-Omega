package omega.sgb.dominio;

public class LibroVirtual {
    private Integer id;
    private String isbn;
    private String titulo;
    private Integer cantidad;
    private String autor;

    //Métodos constructores
    public LibroVirtual() {
    }

    public LibroVirtual(Integer id, String isbn, String titulo, Integer cantidad, String autor) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.cantidad = cantidad;
        this.autor = autor;
    }

    //Métodos de acceso
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
