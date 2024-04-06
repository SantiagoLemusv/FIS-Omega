package omega.sgb.dominio;

public class Persona {
    private Integer id;
    private String nombre;
    private String contrasenia;
    private Integer cedula;
    private Integer tipoPersonaId;

    //Constructores
    public Persona() {
    }

    public Persona(Integer id, String nombre, String contrasenia, Integer cedula, Integer tipoPersonaId) {
        this.id = id;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.cedula = cedula;
        this.tipoPersonaId = tipoPersonaId;
    }


    //Métodos de acceso
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public Integer getTipoPersonaId() {
        return tipoPersonaId;
    }

    public void setTipoPersonaId(Integer tipoPersonaId) {
        this.tipoPersonaId = tipoPersonaId;
    }
}

