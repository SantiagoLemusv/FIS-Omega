package omega.sgb.dominio;

public class LibroFisico {
    //Atributos de instancia
    private Integer id;
    private String ubicacion;
    private String numeroClasificacion;
    private LibroVirtual libroVirtual;
    private Integer estadoLibroFisicoId;//Prestado (0), disponible (1)

    //Métodos constructores
    public LibroFisico() {
    }

    public LibroFisico(Integer id, String ubicacion, String numeroClasificacion, LibroVirtual libroVirtual, Integer estadoLibroFisicoId) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.numeroClasificacion = numeroClasificacion;
        this.libroVirtual = libroVirtual;
        this.estadoLibroFisicoId = estadoLibroFisicoId;
    }

    public LibroFisico(String ubicacion, String numeroClasificacion, LibroVirtual libroVirtual, Integer estadoLibroFisicoId) {
        this.ubicacion = ubicacion;
        this.numeroClasificacion = numeroClasificacion;
        this.libroVirtual = libroVirtual;
        this.estadoLibroFisicoId = estadoLibroFisicoId;
    }

    //Métodos de acceso
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getNumeroClasificacion() {
        return numeroClasificacion;
    }

    public void setNumeroClasificacion(String numeroClasificacion) {
        this.numeroClasificacion = numeroClasificacion;
    }

    public LibroVirtual getLibroVirtual() {
        return libroVirtual;
    }

    public void setLibroVirtual(LibroVirtual libroVirtual) {
        this.libroVirtual = libroVirtual;
    }

    public Integer getEstadoLibroFisicoId() {
        return estadoLibroFisicoId;
    }

    public void setEstadoLibroFisicoId(Integer estadoLibroFisicoId) {
        this.estadoLibroFisicoId = estadoLibroFisicoId;
    }
}
