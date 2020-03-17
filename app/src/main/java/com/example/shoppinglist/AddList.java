package com.example.shoppinglist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class AddList extends AppCompatActivity {

    Button createListFinal;

    SaveData sData;

    SimpleShoppingList shoppingList;

    EditText listName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);


        sData = new SaveData();
        load();

        createListFinal = (Button) findViewById(R.id.buttonCreateListFinal);

        createListFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createList();
            }
        });
    }

    public void createList(){
        listName = (EditText) findViewById(R.id.edit_list_name);
        int listCounter = 0;
        if (sData.getLists().size() > 0){
            for (SimpleShoppingList shoppingList : sData.getLists()){
                if (shoppingList.getName().equals(listName.getText().toString())){
                    listCounter++;
                }
            }
            if (listCounter > 0){
                Toast.makeText(this, "Name is already taken",
                        Toast.LENGTH_SHORT).show();
            } else {
                createListFinal();
            }
        } else {
            createListFinal();
        }
    }

    public void createListFinal(){
        shoppingList = new SimpleShoppingList(listName.getText().toString());
        sData.addShoppingList(shoppingList);
        save();

        Intent intent = new Intent(AddList.this, AllLists.class);
        startActivity(intent);
    }

    public void load(){
        try {
            sData =  (SaveData) InternalStorage.readObject(this, "sData");
        } catch (IOException e) {
            System.out.println(e + " e1");
        } catch (ClassNotFoundException e) {
            System.out.println(e + "e2");
        }
    }


    public void save(){

        try {
            // Save the list of entries to internal storage
            InternalStorage.writeObject(this, "sData", sData);
        } catch (IOException e) {

        }
    }
}
