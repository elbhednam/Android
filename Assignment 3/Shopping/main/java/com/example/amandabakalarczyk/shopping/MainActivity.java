package com.example.amandabakalarczyk.shopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

// Amanda Bakalarczyk
// COSC 3596
// 2018-03-18
// Question #2 - Shopping

public class MainActivity extends AppCompatActivity {

    // items for sale
    private static final int[] imageArray = {
            R.drawable.raspberry,
            R.drawable.cupcake,
            R.drawable.chocolate,
            R.drawable.confetti,
            R.drawable.mango,
            R.drawable.vanilla
    };
    // item prices
    private static final double[] priceArray = {2.50,2.50,4.00,2.00,3.75,1.75};
    // EditText ids
    private static final int [] etid = {R.id.et1, R.id.et2, R.id.et3, R.id.et4, R.id.et5, R.id.et6};

    private TextView tv;
    private TextView tv2;
    private EditText et;
    private double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout layout = findViewById(R.id.activity_main);

        // Layout inflater for items, prices, and quantities
        View inflatedView;
        for (int i = 0; i < imageArray.length; i++) {
            inflatedView = getLayoutInflater().inflate(R.layout.layout, null);
            ImageView iv = inflatedView.findViewById(R.id.icon);
            iv.setImageResource(imageArray[i]);
            tv = inflatedView.findViewById(R.id.tv);
            tv.setText("Price: $" + String.format("%.2f", priceArray[i]));
            et = inflatedView.findViewById(R.id.et);
            et.setId(etid[i]);
            layout.addView(inflatedView);
        }

        // Layout parameters for next two widgets
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, // width
                LinearLayout.LayoutParams.WRAP_CONTENT // height
        );
        params.setMargins(0, 10, 0, 50);
        params.gravity = Gravity.CENTER;

        // "Checkout" button
        Button bt = new Button(this);
        bt.setText("CHECKOUT");
        bt.setTextSize(18);
        bt.setPadding(200,40,200,40);
        bt.setLayoutParams(params);
        layout.addView(bt);

        // TextView for price total
        tv2 = new TextView(this);
        tv2.setLayoutParams(params);
        tv2.setTextSize(27);

        // Listener method to calculate and display total
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                total = 0;
                // Calculate total from price array elements and EditText entries
                for (int i = 0; i < priceArray.length; i++) {
                    et = findViewById(etid[i]);
                    // If quantity not specified, set EditText to zero
                    if (et.getText().toString().length() == 0) {
                        et.setText("0");

                    }
                    total += priceArray[i]
                            * (Integer.parseInt(et.getText().toString()));
                }
                tv2.setText("Total: $" + String.format("%.2f", total));
            }
        });
        // Add total to layout
        layout.addView(tv2);
    }
}


