package com.jakartaeeproject.book;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@Path("/book")
public class BookController {

    @Inject BookDao bookDao;

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    public Book bookAdd(@HeaderParam("AuthorID") int aId,@HeaderParam("CategoryId") int cId,@HeaderParam("title") String title,@HeaderParam("pages") int pages){
        return bookDao.BookAdd(title,pages,aId,cId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getAll(){
        return bookDao.getBooks();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.TEXT_HTML)
    public String deleteBook(@PathParam("id") int bookId){
        return bookDao.deleteBook(bookId);
    }

    @PUT
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    public Book updateBook(@HeaderParam("id") int id,@HeaderParam("title")String name,@HeaderParam("pages") int numOfPages,@HeaderParam("AuthorId") int aid,@HeaderParam("CategoryId")int cid){
        return bookDao.updateBook(id,name,numOfPages,aid,cid);
    }

}
