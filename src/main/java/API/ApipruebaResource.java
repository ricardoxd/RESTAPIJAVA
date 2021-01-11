/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import com.google.gson.Gson;
import com.prueba2.prueba2.Hora;
import com.prueba2.prueba2.Respuesta;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author ricardo
 */
@Path("apiprueba")
public class ApipruebaResource {

    @Context
    private UriInfo context;


    /**
     * Creates a new instance of ApipruebaResource
     */
    public ApipruebaResource() {
    }

    /**
     * Retrieves representation of an instance of API.ApipruebaResource
     * @param dato1
     * @param dato2
     * @return an instance of java.lang.String
     */
    @POST
    @Path("/utc")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@FormParam("dato1") String dato1,@FormParam("dato2") String dato2) {
       Time t = Time.valueOf(dato1);
       long ts = t.getTime();
       Date localTime = new Date(ts);
       
       Calendar cal = GregorianCalendar.getInstance();
       cal.setTime( localTime );
       String[] zone = dato2.split(":");
       int horas = Integer.valueOf(zone[0]);
       int minutos = 0;
       if(zone.length>1){
        minutos = Integer.valueOf(zone[1]);
       }
       if(horas>0) {
        minutos = minutos*-1;
       }
        cal.add( GregorianCalendar.HOUR, horas*-1 );
        cal.add( GregorianCalendar.MINUTE, minutos );
       
       String format2 = "HH:mm:ss";
       SimpleDateFormat sdf3 = new SimpleDateFormat(format2);
       
       Respuesta respuesta = new Respuesta();
       Hora hora = new Hora();
       hora.setTimezone("UTC");
       hora.setTime(sdf3.format( cal.getTime() ));
       respuesta.setResponse(hora);
       Gson gson = new Gson();
       String stringjson = gson.toJson(respuesta);
       return stringjson;
    }

    /**
     * PUT method for updating or creating an instance of ApipruebaResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
