package co.deivisjoro.apirestciclo3unabmaven.recurso;

import co.deivisjoro.apirestciclo3unabmaven.bd.Conexion;
import co.deivisjoro.apirestciclo3unabmaven.modelo.dao.UsuarioDAO;
import co.deivisjoro.apirestciclo3unabmaven.modelo.entidad.Usuario;
import co.deivisjoro.apirestciclo3unabmaven.recurso.filtro.Autorizacion;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.ArrayList;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/usuarios")
public class UsuarioRecurso {
    
    Conexion conexion = new Conexion();    
    UsuarioDAO servicio = new UsuarioDAO(conexion);
    /*
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Usuario> getUsuarios(){        
        ArrayList<Usuario> usuarios = servicio.getUsuarios();                
        return usuarios;
    } */
    
    /*
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUsuario(@PathParam("id") int id){        
        Usuario usuario = servicio.getUsuario(id);                
        return usuario;
    } */
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario addUsuario(Usuario usuario){
        return servicio.addUsuario(usuario);
    }
    
    /*
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario checkLogin(Usuario usuario){
        return servicio.checkLogin(usuario);
    }*/
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkLogin(Usuario usuario){
        
        Usuario u = servicio.checkLogin(usuario); 
        
        String hash = "";
        
        long tiempo = System.currentTimeMillis();
        
        if(u.getId()!=0){
            //String KEY = "!#12DE!1Zsddd3";
            hash = Jwts.builder()
                       .signWith(SignatureAlgorithm.HS256, Autorizacion.KEY)
                       .setSubject(u.getNombre())
                       .setIssuedAt(new Date(tiempo))
                       .setExpiration(new Date(tiempo + 900000))
                       .claim("username", u.getUsername())
                       .claim("correo", u.getCorreo())
                       .compact();
        }
        
        JsonObject json = Json.createObjectBuilder()
                              .add("id", u.getId())
                              .add("nombre", u.getNombre())
                              .add("correo", u.getCorreo())
                              .add("nombre", u.getNombre())
                              .add("username", u.getUsername())
                              .add("hash", hash)
                              .build();
        
        return Response.status(Response.Status.CREATED).entity(json).build();
              
    }
    /*
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteUsuario(@PathParam("id") int id){
        boolean result = servicio.deleteUsuario(id);
        String respuesta = result ? "OK" : "ERROR";
        return respuesta;
    }
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateUsuario(@PathParam("id") int id, Usuario usuario){
        boolean result = servicio.updateUsuario(usuario);
        String respuesta = result ? "OK" : "ERROR";
        return respuesta;
    }*/

}
