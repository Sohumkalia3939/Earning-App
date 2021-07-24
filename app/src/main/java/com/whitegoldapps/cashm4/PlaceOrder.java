package com.whitegoldapps.cashm4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PlaceOrder extends AppCompatActivity {
TextView p;
TextView Goldcoins,leftpay,button2;
String c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        String f=getIntent().getStringExtra("left");

        String items=getIntent().getStringExtra("Price");
button2=findViewById(R.id.button2);
        //String f=getIntent().getStringExtra("left");
c=f;
        p=findViewById(R.id.pricess);
        p.setText("Your item "+items);
leftpay=findViewById(R.id.left_pay);
leftpay.setText("Amount "+f+" Left to Pay");
        double str1 = Double.parseDouble(f);

        if (str1 > 0){
           // Toast.makeText(this, "Given number is a positive integer", Toast.LENGTH_SHORT).show();
leftpay.setText("Amount 0 is Left to pay");
button2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(PlaceOrder.this, "Order Placed", Toast.LENGTH_SHORT).show();
    }
});
        } else if(str1 < 0){
            //Toast.makeText(this, "Given number is a negative integer", Toast.LENGTH_SHORT).show();
            leftpay.setText("Amount "+f+" Left to Pay");
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
o();


                }
            });


        } else {
            Toast.makeText(this, "Given number is neither positive nor negative integer", Toast.LENGTH_SHORT).show();
        }









    }


    private void o() {

        Intent intent = new Intent(this, Pay.class);


//        String df=String.valueOf(f);

        intent.putExtra("p",c);
        startActivity(intent);
    }
}