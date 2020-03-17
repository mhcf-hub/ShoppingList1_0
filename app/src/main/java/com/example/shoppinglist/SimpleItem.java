package com.example.shoppinglist;

import java.io.Serializable;

public class SimpleItem implements Item, Serializable {
    private String name;
    private boolean bought;
    private int id;

    public SimpleItem(String name, int id){
        this.name = name;
        this.bought = false;
        this.id = id;

    }


    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setBought(boolean bought) {
        this.bought = bought;
    }

    @Override
    public boolean getBought() {
        return this.bought;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return this.id;
    }
}
