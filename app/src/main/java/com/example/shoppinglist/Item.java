package com.example.shoppinglist;

public interface Item {

     void setName(String name);
     String getName();
     void setBought(boolean bought);
     boolean getBought();
     void setId(int id);
     int getId();

}
