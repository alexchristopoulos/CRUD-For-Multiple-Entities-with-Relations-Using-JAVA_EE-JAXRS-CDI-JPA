package com.jakartaeeproject.author;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/author")
@Stateless
public class AuthorController {

    @Inject
    private AuthorDao authorDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Author> getAll(){
        return authorDao.getAll();
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    public Author CreateAuthorEntity(@HeaderParam("name") String name, @HeaderParam("surname") String surname, @HeaderParam("age") int age){
        Author newAuthor = new Author(name,surname,age);
        authorDao.createAuthor(newAuthor);
        return newAuthor;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_HTML)
    public String deleteById(@PathParam("id") int id){
        authorDao.DeleteAuthor(id);
        return "Author "+ id + "deleted";
    }

    @PUT
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_HTML)
    public String UpdateAuthor(@HeaderParam("id") int id,@HeaderParam("name") String name, @HeaderParam("surname") String surname, @HeaderParam("age") int age){
        authorDao.UpdateAuthor(id,name,surname,age);
        return "Author "+ id + " updated";
    }
}
