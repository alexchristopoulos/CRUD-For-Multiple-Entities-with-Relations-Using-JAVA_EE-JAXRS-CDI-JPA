package com.jakartaeeproject.category;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/category")
@Stateless
public class CategoryController {

    @Inject
    CategoryDao categoryDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> getCategories(){
        return categoryDao.getCategories();
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    public Category addCategory(@HeaderParam("name") String name,@HeaderParam("belongs") int id){
        if(id<=0){//rootCategory
            return categoryDao.addRootCategory(name);
        }else{// subCategory
            return categoryDao.addSubCategory(name,id);
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_HTML)
    public String deleteCategoryById(@PathParam("id") int id){
        return categoryDao.deleteCategory(id);
    }

    @PUT
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_HTML)
    public String updateCategory(@HeaderParam("id") int id,@HeaderParam("name") String name,@HeaderParam("belongs") int pid){
        return categoryDao.updateCategory(id,name,pid);
    }

}
