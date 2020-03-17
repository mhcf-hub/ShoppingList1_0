package com.example.shoppinglist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SimpleShoppingList implements ShoppingList, Serializable {
    private List<SimpleItem> items;
    private String name;

    public SimpleShoppingList(String name){
        this.name = name;
        this.items = new ArrayList<SimpleItem>();
    }

    @Override
    public void addItem(SimpleItem item) {
        this.items.add(item);
    }

    @Override
    public void removeItem(){

    }

    @Override
    public List<SimpleItem> getItems() {
        return items;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
