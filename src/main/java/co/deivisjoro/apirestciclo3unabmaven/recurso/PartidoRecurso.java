package co.deivisjoro.apirestciclo3unabmaven.recurso;

import co.deivisjoro.apirestciclo3unabmaven.bd.Conexion;
import co.deivisjoro.apirestciclo3unabmaven.modelo.dao.PartidoDAO;
import co.deivisjoro.apirestciclo3unabmaven.modelo.entidad.Partido;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/partidos")
public class PartidoRecurso {

    Conexion conexion = new Conexion();    
    PartidoDAO servicio = new PartidoDAO(conexion);
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Partido> getPartidos(){        
        ArrayList<Partido> partidos = servicio.getPartidos();                
        return partidos;
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Partido getPartido(@PathParam("id") int id){        
        Partido partido = servicio.getPartido(id);                
        return partido;
    } 
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Partido addPartido(Partido partido){
        return servicio.addPartido(partido);
    }
  
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Partido updatePartido(@PathParam("id") int id, Partido partido){
        Partido p = servicio.updatePartido(partido);
        return p;
    }

}
