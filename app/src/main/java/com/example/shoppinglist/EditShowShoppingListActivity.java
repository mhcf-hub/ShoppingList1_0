package com.example.shoppinglist;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class EditShowShoppingListActivity extends AppCompatActivity {

    SaveData sData;

    ShoppingListEditShow sListEditShow;

    TextView showNameTV;

    Button addItemButton;

    EditText itemNameET;

    List<Item> shoppingItems;

    LinearLayout linearLayoutAllItems;

    String nameShoppingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_show_shopping_list);

        load();

        nameShoppingList = getIntent().getExtras().get("name").toString();

        linearLayoutAllItems = (LinearLayout) findViewById(R.id.linearLayoutAllItems);

        showNameTV = (TextView) findViewById(R.id.textViewEditShowListName);
        showNameTV.setText(nameShoppingList + "");


        for (SimpleShoppingList shoppingList : sData.getLists()) {
            if (shoppingList.getName().equals(nameShoppingList)) {
                sListEditShow = editShow(shoppingList);
            }
        }
        drawItems();


        System.out.println("item");

        itemNameET = (EditText) findViewById(R.id.editTextItemName);

        addItemButton = (Button) findViewById(R.id.buttonAddItem);
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleItem item = new SimpleItem(itemNameET.getText().toString(), sListEditShow.getAllItems().size());
                sListEditShow.addItem(item);
                itemNameET.setText("");
                linearLayoutAllItems.removeAllViews();
                save();
                load();
                drawItems();
            }
        });


    }

    public ShoppingListEditShow editShow(SimpleShoppingList shoppingList){
        return new ShoppingListEditShow(shoppingList);
    }

    public void drawItems() {

        final float scale = this.getResources().getDisplayMetrics().density;


        for (SimpleShoppingList shoppingList : sData.getLists()) {
            if (shoppingList.getName().equals(nameShoppingList)) {
                sListEditShow = editShow(shoppingList);
            }
        }

        shoppingItems = new ArrayList<Item>();
        if (sListEditShow.getAllItems().size() > 0) {
            shoppingItems = sListEditShow.getAllItems();
        } else {
            System.out.println("no item found");
        }

        for (final Item item : shoppingItems) {
            //Create Button for each item
            //Set Params
            LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            buttonParams.setMargins(25, 25, 0, 0);

            ////Create Button
            final Button listItemButton = new Button(this);

            //Set TextString of itemButton
            listItemButton.setText(item.getName());

            //Set styles.xml of itemButtom
            listItemButton.setTextAppearance(this, R.style.fontForListItems);

            //Set Width of itemButtom
            listItemButton.setWidth((int) (314 * scale + 0.5f));

            //Set Backgroundcolor of itemButtom
            listItemButton.setBackgroundColor(getResources().getColor(R.color.bgwhite));

            //Assign params to itemButtom
            listItemButton.setLayoutParams(buttonParams);


            linearLayoutAllItems.addView(listItemButton);

            if (item.getBought()) {
                listItemButton.setPaintFlags(listItemButton.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }

            listItemButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!listItemButton.getPaint().isStrikeThruText()) {
                        listItemButton.setPaintFlags(listItemButton.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        for (SimpleShoppingList shoppingList : sData.getLists()) {
                            if (shoppingList.getName().equals(nameShoppingList)) {
                                shoppingList.getItems().get(item.getId()).setBought(true);
                            }
                        }
                    } else {
                        listItemButton.setPaintFlags(listItemButton.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                        for (SimpleShoppingList shoppingList : sData.getLists()) {
                            if (shoppingList.getName().equals(nameShoppingList)) {
                                shoppingList.getItems().get(item.getId()).setBought(false);
                            }
                        }
                    }
                    save();
                }
            });

        }
    }


    public void load() {
        try {
            sData = new SaveData();
            sData = (SaveData) InternalStorage.readObject(this, "sData");
        } catch (IOException e) {
            System.out.println(e + " e1");
            sData = new SaveData();
        } catch (ClassNotFoundException e) {
            System.out.println(e + "e2");
            sData = new SaveData();
        }
    }


    public void save() {

        try {
            // Save the list of entries to internal storage
            InternalStorage.writeObject(this, "sData", sData);
        } catch (IOException e) {

        }
    }
}
