/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appserver.grupo5;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author guillermoandres
 */
@Path("atenea")
public class OCResource {

    @Context
    private UriInfo context;

    public OCResource() {
    }

    private String url = "http://chiri.ing.puc.cl/atenea"; // en produccion es  http://localhost:83
    
    @GET
    @Path("/")
    public Response sistema() {
        return http.GET(url + "/");
    }
    
    @PUT
    @Path("/crear")
    public Response crearOC(@HeaderParam("Content-type") String contentType,String content) {
        return http.PUT( url + "/crear", content , contentType);
    }
    
    @POST
    @Path("/recepcionar/{id}")
    public Response recepcionarOC(@HeaderParam("Content-type") String contentType,@PathParam("id") String id, String content) {
        return http.POST( url + "/recepcionar/" + id, content , contentType);
    }
    
    @POST
    @Path("/rechazar/{id}")
    public Response rechazarOC(@HeaderParam("Content-type") String contentType,@PathParam("id") String id, String content) {
        return http.POST( url + "/rechazar/" + id, content , contentType);
    }
    
    @DELETE
    @Path("/anular/{id}")
    public Response anularOC(@HeaderParam("Content-type") String contentType,@PathParam("id") String id, String content) {
        return http.DELETE( url + "/anular/" + id, content, contentType );
    }
    
    @POST
    @Path("/despachar/{id}")
    public Response despacharOC(@HeaderParam("Content-type") String contentType,@PathParam("id") String id, String content) {
        return http.POST(url + "/despachar/" + id, content, contentType);
    }
    
    @GET
    @Path("/obtener/{id}")
    public Response obtenerOC(@PathParam("id") String id) {
        return http.GET( url + "/obtener/" + id );
    }
    
 
}
