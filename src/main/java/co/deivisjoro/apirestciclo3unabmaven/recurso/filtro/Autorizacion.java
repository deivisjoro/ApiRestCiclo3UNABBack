package co.deivisjoro.apirestciclo3unabmaven.recurso.filtro;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.MacProvider;
import java.io.IOException;
import java.security.Key;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
public class Autorizacion implements ContainerRequestFilter{
    
    public static final Key KEY = MacProvider.generateKey();

    @Override
    public void filter(ContainerRequestContext request) throws IOException {
        
        String url = request.getUriInfo().getAbsolutePath().toString();
        if(url.contains("/api/usuarios/login") || url.contains("/api/usuarios")){
            return;
        }
        else{
            String hash = request.getHeaderString("Authorization");
            
            if(hash==null || hash.trim().equals("")){
                JsonObject json = Json.createObjectBuilder()
                                      .add("autorizacion","NO")
                                      .build();
                request.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                                          .entity(json).type(MediaType.APPLICATION_JSON).build());
                return;
                
            }
            
            // Valida el token utilizando la cadena secreta
            try{
                Jws<Claims> claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(hash);
                //System.out.println(claims.getBody().getSubject());
            }
            catch(MalformedJwtException e){
                JsonObject json = Json.createObjectBuilder()
                                      .add("autorizacion","NO")
                                      .build();
                
                request.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                                          .entity(json).type(MediaType.APPLICATION_JSON).build());
                
                return;
            }
            catch(SignatureException e){
                JsonObject json = Json.createObjectBuilder()
                                      .add("autorizacion","NO")
                                      .build();
                
                request.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                                          .entity(json).type(MediaType.APPLICATION_JSON).build());
                
                return;
            }
            catch(ExpiredJwtException e){
                JsonObject json = Json.createObjectBuilder()
                                      .add("autorizacion","NO")
                                      .build();
                
                request.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                                          .entity(json).type(MediaType.APPLICATION_JSON).build());
                
                return;
            }
                        
            /*if(!hash.equals("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c")){
                JsonObject json = Json.createObjectBuilder()
                                      .add("autorizacion","NO")
                                      .build();
                
                request.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                                          .entity(json).type(MediaType.APPLICATION_JSON).build());
                
                return;
            }*/
        }        
    }
    
}
