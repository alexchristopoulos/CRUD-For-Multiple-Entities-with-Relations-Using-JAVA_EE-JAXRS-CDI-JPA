package com.jakartaeeproject.costumer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CostumerDao {

    @PersistenceContext(unitName = "MYSQL")
    private EntityManager entityManager;

    public void addCostumer(Costumer c){
        entityManager
                .persist(c);
    }

    public List<Costumer> getAll(){
        return entityManager
                .createQuery("SELECT c.id,c.name,c.address,c.surname,c.age FROM Costumer c")
                .getResultList();
    }

    public void DeleteCostumer(int id){
        entityManager
                .remove(entityManager
                        .createQuery("SELECT c FROM Costumer c WHERE c.id=:id")
                        .setParameter("id",id)
                        .getResultList()
                        .get(0));
    }

    public void UpdateCostumer(int id,String name,String surname,int age,String address){
        Costumer updatedCostumer = (Costumer) entityManager
                .createQuery("SELECT c FROM Costumer c WHERE c.id=:id")
                .setParameter("id",id)
                .getResultList()
                .get(0);
        updatedCostumer.setName(name);
        updatedCostumer.setAge(age);
        updatedCostumer.setSurname(surname);
        updatedCostumer.setAddress(address);
        entityManager.merge(updatedCostumer);
    }


}
