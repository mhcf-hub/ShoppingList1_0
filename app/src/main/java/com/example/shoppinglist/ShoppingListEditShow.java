package com.example.shoppinglist;

import java.io.IOException;
import java.util.List;

public class ShoppingListEditShow {
    private ShoppingList shoppingList;

    public ShoppingListEditShow(ShoppingList shoppingList){
        this.shoppingList = shoppingList;
    }

    public List<Item> getAllItems(){
        return this.shoppingList.getItems();
    }

    public void addItem(SimpleItem item){
        this.shoppingList.addItem(item);
    }





}
