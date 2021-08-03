package co.deivisjoro.apirestciclo3unabmaven.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private Connection conexion;

    public Conexion() {
        this.conexion = null;
    }

    public Connection getConexion(){
      
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conexion = DriverManager.getConnection("jdbc:mysql://localhost/ciclo3unab", "root", "");
        } catch (ClassNotFoundException ex) {
            
        } catch (SQLException ex) {
            
        }
       
        return conexion;
    }

}
