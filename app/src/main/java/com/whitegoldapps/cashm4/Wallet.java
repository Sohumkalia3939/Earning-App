package com.whitegoldapps.cashm4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import org.w3c.dom.Text;

import javax.annotation.Nullable;

public class Wallet extends AppCompatActivity {
    private static final int GALLERY_INTENT_CODE = 1023 ;
    TextView fullName,email,phone,verifyMsg;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;

    Button resendCode;
    Button resetPassLocal,changeProfileImage;
    FirebaseUser user;
    ImageView profileImage;
    StorageReference storageReference;
TextView ban,erc;

TextView discount,pro,d;

ImageView uccs,b,s,c,n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet2);
        phone = findViewById(R.id.currentCoins);
        discount=findViewById(R.id.discount);
        n=findViewById(R.id.ff);
        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Wallet.this, "if you want any other thing from CashM4 please tell us. We will add it for you ", Toast.LENGTH_SHORT).show();
            }
        });
        fAuth = FirebaseAuth.getInstance();
        if (fAuth.getCurrentUser() != null) {
            userId = fAuth.getCurrentUser().getUid();
            fAuth = FirebaseAuth.getInstance();
            fStore = FirebaseFirestore.getInstance();
            storageReference = FirebaseStorage.getInstance().getReference();
d=findViewById(R.id.diamond);
s=findViewById(R.id.social);
c=findViewById(R.id.cp);
c.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        c();
    }
});
s.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        social();
    }
});
d.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        dia();
    }
});

ban=findViewById(R.id.bonus);
b=findViewById(R.id.battle);
            ImageView u;
            u=findViewById(R.id.upi);
            u.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    upi();
                }
            });

            b.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        battle();
    }
});
ProgressBar progress_bars;
TextView p;
progress_bars=findViewById(R.id.progress_bar);
p=findViewById(R.id.text_view_progress);
erc=findViewById(R.id.erc);
            uccs=findViewById(R.id.uc);
            uccs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                uc();
                }
            });

            //userId = fAuth.getCurrentUser().getUid();
            user = fAuth.getCurrentUser();

            DocumentReference documentReference = fStore.collection("users").document(userId);
            documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    if(documentSnapshot.exists()){
                        String main;
                        phone.setText("You have "+documentSnapshot.getDouble("final")+" Gold coins");
                        ban.setText("Bonus Gold Coins "+documentSnapshot.getDouble("Bonus"));
                        discount.setText("Discount Coupon "+documentSnapshot.getDouble("discount"));
                        erc.setText("No ERF Coupon "+documentSnapshot.getDouble("erc"));
                        main=documentSnapshot.getString("progress");
                        p.setText(main+"%");
                        int i=Integer.parseInt(main);

                        progress_bars.setProgress(i);
                        progress_bars.setMax(100); // 100 maximum value for the progress bar
                    }else {
                        Log.d("tag", "onEvent: Document do not exists");
                    }
                }
            });

        }
        else{
            o();



        }




//            resendCode.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(final View v) {
//
//                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            Toast.makeText(v.getContext(), "Verification Email Has been Sent.", Toast.LENGTH_SHORT).show();
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Log.d("tag", "onFailure: Email not sent " + e.getMessage());
//                        }
//                    });
//                }
//            });



ImageView b,g;
        b=findViewById(R.id.bc);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bc();
            }
        });
        g=findViewById(R.id.jio);
        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jio();
            }
        });





    }

    private void c() {
        Intent intent = new Intent(this, cp.class);
        startActivity(intent);
    }

    private void battle() {
        Intent ssd;
        ssd=new Intent(this,Battleground.class);
        startActivity(ssd);
    }

    private void upi() {
        Intent ssd;
        ssd=new Intent(this,upi.class);
        startActivity(ssd);
    }
    private void dia() {
        Intent ssd;
        ssd=new Intent(this,diamond.class);
        startActivity(ssd);
    }

    private void o() {

        Intent ssd;
        ssd=new Intent(this,RegisterActivity.class);
        startActivity(ssd);
    }
    public void uc(){
        Intent intent = new Intent(this, uc.class);
        startActivity(intent);
    }
    public void bc(){
        Intent intent = new Intent(this, bc.class);
        startActivity(intent);
    }

    private void jio(){
        Intent intent = new Intent(this, jio.class);
        startActivity(intent);
    }
    private void social(){
        Intent intent = new Intent(this, socialmedia.class);
        startActivity(intent);
    }
}
