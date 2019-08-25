package com.jakartaeeproject.book;
import com.jakartaeeproject.author.Author;
import com.jakartaeeproject.category.Category;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class BookDao {

    @PersistenceContext(unitName = "MYSQL")
    private EntityManager entityManager;

    public Book BookAdd(String title,int numOfPages,int AuthorID,int CategoryID){
        if(this.CorrectAuthorID(AuthorID)&&this.CorrectCategoryID(CategoryID)){
            Book newBook = new Book(this.getAuthor(AuthorID),this.getCategory(CategoryID),title,numOfPages);
            entityManager.persist(newBook);
            return newBook;
        }else{
            return null;
        }
    }

    public List<Book> getBooks(){
        return entityManager
                .createQuery("SELECT b FROM Book b")
                .getResultList();
    }

    private Book getBook(int id){
        return entityManager.createQuery("SELECT b FROM Book b WHERE b.ISBN=:id",Book.class).setParameter("id",id).getSingleResult();
    }

    private Author getAuthor(int id){
        return (Author)entityManager.createQuery("SELECT a FROM Author a WHERE a.id=:id").setParameter("id",id).getResultList().get(0);
    }

    private Category getCategory(int id){
        return (Category) entityManager.createQuery("SELECT c FROM Category c WHERE c.id=:id").setParameter("id",id).getResultList().get(0);
    }

    private boolean CorrectAuthorID(int id){
        return entityManager
                .createQuery("SELECT a FROM Author a WHERE a.id=:id")
                .setParameter("id",id)
                .getResultList().size()==1?true:false;
    }

    private boolean CorrectCategoryID(int id){
        return entityManager
                .createQuery("SELECT c FROM Category c WHERE c.id=:id")
                .setParameter("id",id)
                .getResultList().size()==1?true:false;
    }

    public String deleteBook(int id){
        entityManager.remove(this.getBook(id));
        return "Deleted";
    }

    public Book updateBook(int id,String name,int numofPages,int aid,int cid){
        Book b = this.getBook(id);
        b.setTitle(name);
        b.setNumOfPages(numofPages);
        b.setAuthor(this.getAuthor(aid));
        b.setCategory(this.getCategory(cid));
        entityManager.merge(b);
        return b;
    }
}
