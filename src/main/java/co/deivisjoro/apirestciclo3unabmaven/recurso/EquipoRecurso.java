package co.deivisjoro.apirestciclo3unabmaven.recurso;

import co.deivisjoro.apirestciclo3unabmaven.bd.Conexion;
import co.deivisjoro.apirestciclo3unabmaven.modelo.dao.EquipoDAO;
import co.deivisjoro.apirestciclo3unabmaven.modelo.entidad.Equipo;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/equipos")
public class EquipoRecurso {
    
    Conexion conexion = new Conexion();    
    EquipoDAO servicio = new EquipoDAO(conexion);
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Equipo> getEquipos(){        
        ArrayList<Equipo> equipos = servicio.getEquipos();                
        return equipos;
    }

}
