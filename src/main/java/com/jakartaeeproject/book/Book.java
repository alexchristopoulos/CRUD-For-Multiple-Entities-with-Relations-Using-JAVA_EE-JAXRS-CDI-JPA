package com.jakartaeeproject.book;
import com.jakartaeeproject.author.Author;
import com.jakartaeeproject.category.Category;
import javax.persistence.*;

@Entity
public class Book {

    public Book(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ISBN;

    private String title;
    private int numOfPages;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNumOfPages(int numOfPages) {
        this.numOfPages = numOfPages;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @OneToOne
    private Author author;

    @OneToOne
    private Category category;

    public Book(Author a,Category c,String title,int numOfPages){
        this.author = a;
        this.category=c;
        this.title=title;
        this.numOfPages=numOfPages;
    }

    public int getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public int getNumOfPages() {
        return numOfPages;
    }

    public Author getAuthor() {
        return author;
    }

    public Category getCategory() {
        return category;
    }
}
