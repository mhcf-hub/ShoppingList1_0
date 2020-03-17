package com.example.shoppinglist;

import java.util.List;

public interface ShoppingList {

     void addItem(Item item);
     void removeItem();
     List<Item> getItems();
     String getName();
     void setName(String name);
}
