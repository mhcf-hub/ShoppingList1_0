package com.example.shoppinglist;

import java.io.IOException;
import java.util.List;

import static android.provider.Contacts.SettingsColumns.KEY;

public class ShoppingListEditShow {
    private ShoppingList shoppingList;

    public ShoppingListEditShow(ShoppingList shoppingList){
        this.shoppingList = shoppingList;
    }

    public List<SimpleItem> getAllItems(){
        return this.shoppingList.getItems();
    }

    public void addItem(SimpleItem item){
        this.shoppingList.addItem(item);
    }





}
