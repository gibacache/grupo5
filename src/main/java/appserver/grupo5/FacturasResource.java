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
 * @author Matias Vergara
 */
@Path("zeuz")
public class FacturasResource {

    @Context
    private UriInfo context;

    //private String url = "http://chiri.ing.puc.cl/zeuz"; // en produccion es  http://localhost:83
    private String url = "http://localhost:83";
    /**
     * Creates a new instance of FacturasResource
     */
    public FacturasResource() {
    }
    
    @PUT
    @Path("/") //emitir factura asociada a una OC.  parametro ID de la OC debe ir en el json (content)
    public Response emitirFacturas(@HeaderParam("Content-type") String contentType,String content) {
        return http.PUT( url , content , contentType);
        
// <editor-fold defaultstate="collapsed" desc=" Formato el JSON retornado ">
        /* 
        {
            "__v": 0,
            "created_at": "2015-05-25T21:50:10.789Z",
            "updated_at": "2015-05-25T21:50:10.789Z",
            "cliente": "5",
            "proveedor": "4",
            "bruto": 336,
            "iva": 64,
            "total": 400,
            "oc": "5563933be0949e0300344fda",
            "_id": "55639912e0949e0300344fdd",
            "estado": "pendiente"
        }
       */ // Formato del JSON retornado


// </editor-fold>
       
    }
    
    @GET
    @Path("/{id}") //obtener factura asociada a un ID de factura
    public Response obtenerFactura(@PathParam("id") String id) {   
        return http.GET( url + "/" + id );
        
// <editor-fold  desc=" Formato del JSON retornado ">
        /*
        {
            "_id": "5563960fe0949e0300344fdc",
            "created_at": "2015-05-25T21:37:19.990Z",
            "updated_at": "2015-05-25T21:37:19.990Z",
            "cliente": "5",
            "proveedor": "4",
            "bruto": 336,
            "iva": 64,
            "total": 400,
            "oc": "5563933be0949e0300344fda",
            "estado": "pendiente",
            "__v": 0
        }
        */  // Formato del JSON retornado


// </editor-fold>
        
    }
    
    @POST
    @Path("/pay") //mandar id de la factura en el json (content)
    public Response pagarFactura(@HeaderParam("Content-type") String contentType, String content) {
        return http.POST(url + "/pay/", content, contentType);
        
// <editor-fold defaultstate="collapsed" desc=" Formato del JSON retornado ">
        /*
        {
            "_id": "5563960fe0949e0300344fdc",
            "created_at": "2015-05-25T21:37:19.990Z",
            "updated_at": "2015-05-25T21:57:47.480Z",
            "cliente": "5",
            "proveedor": "4",
            "bruto": 336,
            "iva": 64,
            "total": 400,
            "oc": "5563933be0949e0300344fda",
            "estado": "pagada",
            "__v": 0
        }
                */

// </editor-fold>
        
    }
    
    @POST
    @Path("/reject") //mandar id de la factura y el motivo del rechazo en el json (content). solamente se pueden rechazar facturas en estado pendiente
    public Response rechazarFactura(@HeaderParam("Content-type") String contentType, String content) {
        return http.POST(url + "/reject/", content, contentType);
        
// <editor-fold defaultstate="collapsed" desc=" Formato del JSON retornado ">
        /*
        {
            "_id": "55639d47e0949e0300344fde",
            "created_at": "2015-05-25T22:08:07.937Z",
            "updated_at": "2015-05-25T22:08:21.910Z",
            "cliente": "5",
            "proveedor": "4",
            "bruto": 336,
            "iva": 64,
            "total": 400,
            "oc": "5563933be0949e0300344fda",
            "estado": "rechazada",
            "__v": 0,
            "rechazo": "motivo por el cual rechazo"
        }
        */

// </editor-fold>
        
    }
    
    @POST
    @Path("/cancel") //mandar id de la factura y el motivo de la anulación en el json (content). solamente se pueden rechazar facturas en estado pendiente
    public Response anularFactura(@HeaderParam("Content-type") String contentType, String content) {
        return http.POST(url + "/cancel/", content, contentType);
        
// <editor-fold defaultstate="collapsed" desc=" Formato del JSON retornado ">
        /*
        {
            "_id": "55639dcee0949e0300344fe0",
            "created_at": "2015-05-25T22:10:22.023Z",
            "updated_at": "2015-05-25T22:10:36.381Z",
            "cliente": "5",
            "proveedor": "4",
            "bruto": 336,
            "iva": 64,
            "total": 400,
            "oc": "5563933be0949e0300344fda",
            "estado": "anulada",
            "__v": 0,
            "anulacion": "motivo por el cual anulo"
        }
        */

// </editor-fold>
        
    }
    
}
