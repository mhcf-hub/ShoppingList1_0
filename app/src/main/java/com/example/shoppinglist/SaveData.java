package com.example.shoppinglist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SaveData implements Serializable {

        private List<SimpleShoppingList> shoppingLists;

        public SaveData(){
            this.shoppingLists = new ArrayList<SimpleShoppingList>();
        }

        public void addShoppingList(SimpleShoppingList shoppingList){
            this.shoppingLists.add(shoppingList);
        }

        public List<SimpleShoppingList> getLists(){
            return this.shoppingLists;
        }

}






