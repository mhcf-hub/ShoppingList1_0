package com.example.shoppinglist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.provider.Contacts.SettingsColumns.KEY;

public class EditShowShoppingListActivity extends AppCompatActivity {

    SaveData sData;

    ShoppingListEditShow sListEditShow;

    TextView showNameTV;

    Button addItemButton;

    EditText itemNameET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_show_shopping_list);

        load();

        String name = getIntent().getExtras().get("name").toString();

        showNameTV = (TextView) findViewById(R.id.textViewEditShowListName);
        showNameTV.setText(name + "");

        for (SimpleShoppingList shoppingList : sData.getLists()){
            System.out.println(shoppingList.getName() + " item name list");
            if(shoppingList.getName().equals(name)){
                sListEditShow = new ShoppingListEditShow(shoppingList);
            }
        }
        List<SimpleItem> shoppingItems = new ArrayList<SimpleItem>();
        if(sListEditShow.getAllItems().size() > 0){
            shoppingItems = sListEditShow.getAllItems();
        } else {
            System.out.println("no item found");
        }

        for (SimpleItem item: shoppingItems){
            System.out.println(item.getName() + " item");
        }

        System.out.println("item");

        itemNameET = (EditText) findViewById(R.id.editTextItemName);

        addItemButton = (Button) findViewById(R.id.buttonAddItem);
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleItem item = new SimpleItem(itemNameET.getText().toString());
                sListEditShow.addItem(item);
                System.out.println(item.getName() + " item add");
                save();
            }
        });


    }


    public void load(){
        try {
            sData = new SaveData();
            sData =  (SaveData) InternalStorage.readObject(this, KEY);
        } catch (IOException e) {
            System.out.println(e + " e1");
            sData = new SaveData();
        } catch (ClassNotFoundException e) {
            System.out.println(e + "e2");
            sData = new SaveData();
        }
    }


    public void save(){

        try {
            // Save the list of entries to internal storage
            InternalStorage.writeObject(this, KEY, sData);
        } catch (IOException e) {

        }
    }
}
