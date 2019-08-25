package com.jakartaeeproject.category;

import javax.persistence.*;

@Entity
public class Category {

    public Category(){}

    public Category(String Cat,Category parent){
        this.categoryName = Cat;
        this.belongsTo = parent;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;
    private String categoryName;

    @OneToOne
    Category belongsTo;


    public Category(String name){
        this.categoryName=name;
        this.belongsTo=null;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setBelongsTo(Category belongsTo) {
        this.belongsTo = belongsTo;
    }

    public int getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Category getBelongsTo() {
        return belongsTo;
    }

}
