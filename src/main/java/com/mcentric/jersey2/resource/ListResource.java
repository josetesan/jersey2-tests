package com.mcentric.jersey2.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("list")
public class ListResource {


       @GET
       @Produces("text/plain")
       public String getHello() {
           try {
               Thread.sleep(100L);
           } catch (InterruptedException e) {

           }
           return "1";
       }
    
}
