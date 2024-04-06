package omega.sgb.dominio;

public class LibroFisico {
    private Integer id;
    private Integer estado;
    private String ubicacion;
    private Integer libroVirtualId;
    private Integer prestamoId;

    //Métodos constructores
    public LibroFisico() {
    }

    public LibroFisico(Integer id, Integer estado, String ubicacion, Integer libroVirtualId, Integer prestamoId) {
        this.id = id;
        this.estado = estado;
        this.ubicacion = ubicacion;
        this.libroVirtualId = libroVirtualId;
        this.prestamoId = prestamoId;
    }

    //Métodos de acceso
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getLibroVirtualId() {
        return libroVirtualId;
    }

    public void setLibroVirtualId(Integer libroVirtualId) {
        this.libroVirtualId = libroVirtualId;
    }

    public Integer getPrestamoId() {
        return prestamoId;
    }

    public void setPrestamoId(Integer prestamoId) {
        this.prestamoId = prestamoId;
    }
}
