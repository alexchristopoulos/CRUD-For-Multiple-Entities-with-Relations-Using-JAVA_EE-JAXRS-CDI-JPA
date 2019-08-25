package com.jakartaeeproject.category;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CategoryDao {

    @PersistenceContext(unitName = "MYSQL")
    private EntityManager entityManager;

    public List<Category> getCategories(){
        return entityManager.createQuery("SELECT c FROM Category c").getResultList();
    }

    public Category addSubCategory(String name,int id){
        if(this.rootCatExists(id)){
            Category c = new Category(name,this.getById(id));
            entityManager.persist(c);
            return c;//"Category added succesfully!!";
        }else{
            return null;//"Invalid Root Category ID. Try again...";
        }
    }

    private boolean rootCatExists(int id){
        return entityManager
                .createQuery("SELECT c FROM Category c WHERE c.id=:id")
                .setParameter("id",id)
                .getResultList()
                .size()==1?true:false;
    }

    private Category getById(int id){
        return entityManager
                .createQuery("SELECT c FROM Category c WHERE c.id=:id",Category.class)
                .setParameter("id",id)
                .getSingleResult();
    }

    public Category addRootCategory(String name){
        Category c = new Category(name);
        entityManager.persist(c);
        return c;
    }

    public String deleteCategory(int id){
        entityManager.remove(this.getById(id));
        return "{\ndelete:Deleted!\n}";
    }

    public String updateCategory(int id,String name,int pid){
        if(rootCatExists(id)&&rootCatExists(pid)){//update subCategory
            Category c = this.getById(id);
            c.setCategoryName(name);
            c.setBelongsTo(this.getById(pid));
            entityManager.merge(c);
            return "Updated!";
        }else if(rootCatExists(id)&&pid<=0) {//update root category
            Category c = this.getById(id);
            c.setCategoryName(name);
            entityManager.merge(c);
            return "Updated!";
        }else{
            return "Invalid Category ID or Parent ID!";
        }
    }
}
