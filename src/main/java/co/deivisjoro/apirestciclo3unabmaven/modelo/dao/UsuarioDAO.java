package co.deivisjoro.apirestciclo3unabmaven.modelo.dao;

import co.deivisjoro.apirestciclo3unabmaven.bd.Conexion;
import co.deivisjoro.apirestciclo3unabmaven.modelo.entidad.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsuarioDAO {

    private Connection connection;
    
    public UsuarioDAO(Conexion conexion){  
        this.connection = conexion.getConexion();        
    }
     
    public ArrayList<Usuario> getUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<>();            
        try {
            String sql = "SELECT * FROM usuarios";
            PreparedStatement pst;
            pst = this.connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));

                usuarios.add(usuario);

            }
            rs.close();
            pst.close();
        } catch (SQLException ex) {
        }        
        return usuarios;    
    }
    
    public Usuario getUsuario(int id){        
        for(Usuario usuario: this.getUsuarios()){
            if(usuario.getId()==id){
                return usuario;
            }
        }        
        return null;
    }
    
    public Usuario addUsuario(Usuario usuario){
        try{
            String sql = "INSERT INTO usuarios (nombre, correo, username, password) VALUES (?, ?, ?, ?)";        
            PreparedStatement pst = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, usuario.getNombre());
            pst.setString(2, usuario.getCorreo());
            pst.setString(3, usuario.getUsername());
            pst.setString(4, usuario.getPassword());
            int result = pst.executeUpdate();        
            ResultSet rs = pst.getGeneratedKeys();
            if(result>0){            
                if (rs.next()) {
                    int id = rs.getInt(1);
                    usuario.setId(id);
                }
            }        

            rs.close();
            pst.close();
        }
        catch(SQLException e){
        }
        
        return usuario;
    }
    
    public boolean updateUsuario(Usuario usuario){
        try{
            String sql = "UPDATE usuarios SET nombre=?, correo=?, username=?, password=? WHERE id=?";        
            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setString(1, usuario.getNombre());
            pst.setString(2, usuario.getCorreo());
            pst.setString(3, usuario.getUsername());
            pst.setString(4, usuario.getPassword());
            pst.setInt(5, usuario.getId());
            int result = pst.executeUpdate();
            pst.close();

            if(result>0){
                return true;
            }
        }
        catch(SQLException e){
        
        }        
        return false;
    }
    
    public boolean deleteUsuario(int id){
        try{
            String sql = "DELETE FROM usuarios WHERE id=?";        
            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setInt(1, id);
            int result = pst.executeUpdate();
            pst.close();

            if(result>0){
                return true;
            }
        }
        catch(SQLException e){
        }
        
        return false;
    
    }
    
    public Usuario checkLogin(Usuario usuario){
        Usuario result = new Usuario();           
        try {
            String sql = "SELECT * FROM usuarios WHERE username=? AND password=?";
            PreparedStatement pst;
            pst = this.connection.prepareStatement(sql);
            pst.setString(1, usuario.getUsername());
            pst.setString(2, usuario.getPassword());
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));

                result = usuario;

            }
            rs.close();
            pst.close();
        } catch (SQLException ex) {
        }        
        return result;    
    }

}
