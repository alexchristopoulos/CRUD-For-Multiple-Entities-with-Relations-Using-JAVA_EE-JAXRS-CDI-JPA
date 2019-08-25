package com.jakartaeeproject.item;

import com.jakartaeeproject.book.Book;

import javax.persistence.*;

@Entity
public class Item {

    public Item(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    Book book;

    private int quantity;

    public void setBook(Book book) {
        this.book = book;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public int getQuantity() {
        return quantity;
    }

    public Item(Book b,int q){
        this.book=b;
        this.quantity=q;
    }
}
