/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appserver.grupo5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.ws.rs.core.Response;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;



/**
 *
 * @author guillermoandres
 */
public class http {
    
    public enum Method{
    GET,POST,PUT,DELETE
}
    public static Response GET(String url){
        return Request(Method.GET,url,null,null);
    }
    
    public static Response POST(String url, String content, String contentType){
        return Request(Method.POST,url,content,contentType);
    }
        
    public static Response PUT(String url, String content, String contentType){
        return Request(Method.PUT,url,content,contentType);
    }
            
    public static Response DELETE(String url, String content, String contentType){
        return Request(Method.DELETE,url,content,contentType);
    }
    
    public static Response Request(Method method, String url, String content, String contentType){
        Response response;
        try {
            HttpClient client = new DefaultHttpClient();
            HttpUriRequest request;
            switch (method){
                case GET:
                    request = new HttpGet(url);
                    break;
                case POST:
                    request = new HttpPost(url);
                    ((HttpPost) request).setEntity(new StringEntity(content));
                    break;
                case PUT:
                    request = new HttpPut(url);
                    ((HttpPut) request).setEntity(new StringEntity(content));
                    break;
                case DELETE:
                    request = new HttpDeleteWithBody(url);
                    ((HttpDeleteWithBody) request).setEntity(new StringEntity(content));
                    break;
                default:
                    request = new HttpGet(url);
                    break;
            }
            if (method != Method.GET){
                request.setHeader("Content-type", contentType);
            }
            response = executeRequest(client,request);
            }catch(Exception e){
                response = Response.status(400).entity(e.getMessage()).build();
            }
        return response;
    }

    private static Response executeRequest(HttpClient client, HttpUriRequest request) throws Exception{
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader(
               new InputStreamReader(response.getEntity().getContent()));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        String entity = result.toString();
        int status = response.getStatusLine().getStatusCode();
        String contentType = response.getFirstHeader("Content-type").getValue();
        return Response.status(status).entity(entity).header("Content-type", contentType).build();
    }
    
    
}