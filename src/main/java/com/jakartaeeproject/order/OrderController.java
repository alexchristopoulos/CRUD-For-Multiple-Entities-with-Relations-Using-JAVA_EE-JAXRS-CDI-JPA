package com.jakartaeeproject.order;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;


@Path("/order")
@Stateless
public class OrderController {

    @Inject
    private OrderDao orderDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getOrders(){
        return orderDao.getOrders();
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Order CreateOrder(String context){//0-> cotumer ID -> 1-> item1 2->item2 etc.....
        List<Integer> itemsId = new ArrayList<Integer>();
        for(String s:context.split(" ")){
            try{
                itemsId.add(Integer.parseInt(s));
            }catch(Exception e){}
        }
        return orderDao.createOrder(itemsId);
    }


}
