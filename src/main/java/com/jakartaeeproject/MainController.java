package com.jakartaeeproject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class MainController {
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String GetHelloMessage(){
        return "<h1 style='font-family:verdana; font-size:20px; text-shadow: 3px 3px 5px darkred;'>WELCOME</h1><hr>"+"" +
                "CRUD API FOR:<br><ul><li>CUSTOMER</li><li>ORDER</li><li>BOOK</li><li>AUTHOR</li><li>CATEGORY</li><li>ITEM</li></ul>";
    }
}
