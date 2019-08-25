package com.jakartaeeproject.item;

import com.jakartaeeproject.book.Book;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ItemDao {
    @PersistenceContext(unitName = "MYSQL")
    private EntityManager entityManager;

    public List<Item> getItems(){
        return entityManager.createQuery("SELECT i FROM Item i").getResultList();
    }

    public String updateItem(int id,int q,int bid){
        if(this.ItemExists(id))
        {
            Item current = this.getItem(id);
            if(this.BookExists(bid)){
                current.setBook(this.getBook(bid));
                current.setQuantity(q);
                entityManager.merge(current);
                return "Item quantity and Book updated!";
            }else{
                current.setQuantity(q);
                entityManager.merge(current);
                return "Item quantity updated!";
            }
        }else{
            return "Item Not Exists!";
        }
    }

    public Item CreateItem(int bid,int quantity){
        if(this.BookExists(bid)){
            Item i = new Item(this.getBook(bid),quantity);
            entityManager.persist(i);
            return i;
        }else{
            return null;
        }
    }

    private boolean BookExists(int id){
        return entityManager
                .createQuery("SELECT b FROM Book b WHERE B.ISBN=:id", Book.class)
                .setParameter("id",id)
                .getResultList()
                .size()==1?true:false;
    }

    private boolean ItemExists(int id){
        return entityManager
                .createQuery("SELECT i FROM Item i WHERE i.id=:id", Item.class)
                .setParameter("id",id)
                .getResultList()
                .size()==1?true:false;
    }

    private Book getBook(int id){
        return entityManager.createQuery("SELECT b FROM Book B WHERE b.ISBN=:id",Book.class)
                .setParameter("id",id)
                .getResultList().get(0);
    }

    private Item getItem(int id){
        return entityManager.createQuery("SELECT b FROM Item B WHERE b.id=:id",Item.class)
                .setParameter("id",id)
                .getResultList().get(0);
    }

    public String deleteItem(int id){
        if(this.ItemExists(id))
            entityManager.remove(this.getItem(id));
        return "Deleted!";
    }

}
