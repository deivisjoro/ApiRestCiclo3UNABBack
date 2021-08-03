package co.deivisjoro.apirestciclo3unabmaven.modelo.entidad;

public class Usuario {

    private int id;
    private String nombre;
    private String correo;
    private String username;
    private String password;

    public Usuario() {
        this.id = 0;
        this.nombre = "";
        this.correo = "";
        this.username = "";
        this.password = "";
    }

    public Usuario(int id, String nombre, String correo, String username, String password) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
