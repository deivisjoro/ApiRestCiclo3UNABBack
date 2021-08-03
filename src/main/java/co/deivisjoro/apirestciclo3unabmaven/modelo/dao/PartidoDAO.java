package co.deivisjoro.apirestciclo3unabmaven.modelo.dao;

import co.deivisjoro.apirestciclo3unabmaven.bd.Conexion;
import co.deivisjoro.apirestciclo3unabmaven.modelo.entidad.Partido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PartidoDAO {

    private Connection connection;
    
    public PartidoDAO(Conexion conexion){  
        this.connection = conexion.getConexion();        
    }
     
    public ArrayList<Partido> getPartidos(){
        ArrayList<Partido> partidos = new ArrayList<>();            
        try {
            String sql = "SELECT p.*, u.nombre AS usuario_nombre, e1.nombre as local_nombre,  e2.nombre as visitante_nombre FROM partidos AS p, usuarios AS u, equipos AS e1, equipos AS e2 WHERE u.id = p.usuario AND p.local = e1.id AND p.visitante = e2.id ORDER BY p.fecha DESC";
            PreparedStatement pst;
            pst = this.connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Partido partido = new Partido();
                partido.setId(rs.getInt("id"));
                partido.setUsuario(rs.getInt("usuario"));
                partido.setUsuarioNombre(rs.getString("usuario_nombre"));
                partido.setLocal(rs.getInt("local"));
                partido.setLocalNombre(rs.getString("local_nombre"));
                partido.setVisitante(rs.getInt("visitante"));
                partido.setVisitanteNombre(rs.getString("visitante_nombre"));
                partido.setFecha(rs.getString("fecha"));
                partido.setGolesLocal(rs.getInt("goles_local"));
                partido.setGolesVisitante(rs.getInt("goles_visitante"));
                partidos.add(partido);

            }
            rs.close();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
        }        
        return partidos;    
    }
    
    public Partido getPartido(int id){    
        Partido p = new Partido();
        for(Partido partido: this.getPartidos()){
            if(partido.getId()==id){
                return partido;
            }
        }        
        return p;
    }
    
    public Partido addPartido(Partido partido){
        try{
            String sql = "INSERT INTO partidos (usuario, local, visitante, fecha, goles_local, goles_visitante) VALUES (?, ?, ?, ?, ?, ?)";        
            PreparedStatement pst = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, partido.getUsuario());
            pst.setInt(2, partido.getLocal());
            pst.setInt(3, partido.getVisitante());
            pst.setString(4, partido.getFecha());
            pst.setInt(5, partido.getGolesLocal());
            pst.setInt(6, partido.getGolesVisitante());
            int result = pst.executeUpdate();        
            ResultSet rs = pst.getGeneratedKeys();
            if(result>0){            
                if (rs.next()) {
                    int id = rs.getInt(1);
                    partido.setId(id);
                }
            }        

            rs.close();
            pst.close();
        }
        catch(SQLException e){
        }
        
        return partido;
    }
    
    public Partido updatePartido(Partido partido){
        Partido p = new Partido();
        try{
            String sql = "UPDATE partidos SET usuario=?, local=?, visitante=?, fecha=?, goles_local=?, goles_visitante=? WHERE id=?";        
            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setInt(1, partido.getUsuario());
            pst.setInt(2, partido.getLocal());
            pst.setInt(3, partido.getVisitante());
            pst.setString(4, partido.getFecha());
            pst.setInt(5, partido.getGolesLocal());
            pst.setInt(6, partido.getGolesVisitante());
            pst.setInt(7, partido.getId());
            int result = pst.executeUpdate();
            pst.close();

            if(result>0){
                return partido;
            }
        }
        catch(SQLException e){
        
        }        
        return p;
    }
    
    public boolean deletePartido(int id){
        try{
            String sql = "DELETE FROM partidos WHERE id=?";        
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

}
