package com.whitegoldapps.cashm4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class Pay extends AppCompatActivity implements PaymentResultListener {
    EditText name, email, phNo,amount;
    Button submit;

    private Checkout chackout;
    private String razorpayKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        String f=getIntent().getStringExtra("p");

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        phNo=findViewById(R.id.phNo);
        amount=findViewById(R.id.amount);
        submit=findViewById(R.id.submit);
amount.setText(f);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().equals(null) || name.getText().toString().equals("")){
                    name.setError("Please Fillup");
                }else if(email.getText().toString().equals(null) || email.getText().toString().equals("")){
                    email.setError("Please Fillup");
                }else if(phNo.getText().toString().equals(null) || phNo.getText().toString().equals("")){
                    phNo.setError("Please Fillup");
                }else if(phNo.getText().toString().length()!=10 ){
                    phNo.setError("Please Enter 10 digit phone number");
                }else if(amount.getText().toString().equals(null) || amount.getText().toString().equals("")){
                    amount.setError("Please Fillup");
                }else if(Integer.parseInt(amount.getText().toString())==0){
                    amount.setError("Amount should be greater than 0"); //Razorpay min amount is 1 Rs.
                }else{
                    //you have to convert Rs. to Paisa using multiplication of 100
                    String convertedAmount=String.valueOf(Integer.parseInt(amount.getText().toString())*100);

                    rezorpayCall(name.getText().toString(),email.getText().toString(),phNo.getText().toString(),convertedAmount);
                }
            }
        });

    }
    public void rezorpayCall(String name, String email, String phNo, String convertedAmount){
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        razorpayKey="rzp_test_qZCbdz1SZ2H0nQ"; //Generate your razorpay key from Settings-> API Keys-> copy Key Id
        chackout = new Checkout();
        chackout.setKeyID(razorpayKey);
        try {
            JSONObject options = new JSONObject();
            options.put("name", name);
            options.put("description", "Razorpay Payment Test");
            options.put("currency", "INR");
            options.put("amount", convertedAmount);

            JSONObject preFill = new JSONObject();
            preFill.put("email", email);
            preFill.put("contact", phNo);
            options.put("prefill", preFill);

            chackout.open(Pay.this, options);
        } catch (Exception e) {
            Toast.makeText(Pay.this, "Error in payment: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        // After successful payment Razorpay send back a unique id
        Toast.makeText(Pay.this, "Transaction Successful: Order Placed" + razorpayPaymentID, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onPaymentError(int i, String error) {
        // Error message
        Toast.makeText(Pay.this, "Transaction unsuccessful: "+ error , Toast.LENGTH_LONG).show();
    }
}
