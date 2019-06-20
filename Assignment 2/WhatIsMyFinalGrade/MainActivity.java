// Amanda Bakalarczyk
// COSC 3596
// 2018-02-05
// Application calculates and displays final grade based on user input
package com.example.amandabakalarczyk.whatismyfinalgrade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText assign = findViewById(R.id.Assignments);
        final EditText ex = findViewById(R.id.Exams);
        final TextView finalGrade = findViewById(R.id.Final);
        Button calc = findViewById(R.id.Calculate);
        Button clear = findViewById(R.id.Clear);

		// Click the calculate button to calculate grade
        // Set text on TextView to calculated grade average
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int finalG = (Integer.parseInt(assign.getText().toString()) / 2) +
                        (Integer.parseInt(ex.getText().toString()) / 2);
                finalGrade.setText(Integer.toString(finalG));
            }
        });
		
		// Click the clear button to clear EditText string
        // Reset text and hint on TextView and EditText
		// Display toast message
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalGrade.setText("Final Grade");
                assign.setText("");
                ex.setText("");
                assign.setHint("Assignments (%)");
                ex.setHint("Exams (%)");

                Toast.makeText(getBaseContext(), "Clear", Toast.LENGTH_LONG).show();
            }
        });
    }
}