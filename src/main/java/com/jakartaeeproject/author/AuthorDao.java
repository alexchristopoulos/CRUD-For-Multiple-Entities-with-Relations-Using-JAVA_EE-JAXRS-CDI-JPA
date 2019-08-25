package com.jakartaeeproject.author;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AuthorDao {

    @PersistenceContext(unitName = "MYSQL")
    private EntityManager entityManager;

    public List<Author> getAll(){
        return entityManager.createQuery("SELECT a.id,a.name,a.surname,a.age FROM Author a").getResultList();
    }

    public void createAuthor(Author a){
        entityManager.persist(a);
    }

    public void DeleteAuthor(int id){
        Author a = (Author) entityManager
                .createQuery("SELECT a FROM Author a WHERE a.id=:id")
                .setParameter("id",id)
                .getResultList()
                .get(0);
        entityManager.remove(a);
    }

    public void UpdateAuthor(int id,String name,String surname,int age){
        Author a = (Author) entityManager
                .createQuery("SELECT a FROM Author a WHERE a.id=:id")
                .setParameter("id",id)
                .getResultList()
                .get(0);
        a.setName(name);
        a.setAge(age);
        a.setSurname(surname);
        entityManager.merge(a);
    }
}
