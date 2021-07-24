package com.whitegoldapps.cashm4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Share extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        Button s=findViewById(R.id.shares);
        Button link=findViewById(R.id.Link);
        ImageView back1=findViewById(R.id.back);

        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String body="Download this app";
                String sub="https://play.google.com";
                intent.putExtra(Intent.EXTRA_TEXT,body);
                intent.putExtra(Intent.EXTRA_TEXT,sub);
                startActivity(Intent.createChooser(intent,"Share"));


            }
        });



        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClipboardManager manager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                String susb="https://play.google.com";

                ClipData clipData = ClipData.newPlainText("Label",susb);
                manager.setPrimaryClip(clipData);
                Toast.makeText(Share.this, "Link Copied", Toast.LENGTH_SHORT).show();

            }
        });
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open();
            }
        });
    }
    private void open() {
        Intent d;
        d=new Intent(this,MainActivity.class);
        startActivity(d);
    }
}