// Amanda Bakalarczyk
// COSC 3596
// 2018-02-05
// Shopping list application allows user to add and remove items from a shopping list
package com.example.amandabakalarczyk.shoppinglist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> groceryArray;
    private ArrayAdapter<String> listAdapter;
    private ListView groceryList;
    private EditText groceryItem;
    private Button addItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        groceryArray = new ArrayList<>();
        listAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, groceryArray);
        groceryList = findViewById(R.id.List);
        groceryList.setAdapter(listAdapter);
        groceryItem = findViewById(R.id.Item);
        addItem = findViewById(R.id.Add);

        // Click the add button to add EditText string to ListView
        // notify the ArrayAdapter that the data has been changed
        // Reset text and hint on EditText
        addItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String item = groceryItem.getText().toString();
                listAdapter.add(item);
                listAdapter.notifyDataSetChanged();
                groceryItem.setText("");
                groceryItem.setHint("Enter an item you want to buy");
            }
        });

        // remove the item at position from the ArrayList using the remove method
        // notify the ArrayAdapter that the data has been changed
        // return true
        groceryList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                listAdapter.remove(groceryArray.get(position));
                listAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}
