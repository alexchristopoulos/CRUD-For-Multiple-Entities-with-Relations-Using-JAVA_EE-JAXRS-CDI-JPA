package com.jakartaeeproject.order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jakartaeeproject.costumer.Costumer;
import com.jakartaeeproject.item.Item;
import javax.persistence.*;
import java.util.List;

@Entity
public class Order {

    public Order(){}


    public Order(Costumer costumer,List<Item> items){
        this.costumer = costumer;
        this.items=items;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JsonIgnore
    private Costumer costumer;

    @OneToMany
    private List<Item> items;


    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public Costumer getCostumer() {
        return costumer;
    }

    public List<Item> getItems() {
        return items;
    }
}
