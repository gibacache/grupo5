/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appserver.grupo5;

import cl.uc.integracion.banco.servicios.Cartola;
import cl.uc.integracion.banco.servicios.CrearTransaccion;
import cl.uc.integracion.banco.servicios.Cuenta;
import cl.uc.integracion.banco.servicios.CuentaBancoArray;
import cl.uc.integracion.banco.servicios.Cuenta_Service;
import cl.uc.integracion.banco.servicios.GetCartola;
import cl.uc.integracion.banco.servicios.Transaccion;
import cl.uc.integracion.banco.servicios.TransaccionArray;
import cl.uc.integracion.banco.servicios.Trx;
import cl.uc.integracion.banco.servicios.Trx_Service;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

 

/**
 * REST Web Service
 *
 * @author Matias Vergara
 */
@Path("apolo")
public class BancoResource {

    @Context
    private UriInfo context;
    
    

    /**
     * Creates a new instance of BancoResource
     */
    public BancoResource() {
    }

    
 
    @PUT
    @Path("/trx")
    public Response transferir(@HeaderParam("Content-type") String contentType,String content, 
            @QueryParam("monto") int monto, @QueryParam("origen") String origen ,@QueryParam("destino") String destino) {
        
        
        try { // Call Web Service Operation
            Trx_Service service = new Trx_Service();
            Trx port = service.getTrxPort();
            // TODO initialize WS operation arguments here
            CrearTransaccion trx = new CrearTransaccion();
            trx.setMonto(monto);
            trx.setOrigen(origen);
            trx.setDestino(destino);       
            
            // TODO process result here
            Transaccion result = port.crearTransaccion(trx);
            
            System.out.println("Result = "+result);
            
            return Response.status(200).entity(result).build();
            
        } catch (Exception ex) {
            // TODO handle custom exceptions here
            return Response.status(400).entity(ex.getMessage()).build();
        }

    }
    
    @GET
    @Path("/trx/{id}")
    public Response obtenerTransaccion(@PathParam("id") String id){
        
        try { 
            // Call Web Service Operation
            System.out.println(id);
            Trx_Service service = new Trx_Service();
            Trx port = service.getTrxPort();
            // TODO initialize WS operation arguments here
            System.out.println("5");
            //java.lang.String _id = id;
            System.out.println("10");
            // TODO process result here
            TransaccionArray result = port.getTransaccion(id);
            System.out.println("20");
            System.out.println("Result = "+result);
            
            return Response.status(200).entity(result).build();
            
        } catch (Exception ex) {
            // TODO handle custom exceptions here
            return Response.status(400).entity(ex.getMessage()).build();
        }
        
    }
    
    @POST
    @Path("/cartola/")
    public Response obtenerCartola(@HeaderParam("Content-type") String contentType, String content,
     @QueryParam("fechaInicio") float fechaInicio, @QueryParam("fechaFin") float fechaFin, 
     @QueryParam("id") String id, @QueryParam("limit") int limit) {
        
        
        try { 
            // Call Web Service Operation
            Cuenta_Service service = new Cuenta_Service();
            Cuenta port = service.getCuentaPort();
            // TODO initialize WS operation arguments here
            GetCartola cartola = new GetCartola();
            cartola.setInicio(fechaInicio);
            cartola.setFin(fechaFin);
            cartola.setId(id);
            cartola.setLimit(limit);
            // TODO process result here
            Cartola result = port.getCartola(cartola);
            
            System.out.println("Result = "+result);
            
            return Response.status(200).entity(result).build();
            
        } catch (Exception ex) {
            // TODO handle custom exceptions here
            return Response.status(400).entity(ex.getMessage()).build();
        }

        
        
        
    
    }
    
    @GET
    @Path("/banco/cuenta/{id}")
    public Response obtenerCuenta(@PathParam("id") String id){
        
        try { // Call Web Service Operation
            Cuenta_Service service = new Cuenta_Service();
            Cuenta port = service.getCuentaPort();
            // TODO initialize WS operation arguments here
            
            // TODO process result here
            CuentaBancoArray result = port.getCuenta(id);
            
            System.out.println("Result = "+result);
            
            return Response.status(200).entity(result).build();
            
        } catch (Exception ex) {
            // TODO handle custom exceptions here
            return Response.status(400).entity(ex.getMessage()).build();
        }

    }
    
    
    
    
    
    
    
    
    
    
    
    /**
     * Retrieves representation of an instance of appserver.grupo5.BancoResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of BancoResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
