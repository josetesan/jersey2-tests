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
    

    public TestServer() throws Exception {
        ResourceConfig resourceConfig = new ResourceConfig(ListResource.class, AsyncListResource.class);
        server = GrizzlyHttpServerFactory.createHttpServer(new URI(SERVER_URL), resourceConfig);
    }
    
    public void start() throws Exception {
        server.start();
    }
    
    
    public void stop() throws Exception {
        if ( null != server)
            server.stop();
    }
    
}
