package com.example.shoppinglist;

import java.io.Serializable;

public class SimpleItem implements Item, Serializable {
    private String name;

    public SimpleItem(String name){
        this.name = name;
    }


    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
