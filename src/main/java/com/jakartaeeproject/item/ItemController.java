package com.jakartaeeproject.item;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/item")
@Stateless
public class ItemController {

    @Inject
    private ItemDao itemDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> getItems(){
        return itemDao.getItems();
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    public Item createItem(@HeaderParam("BookId") int id,@HeaderParam("quantity") int q){
        return itemDao.CreateItem(id,q);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_HTML)
    public String deleteItem(@PathParam("id") int id){
        return itemDao.deleteItem(id);
    }

    @PUT
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_HTML)
    public String updateItem(@HeaderParam("id") int itemId,@HeaderParam("quantity") int q,@HeaderParam("BookId") int bookId){
        return itemDao.updateItem(itemId,q,bookId);
    }

}
