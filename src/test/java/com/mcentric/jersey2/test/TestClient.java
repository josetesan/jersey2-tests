package com.mcentric.jersey2.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestClient {
    
    
    private static TestServer server;
    private Client client = ClientBuilder.newClient();
    
    @Rule
    public ContiPerfRule i = new ContiPerfRule();

    @BeforeClass
    public static void setUp() throws Exception {
        server = new TestServer();
        server.start();
    }
    
    @AfterClass
    public static void cleanup() throws Exception {
        server.stop();
    }


    @Test
    @PerfTest(duration = 60000, threads=16)
    public void testSync() {
        WebTarget target = client.target(TestServer.SERVER_URL).path("/list");
        String result = target
                .request(MediaType.TEXT_PLAIN)
                .accept(MediaType.TEXT_PLAIN)
                .buildGet()
                .invoke(String.class);
        Assert.assertEquals("1", result);
    }
    
    
    @Test
    @PerfTest(duration = 60000, threads=16)
    public void testASync() {
        WebTarget target = client.target(TestServer.SERVER_URL).path("/listAsync");
        target.request()
                .async()
                .get(new InvocationCallback<String>() {
                    @Override
                    public void completed(String response) {
                        Assert.assertEquals("1", response);
                    }
         
                    @Override
                    public void failed(Throwable throwable) {
                    }
                });
    }

}
