package omega.sgb.dominio;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class LibroVirtual {
    //Atributos de instancia
    private Integer id;
    private String isbn;
    private String titulo;
    private Integer cantidad;//número de libros físicos que existen
    private String autor;
    private ImageView imagenLibro;
    private Integer duracionPrestamo;//Por cuantos días se presta el libro
    private Integer multaValorDia;//No tiene setValorMultaDia
    private List<LibroFisico> librosFisicos;

    //Métodos constructores
    public LibroVirtual() {
    }

    public LibroVirtual(Integer id, String isbn, String titulo, Integer cantidad, String autor, Integer duracionPrestamo, Integer multaValorDia, List<LibroFisico> librosFisicos) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.cantidad = cantidad;
        this.autor = autor;
        this.duracionPrestamo = duracionPrestamo;
        this.multaValorDia = multaValorDia;
        this.librosFisicos = librosFisicos;
    }

    public LibroVirtual(String isbn, String titulo, Integer cantidad, String autor, ImageView imagenLibro,Integer duracionPrestamo, Integer multaValorDia, List<LibroFisico> librosFisicos) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.cantidad = cantidad;
        this.autor = autor;
        this.imagenLibro = imagenLibro;
        this.duracionPrestamo = duracionPrestamo;
        this.multaValorDia = multaValorDia;
        this.librosFisicos = librosFisicos;
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

    public ImageView getImagenLibro() {
        return imagenLibro;
    }

    public void setImagenLibro(ImageView imagenLibro) {
        this.imagenLibro = imagenLibro;
    }

    public Integer getDuracionPrestamo() {
        return duracionPrestamo;
    }

    public void setDuracionPrestamo(Integer duracionPrestamo) {
        this.duracionPrestamo = duracionPrestamo;
    }

    public Integer getMultaValorDia() {
        return multaValorDia;
    }

    public void setMultaValorDia(Integer multaValorDia) {
        this.multaValorDia = multaValorDia;
    }

    public List<LibroFisico> getLibrosFisicos() {
        return librosFisicos;
    }

    public void setLibrosFisicos(List<LibroFisico> librosFisicos) {
        this.librosFisicos = librosFisicos;
    }
}
