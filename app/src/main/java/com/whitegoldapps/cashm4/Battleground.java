
package com.whitegoldapps.cashm4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Battleground extends AppCompatActivity {


    FirebaseDatabase database;
    DatabaseReference reff;
    member member;
    Button button;
    Spinner spinner;
    int maxid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battleground);


        button = findViewById(R.id.sub);
        spinner = findViewById(R.id.spinner1);
        member = new member();
        reff = database.getInstance().getReference().child("Playerid Battle Ground");

        List<String> categories = new ArrayList<>();
        categories.add(0, "choose month");
        categories.add("january");
        categories.add("february");
        categories.add("March");
        categories.add("april");
        categories.add("may");
        categories.add("june");
        categories.add("august");
        categories.add("sepetember");
        categories.add("october");
        categories.add("november");
        categories.add("December");
        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("choose event")) {

                } else {
                    String item = adapterView.getItemAtPosition(i).toString();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                    maxid = (int) dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String spin = spinner.getSelectedItem().toString();
                if (spinner != null){
                    TextView p;
                    p=findViewById(R.id.playerid);
                    final String title = p.getText().toString().trim();

                    member.setPlayerid(title);
                    member.setSpinner(spin);

                    Toast.makeText(Battleground.this, "Registration Successful" +
                            "", Toast.LENGTH_LONG).show();
                    //toast.show();

                    reff.child(String.valueOf(maxid+1)).setValue(member);
                    trap();
                }

            }
        });

    }

    private void trap() {

        Intent sas;
        sas=new Intent(this,Bill.class);
        startActivity(sas);
    }
}