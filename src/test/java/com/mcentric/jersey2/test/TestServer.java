package com.mcentric.jersey2.test;

import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.mcentric.jersey2.resource.AsyncListResource;
import com.mcentric.jersey2.resource.ListResource;

public class TestServer {
    
    private HttpServer  server;
    
    public  static final String SERVER_URL = "http://localhost:5555";
    

    public TestServer() {
        
    }
    
    public void start() throws Exception {
        ResourceConfig resourceConfig = new ResourceConfig(ListResource.class, AsyncListResource.class);
        // The following line is to enable GZIP when client accepts it
        server = GrizzlyHttpServerFactory.createHttpServer(new URI(SERVER_URL), resourceConfig);
        server.start();
    }
    
    
    public void stop() throws Exception {
        if ( null != server)
            server.stop();
    }
    
}
