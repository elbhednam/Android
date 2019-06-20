package com.example.amandabakalarczyk.mathquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    private TextView question;
    private TextView points;
    private EditText answer;
    private int n1;
    private int n2;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button submit = findViewById(R.id.submit);
        Button next = findViewById(R.id.next);
        question = findViewById(R.id.question);
        points = findViewById(R.id.points);
        answer = findViewById(R.id.editText);
        n1 = (int)(Math.random() * 100) + 1;
        n2 = (int)(Math.random() * 100) + 1;
        question.setText("What is the sum of " + n1 + " and " + n2 + "?");


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Integer.parseInt(answer.getText().toString()) == (n1 + n2)) {
                    count++;
                    points.setText("Points: " + count);
                    Toast.makeText(getApplicationContext(),"correct",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"wrong",Toast.LENGTH_LONG).show();

                }

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer.setText("");
                answer.setHint("Enter your answer");
                n1 = (int)(Math.random() * 100) + 1;
                n2 = (int)(Math.random() * 100) + 1;
                question.setText("What is the sum of " + n1 + " and " + n2 + "?");
            }
        });
    }
}
