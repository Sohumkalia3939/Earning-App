package com.whitegoldapps.cashm4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class Bill extends AppCompatActivity {
    TextView item,prices,gold,usegold,usebonus,usediscount;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String mains;
    String rate;
    String ban;
    Double a;
    String diss;
    String main;
    String main2;
    String processes;
    Double ans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        item=findViewById(R.id.youritem);
        prices=findViewById(R.id.price);
        gold=findViewById(R.id.c);
        usegold=findViewById(R.id.ga);
        usebonus=findViewById(R.id.b);
        usediscount=findViewById(R.id.d);













        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        String items=getIntent().getStringExtra("UC");

        item.setText("Your item is "+items);
        String userId;
        userId = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("admin").document("Admin");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    mains=("Price ₹ "+documentSnapshot.getDouble("uc"));
                    rate=("Gold Coins "+documentSnapshot.getDouble("goldcoinrate"));
                    main2=(""+documentSnapshot.getDouble("uc"));

                    gold.setText(rate);
                    prices.setText(mains);


                }else {
                    Log.d("tag", "onEvent: Document do not exists");
                }





            }
        });
        DocumentReference documentReference1 = fStore.collection("users").document(userId);
        documentReference1.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){

                    TextView b,d,j,no,last;
                    b=findViewById(R.id.goldcoin);
                    d=findViewById(R.id.bonus);
                    j=findViewById(R.id.t);
                    last=findViewById(R.id.total);
                    no=findViewById(R.id.noerf);
                    b.setText("You have "+documentSnapshot.getDouble("final")+" Gold Coins");
                    d.setText("You have "+documentSnapshot.getDouble("Bonus")+" Bonus Gold Coins");
                    no.setText("You have "+documentSnapshot.getDouble("erc")+" ERF Coupon");

                    ban=""+documentSnapshot.getDouble("Bonus");
                    processes=""+documentSnapshot.getString("progress");

                    String golds;
                    golds= ""+documentSnapshot.getDouble("final");
                    j.setText("You have "+documentSnapshot.getDouble("discount")+" Discount Coupon");

                    diss=""+documentSnapshot.getDouble("discount");

                    TextView sub,bon,dis,t,am;
                    sub=findViewById(R.id.subs);
                    dis=findViewById(R.id.d);
                    bon=findViewById(R.id.b);
                    t=findViewById(R.id.totalerf);
                    am=findViewById(R.id.total);
                    usegold.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Double ans;
                            Double main;
                            double i = Double.parseDouble(golds);
                            double h = Double.parseDouble(main2);

                            ans=i-h;
                            main=ans*20/100;

                            sub.setText("SUB TOTAL ₹"+ans);

                            double m = Double.parseDouble(processes);

                            if (m==100.0) {
                                t.setText("Total ERF is 0 Gold Coins");
                                am.setText("TOTAL-"+ans);
                                Map<String, Object> edited = new HashMap<>();

                                edited.put("final",ans);


                                documentReference1.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {


                                        // startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        //     finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                    }
                                });

                            }else{

                                a=ans+main;
                                t.setText("Total ERF " + main + "Gold Coins");
                                am.setText("TOTAL-"+a);

                                Map<String, Object> edited = new HashMap<>();

                                edited.put("final",a);


                                documentReference1.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {


                                        // startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        //     finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                    }
                                });
                                usegold.setEnabled(false);










                            }

                        }
                    });

                    bon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Double ans;

                            double i = Double.parseDouble(ban);
                            double h = Double.parseDouble(main2);
                            ans=i-h;
                            sub.setText("SUB TOTAL ₹"+ans);


                            double m = Double.parseDouble(processes);
                            double sohum;



                            sohum=ans*20/100;






                            if (m==100.0) {
                                t.setText("Total ERF is 0 Gold Coins");
                                am.setText("TOTAL-"+ans);
                                Map<String, Object> edited = new HashMap<>();

                                edited.put("Bonus",ans);


                                documentReference1.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {


                                        // startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        //     finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                    }
                                });

                            }else{
                                a=ans+sohum;
                                t.setText("Total ERF " + sohum + "Gold Coins");
                                am.setText("TOTAL-"+a);
                                Map<String, Object> edited = new HashMap<>();

                                edited.put("Bonus",a);


                                documentReference1.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {


                                        // startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        //     finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                    }
                                });

                            }

                            bon.setEnabled(false);














                        }
                    });

                    dis.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            double i = Double.parseDouble(diss);
                            double h = Double.parseDouble(main2);
                            ans=i-h;
                            sub.setText("SUB TOTAL ₹"+ans);
                            double m = Double.parseDouble(processes);
                            double sohum;



                            sohum=ans*20/100;






                            if (m==100.0) {
                                t.setText("Total ERF is 0 Gold Coins");
                                am.setText("TOTAL-"+ans);
                                Map<String, Object> edited = new HashMap<>();

                                edited.put("discount",ans);


                                documentReference1.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {


                                        // startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        //     finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                    }
                                });


                            }else{
                                a=ans+sohum;
                                t.setText("Total ERF " + sohum + "Gold Coins");
                                am.setText("TOTAL-"+a);



                                Map<String, Object> edited = new HashMap<>();

                                edited.put("discount",a);


                                documentReference1.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {


                                        // startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        //     finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                    }
                                });

                                dis.setEnabled(false);





                            }

                        }
                    });
                }else {
                    Log.d("tag", "onEvent: Document do not exists");
                }

            }
        });
        Button checkout;
        checkout=findViewById(R.id.out);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c();

                Toast.makeText(Bill.this, ""+a, Toast.LENGTH_SHORT).show();
            }
        });







    }
    private void c() {
        Intent intent = new Intent(this, PlaceOrder.class);
        String s=mains;

        String df=String.valueOf(a);


        intent.putExtra("Price",s);
        intent.putExtra("left",df);
        startActivity(intent);
    }
}