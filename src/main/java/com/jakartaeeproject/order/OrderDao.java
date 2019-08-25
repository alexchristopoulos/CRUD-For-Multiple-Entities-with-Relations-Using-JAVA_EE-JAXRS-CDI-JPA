package com.jakartaeeproject.order;

import com.jakartaeeproject.costumer.Costumer;
import com.jakartaeeproject.item.Item;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class OrderDao {

    @PersistenceContext(unitName = "MYSQL")
    private EntityManager entityManager;

    public List<Order> getOrders(){
        return entityManager.createQuery("SELECT o FROM Order o").getResultList();
    }

    public Order createOrder(List<Integer> params){
        if(this.costumerExists(params.get(0))){
            Costumer c = this.getCostumer(params.get(0));
            params.remove(0);
            Order o = new Order(c,this.getItemList(params));
            entityManager.persist(o);
            return o;
        }else{
            return null;
        }

    }

    private boolean itemExists(int id){
        return entityManager.createQuery("SELECT i FROM Item i WHERE i.id=:id")
                .setParameter("id",id)
                .getResultList()
                .size()==1?true:false;
    }

    private boolean costumerExists(int id){
        return entityManager.createQuery("SELECT i FROM Costumer i WHERE i.id=:id")
                .setParameter("id",id)
                .getResultList()
                .size()==1?true:false;
    }
    private Costumer getCostumer(int id){
        return (Costumer)entityManager.createQuery("SELECT c FROM Costumer c WHERE c.id=:id")
                .setParameter("id",id)
                .getResultList()
                .get(0);
    }

    private Item getItem(int id){
        return (Item)entityManager.createQuery("SELECT c FROM Item c WHERE c.id=:id")
                .setParameter("id",id)
                .getResultList()
                .get(0);
    }

    private List<Item> getItemList(List<Integer> list){
        List<Item> items = new ArrayList<Item>();
        for(int x:list){
            if(this.itemExists(x)){
                items.add(this.getItem(x));
            }
        }
        return items;
    }

}
