package com.jakartaeeproject.costumer;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/costumer")
@Stateless
public class CostumerController {

    @Inject
    private CostumerDao costumerDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Costumer> getCostumers(){
        return costumerDao.getAll();
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    public Costumer addCostumer(@HeaderParam("name") String name,@HeaderParam("surname") String surname,@HeaderParam("age") int age,@HeaderParam("address") String address){
        Costumer newCostumer = new Costumer(name,surname,age,address);
        costumerDao.addCostumer(newCostumer);
        return newCostumer;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_HTML)
    public String deleteCostumer(@PathParam("id") int id){
        costumerDao.DeleteCostumer(id);
        return "<p>Costumer with ID: "+id+" deleted</p>";
    }

    @PUT
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_HTML)
    public String update(@HeaderParam("name") String name,@HeaderParam("id") int id,@HeaderParam("surname") String surname,@HeaderParam("age") int age,@HeaderParam("address") String address){
        costumerDao.UpdateCostumer(id,name,surname,age,address);
        return "<p>Costumer with ID: "+id+" updated</p>";
    }

}
