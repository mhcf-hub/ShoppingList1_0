package com.example.shoppinglist;

import java.util.List;

public interface ShoppingList {

    public void addItem(SimpleItem item);
    public void removeItem();
    public List<SimpleItem> getItems();
    public String getName();
    public void setName(String name);
}
