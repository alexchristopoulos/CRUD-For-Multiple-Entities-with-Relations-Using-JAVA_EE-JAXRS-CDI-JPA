package com.jakartaeeproject;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class Main extends ResourceConfig {
    public Main(){
        super();
        packages(true,"com.jakartaeeproject");
        register(JacksonFeature.class);
    }
}
