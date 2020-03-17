package com.example.shoppinglist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.provider.Contacts.SettingsColumns.KEY;

public class AllLists extends AppCompatActivity {

    Spinner spinnerLists;

    Button createListButton;

    Button showEditList;

    SaveData sData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_lists);

        load();

        spinnerLists = (Spinner) findViewById(R.id.spinnerLists);
        List<String> list = new ArrayList<String>();

        if (sData.getLists().size() > 0){
            for (SimpleShoppingList shoppingList : sData.getLists()){
                list.add(shoppingList.getName());
            }
        } else {
            list.add("no lists found");
        }

        try {

        } catch (Exception e) {

        }


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLists.setAdapter(dataAdapter);


        createListButton = (Button) findViewById(R.id.buttonCreateList);
        createListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllLists.this, AddList.class);
                startActivity(intent);

            }
        });

        showEditList = (Button) findViewById(R.id.buttonShowList);
        showEditList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showList(spinnerLists.getSelectedItem().toString());
            }
        });

    }

    public void showList(String itemName){
        Toast.makeText(this,
                itemName,
                Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AllLists.this, EditShowShoppingListActivity.class);
            intent.putExtra("name", itemName);
            startActivity(intent);
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

}
