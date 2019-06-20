package com.example.amandabakalarczyk.showthemark;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.content.*;

// Amanda Bakalarczyk
// COSC 3596
// Assignment 3
// Question #1 - Show the mark (Activity 2)

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView tv = findViewById(R.id.tv);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String mark = intent.getStringExtra("mark");

        // Display midterm mark for student
        tv.setText("Hello " + name + ",\n\n Your midterm exam mark is \n" + mark);
    }
}
