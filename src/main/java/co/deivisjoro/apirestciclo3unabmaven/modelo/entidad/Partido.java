package co.deivisjoro.apirestciclo3unabmaven.modelo.entidad;

public class Partido {

    private int id;
    private int usuario;
    private String usuarioNombre;
    private int local;
    private String localNombre;
    private int visitante;
    private String visitanteNombre;
    private String fecha;
    private int golesLocal;
    private int golesVisitante;

    public Partido() {
    }

    public Partido(int id, int usuario, String usuarioNombre, int local, String localNombre, int visitante, String visitanteNombre, String fecha, int golesLocal, int golesVisitante) {
        this.id = id;
        this.usuario = usuario;
        this.usuarioNombre = usuarioNombre;
        this.local = local;
        this.localNombre = localNombre;
        this.visitante = visitante;
        this.visitanteNombre = visitanteNombre;
        this.fecha = fecha;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public int getLocal() {
        return local;
    }

    public void setLocal(int local) {
        this.local = local;
    }

    public String getLocalNombre() {
        return localNombre;
    }

    public void setLocalNombre(String localNombre) {
        this.localNombre = localNombre;
    }

    public int getVisitante() {
        return visitante;
    }

    public void setVisitante(int visitante) {
        this.visitante = visitante;
    }

    public String getVisitanteNombre() {
        return visitanteNombre;
    }

    public void setVisitanteNombre(String visitanteNombre) {
        this.visitanteNombre = visitanteNombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesVisitante(int golesVisitante) {
        this.golesVisitante = golesVisitante;
    }

}
