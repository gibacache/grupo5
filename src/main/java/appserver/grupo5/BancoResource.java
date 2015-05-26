/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appserver.grupo5;

import cl.uc.integracion.banco.servicios.Cartola;
import cl.uc.integracion.banco.servicios.CrearTransaccion;
import cl.uc.integracion.banco.servicios.Cuenta;
import cl.uc.integracion.banco.servicios.CuentaBanco;
import cl.uc.integracion.banco.servicios.CuentaBancoArray;
import cl.uc.integracion.banco.servicios.Cuenta_Service;
import cl.uc.integracion.banco.servicios.GetCartola;
import cl.uc.integracion.banco.servicios.Transaccion;
import cl.uc.integracion.banco.servicios.TransaccionArray;
import cl.uc.integracion.banco.servicios.Trx;
import cl.uc.integracion.banco.servicios.Trx_Service;
import java.util.Iterator;
import java.util.List;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;


 

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
    @Path("/trx")//funciona
    @Consumes("application/json")
    public Response transferir(@HeaderParam("Content-type") String contentType,JSONObject inputJsonObj, @QueryParam("monto") int monto, @QueryParam("origen") String origen ,@QueryParam("destino") String destino) {
    //@PUT
    //@Path("/trx")
    //public Response transferir(CreateTransaction createTransaction) {   
        
        try { // Call Web Service Operation
            Trx_Service service = new Trx_Service();
            Trx port = service.getTrxPort();
            // TODO initialize WS operation arguments here
            CrearTransaccion trx = new CrearTransaccion();
            System.out.println(monto);
            System.out.println(origen);
            System.out.println(destino);
            trx.setMonto(monto);
            trx.setOrigen(origen);
            trx.setDestino(destino);       
            System.out.println("paso1");
            System.out.println(trx.getDestino());
            // TODO process result here
            Transaccion result = port.crearTransaccion(trx);
            
            System.out.println("paso2");
            
            String id = result.getId();
            String _origen = result.getOrigen();
            String _destino = result.getDestino();
            int _monto = result.getMonto();
            String updatedAt = result.getUpdatedAt();
            String __v = result.getV();
            
            String json = "{\"_id\":\"" + id + "\","
                    + "\"origen\":\"" + _origen + "\","
                    + "\"destino\":\"" + _destino + "\","
                    + "\"monto\":" + _monto + ","
                    + "\"updatedAt\":\"" + updatedAt + "\","
                    + "\"__v\":\"" + __v + "\""
                    +"}";
            
            System.out.println("Result = ");
            
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
            
        } catch (Exception ex) {
            // TODO handle custom exceptions here
            return Response.status(400).entity(ex.getMessage()).build();
        }

    }
    
    @GET
    @Path("/trx/{id}")//funiona
    public Response obtenerTransaccion(@PathParam("id") String id){
        
        try { 
            // Call Web Service Operation
           
            Trx_Service service = new Trx_Service();
            Trx port = service.getTrxPort();
            // TODO initialize WS operation arguments here
             System.out.println(id);
            // TODO process result here
            TransaccionArray result1 = port.getTransaccion(id);
            Transaccion result = result1.getItem().get(0);

            System.out.println("20");

            
            String json = "{\"_id\":\"" + result.getId() + "\","
                    + "\"origen\":\"" + result.getOrigen() + "\","
                    + "\"destino\":\"" + result.getDestino() + "\","
                    + "\"monto\":" + result.getMonto() + ","
                    + "\"updatedAt\":\"" + result.getUpdatedAt() + "\","
                    + "\"_v\":\"" + result.getV() + "\""
                    +"}";
            
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
            
        } catch (Exception ex) {
            // TODO handle custom exceptions here
            return Response.status(400).entity(ex.getMessage()).build();
        }
        
    }
    
    @POST
    @Path("/cartola/")// funciona
    public Response obtenerCartola(@HeaderParam("Content-type") String contentType, String content, @QueryParam("fechaInicio") float fechaInicio, @QueryParam("fechaFin") float fechaFin, @QueryParam("id") String id, @QueryParam("limit") int limit) {
        
        
        try { 
            // Call Web Service Operation
            Cuenta_Service service = new Cuenta_Service();
            Cuenta port = service.getCuentaPort();
            // TODO initialize WS operation arguments here
            GetCartola cartola = new GetCartola();
            cartola.setInicio(fechaInicio);
            cartola.setFin(fechaFin);
            cartola.setId(id);
            
            
            if(limit!= -1){
                cartola.setLimit(limit);
                System.out.println(limit);
            }
            //
            // TODO process result here
            System.out.println("paso1");
            Cartola result = port.getCartola(cartola);
            
            System.out.println("paso");
            //System.out.println("Result = "+result);
            
            
            List<Transaccion> trxs = result.getTransacciones();
            Iterator<Transaccion> i = trxs.iterator();
            
            
            String json ="\"transacciones\": [";
            while(i.hasNext()){
                Transaccion temp = i.next();
                
                json = json + "{\"_id\":\"" + temp.getId() + "\","
                    + "\"origen\":\"" + temp.getOrigen() + "\","
                    + "\"destino\":\"" + temp.getDestino() + "\","
                    + "\"monto\":" + temp.getMonto() + ","
                    + "\"updatedAt\":\"" + temp.getUpdatedAt() + "\","
                    + "\"__v\":\"" + temp.getV() + "\""
                    +"},";
                
            }
            if(json.charAt(json.length()-1)==',')
                json = json.substring(0, json.length()-1);
            json = json+"]";
            
            
            
            
            //return Response.status(200).entity(result).build();
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
            
        } catch (Exception ex) {
            // TODO handle custom exceptions here
            return Response.status(400).entity(ex.getMessage()).build();
        }

        
        
        
    
    }
    
    @GET 
    @Path("/banco/cuenta/{id}") // funciona
    public Response obtenerCuenta(@PathParam("id") String id){
        
        try { // Call Web Service Operation
            Cuenta_Service service = new Cuenta_Service();
            Cuenta port = service.getCuentaPort();
            // TODO initialize WS operation arguments here
            
            // TODO process result here
            CuentaBancoArray result = port.getCuenta(id);
            System.out.println(id);
            
            List<CuentaBanco> accountList = result.getItem();
            
            CuentaBanco cuenta = accountList.get(0);
            
            String json = "{\"id\":\"" + cuenta.getId() + "\","
                    + "\"grupo\":\"" + cuenta.getGrupo() + "\","
                    + "\"saldo\":" + cuenta.getSaldo() + "}";
            
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
            
        } catch (Exception ex) {
            // TODO handle custom exceptions here
            return Response.status(400).entity(ex.getMessage()).build();
        }

    }

}
