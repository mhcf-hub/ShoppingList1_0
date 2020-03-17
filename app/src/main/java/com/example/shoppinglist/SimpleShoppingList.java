package com.example.shoppinglist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SimpleShoppingList implements ShoppingList, Serializable {
    private List<Item> items;
    private String name;

    public SimpleShoppingList(String name){
        this.name = name;
        this.items = new ArrayList<>();
    }

    @Override
    public void addItem(Item item) {
        this.items.add(item);
    }

    @Override
    public void removeItem(){

    }

    @Override
    public List<Item> getItems() {
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
