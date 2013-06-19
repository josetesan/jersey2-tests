package com.mcentric.jersey2.resource;

import java.util.concurrent.TimeUnit;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.Response;

@Path("listAsync")
public class AsyncListResource {


       @GET
       @Produces("text/plain")
       public void getHello(@Suspended final AsyncResponse asyncResponse) {
           
           
        asyncResponse.setTimeoutHandler(new TimeoutHandler() {

            @Override
            public void handleTimeout(AsyncResponse asyncResponse) {
                asyncResponse.resume(
                        Response.status(Response.Status.SERVICE_UNAVAILABLE)
                        .entity("Operation time out.")
                        .build());
            }
        });
        asyncResponse.setTimeout(20, TimeUnit.SECONDS);

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {

                }
                asyncResponse.resume(new String("1"));
            }

        }).start();
       }
       
       
       
    
}
