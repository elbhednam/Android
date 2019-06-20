package com.example.amandabakalarczyk.showthemark;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.*;
import android.content.Intent;

// Amanda Bakalarczyk
// COSC 3596
// Assignment 3
// Question #1 - Show the mark

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText id;
    private Button bt;
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList <String> ids = new ArrayList<>();
    private ArrayList <String> marks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt = findViewById(R.id.BTsubmit);
        id = findViewById(R.id.ETid);
        name = findViewById(R.id.ETname);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                readFile();
                // get text from edit text, remove spaces, set to lower case
                String n = name.getText().toString().toLowerCase();
                n = n.replaceAll("\\s+","");
                String i = id.getText().toString();

                // If the student clicks the SUBMIT Button without entering a name and/or ID
                if (i.length() == 0 || n.length() == 0 ) {
                    Toast.makeText(getBaseContext(), "Enter student name/id", Toast.LENGTH_LONG).show();
                }
                // If the student name cannot be found in the file
                else if (!names.contains(n)) {
                    Toast.makeText(getBaseContext(), "Student name does not exist", Toast.LENGTH_LONG).show();
                }
                // If the student ID is not correct
                else if (names.indexOf(n) != ids.indexOf(id.getText().toString())) {
                    Toast.makeText(getBaseContext(), "Wrong student ID", Toast.LENGTH_LONG).show();
                }
                // If the name and ID are correct, display midterm mark in second activity
                else {
                    n = name.getText().toString();
                    Character.toUpperCase(n.charAt(0));
                    // Re-capitalize the first letter
                    n =  n.substring(0, 1).toUpperCase() + n.substring(1, n.indexOf(" "));
                    int e = ids.indexOf(i);
                    String m = marks.get(e);
                    Intent intent = new Intent(v.getContext(), Main2Activity.class);
                    intent.putExtra("name", n);
                    intent.putExtra("mark", m);
                    startActivity(intent);
                }
            }
        });
    }

    public void readFile() {
        Scanner scan = new Scanner(getResources().openRawResource(R.raw.exammarks));

        // Read a line from the file line by line
        while (scan.hasNextLine()) {

            String line = scan.nextLine(); // Read a line
            String[] pieces = line.split("\\s+"); // place line in array delimited by spaces
            names.add(pieces[0].toLowerCase() + pieces[1].toLowerCase()); // Add first and last name to names list
            ids.add(pieces[2]); // Add id to id list
            marks.add(pieces[3]); // Add mark to marks list
        }
        scan.close();
    }
}
