package omega.sgb.dominio;

import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class LibroVirtual {
    //Atributos de instancia
    private Integer id;
    private String isbn;
    private String titulo;
    private Integer cantidadCopias;//número de libros físicos que existen
    private String autor;
    private ImageView imagenLibro;
    private Integer duracionPrestamo;//Por cuantos días se presta el libro
    private Integer multaValorDia;//No tiene setValorMultaDia
    private List<LibroFisico> librosFisicosDisponibles;
    private List<LibroFisico> librosFisicosAgotados;
    private List<LibroFisico> librosFisicosReservados;
    private List<LibroFisico> librosFisicosTotales;

    //Métodos constructores
    public LibroVirtual() {
    }

    public LibroVirtual(Integer id, String isbn, String titulo, Integer cantidadCopias, String autor, Integer duracionPrestamo, Integer multaValorDia) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.cantidadCopias = cantidadCopias;
        this.autor = autor;
        this.duracionPrestamo = duracionPrestamo;
        this.multaValorDia = multaValorDia;
    }

    public LibroVirtual(String isbn, String titulo, Integer cantidadCopias, String autor, ImageView imagenLibro, Integer duracionPrestamo, Integer multaValorDia, List<LibroFisico> librosFisicosDisponibles, List<LibroFisico> librosFisicosAgotados) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.cantidadCopias = cantidadCopias;
        this.autor = autor;
        this.imagenLibro = imagenLibro;
        this.duracionPrestamo = duracionPrestamo;
        this.multaValorDia = multaValorDia;
        this.librosFisicosDisponibles = librosFisicosDisponibles;
        this.librosFisicosAgotados = librosFisicosAgotados;
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

    public Integer getCantidadCopias() {
        return cantidadCopias;
    }

    public void setCantidadCopias(Integer cantidadCopias) {
        this.cantidadCopias = cantidadCopias;
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

    public List<LibroFisico> getLibrosFisicosDisponibles() {
        return librosFisicosDisponibles;
    }

    public void setLibrosFisicosDisponibles(List<LibroFisico> librosFisicosDisponibles) {
        this.librosFisicosDisponibles = librosFisicosDisponibles;
    }

    public List<LibroFisico> getLibrosFisicosAgotados() {
        return librosFisicosAgotados;
    }

    public void setLibrosFisicosAgotados(List<LibroFisico> librosFisicosAgotados) {
        this.librosFisicosAgotados = librosFisicosAgotados;
    }

    public List<LibroFisico> getLibrosFisicosReservados() {
        return librosFisicosReservados;
    }

    public void setLibrosFisicosReservados(List<LibroFisico> librosFisicosReservados) {
        this.librosFisicosReservados = librosFisicosReservados;
    }

    public List<LibroFisico> getLibrosFisicosTotales() {
        return librosFisicosTotales;
    }

    public void setLibrosFisicosTotales() {
        List<LibroFisico> nuevaLista = new ArrayList<>();
        nuevaLista.addAll(librosFisicosAgotados);
        nuevaLista.addAll(librosFisicosDisponibles);
        nuevaLista.addAll(librosFisicosReservados);
        this.librosFisicosTotales = nuevaLista;
    }
}
