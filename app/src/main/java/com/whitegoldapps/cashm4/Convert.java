package com.whitegoldapps.cashm4;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.storage.FirebaseStorage;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.Nullable;

public class Convert extends AppCompatActivity {
    Button c;
    FirebaseUser user;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    TextView check;
    String userId;
    Double main;
    Double mainss;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);
        c=findViewById(R.id.convert);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        if (fAuth.getCurrentUser() != null) {
            userId = fAuth.getCurrentUser().getUid();
            TextView coins;
            coins=findViewById(R.id.coins);

            DocumentReference reference = fStore.collection("users").document(userId);
            reference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {

                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    if(documentSnapshot.exists()){





                        mainss=documentSnapshot.getDouble("money");
                        coins.setText("You have "+documentSnapshot.getDouble("money")+" Silver Coins Today");

//                    double result = main/mainss;
//                    double six=result*60/100;


//                    Map<String, Object> edited = new HashMap<>();
//                    edited.put("final",six);
//                    Toast.makeText(Convert.this, Double.toString(six),Toast.LENGTH_LONG).show();
//
//                    reference.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            Toast.makeText(Convert.this, "2 silver Coins credited", Toast.LENGTH_SHORT).show();
//                            // startActivity(new Intent(getApplicationContext(),MainActivity.class));
//                            //     finish();
//                        }
//
//                    });




                    }else {
                        Log.d("tag", "onEvent: Document do not exists");
                    }

                }




            });

            DocumentReference documentReference = fStore.collection("admin").document("Admin");
            documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {

                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    if(documentSnapshot.exists()){

                        main=documentSnapshot.getDouble("div");


                    }else {
                        Log.d("tag", "onEvent: Document do not exists");
                    }

                }

            });


            c.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    double result = main/mainss;
                    double six=result*60/100;
                    double bon=result*40/100;

                    double dis=result*10/100;

                    Map<String, Object> edited = new HashMap<>();

                    edited.put("final",six);
                    edited.put("Bonus",bon);
                    edited.put("discount",dis);

                    //       Toast.makeText(Convert.this, Double.toString(six),Toast.LENGTH_LONG).show();


if(six==0.0){
    Toast.makeText(Convert.this, "No money in account", Toast.LENGTH_SHORT).show();
}

                    reference.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                                Toast.makeText(Convert.this, six + " Coins has been deposited to our account", Toast.LENGTH_SHORT).show();

                            // startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            //     finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Convert.this, "You have 0 coins in your account", Toast.LENGTH_SHORT).show();
                        }
                    });


                }



            });

        }

        else{
            o();



        }
        check=findViewById(R.id.d);
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH:mm a");
// you can get seconds by adding  "...:ss" to it
        date.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
        String localTime = date.format(currentLocalTime);
        LocalTime time1 = LocalTime.parse("17:13:00");
        LocalTime time2 = LocalTime.parse("17:15:00");
        c.setEnabled(false);
        String Scan_hour1 = "20:30 PM";
        String Scan_hour2 = "20:31 PM";
        String Scan_hour3 = "20:32 PM";
        String Scan_hour4 = "20:33 PM";
        String Scan_hour5 = "20:34 PM";
        String Scan_hour6 = "20:35 PM";
        String Scan_hour7 = "20:36 PM";
        String Scan_hour8 = "20:37 PM";
        String Scan_hour9 = "20:38 PM";
        String Scan_hour10 = "20:39 PM";
        String Scan_hour11 = "20:40 PM";
        String Scan_hour12 = "20:41 PM";
        String Scan_hour13 = "20:42 PM";
        String Scan_hour14 = "20:43 PM";
        String Scan_hour15 = "20:44 PM";

        String Scan_hour16 = "20:45 PM";
        String Scan_hour17 = "20:46 PM";
        String Scan_hour18 = "20:47 PM";
        String Scan_hour19 = "20:48 PM";
        String Scan_hour20 = "20:49 PM";
        String Scan_hour21= "20:54 PM";
        String Scan_hour22 = "20:55 PM";
        String Scan_hour23 = "20:56 PM";
        String Scan_hour24 = "20:57 PM";
        String Scan_hour25 = "20:58 PM";
        String Scan_hour26 = "20:59 PM";











        if (localTime.equals(Scan_hour1)) {
            c.setEnabled(true);
        }
        else if (localTime.equals(Scan_hour2)) {
            c.setEnabled(true);
        }
        else  if (localTime.equals(Scan_hour3)) {
            c.setEnabled(true);
        }
        else  if (localTime.equals(Scan_hour4)) {
            c.setEnabled(true);
        }
        else if (localTime.equals(Scan_hour5)) {
            c.setEnabled(true);
        }
        else if (localTime.equals(Scan_hour6)) {
            c.setEnabled(true);
        }
        else if (localTime.equals(Scan_hour7)) {
            c.setEnabled(true);
        }
        else if (localTime.equals(Scan_hour8)) {
            c.setEnabled(true);
        }
        else if (localTime.equals(Scan_hour9)) {
            c.setEnabled(true);
        }
        else if (localTime.equals(Scan_hour10)) {
            c.setEnabled(true);
        }
        else if (localTime.equals(Scan_hour11)) {
            c.setEnabled(true);
        }
        else if (localTime.equals(Scan_hour12)) {
            c.setEnabled(true);
        }
        else if (localTime.equals(Scan_hour13)) {
            c.setEnabled(true);
        } else if (localTime.equals(Scan_hour14)) {
            c.setEnabled(true);
        } else if (localTime.equals(Scan_hour15)) {
            c.setEnabled(true);
        } else if (localTime.equals(Scan_hour16)) {
            c.setEnabled(true);
        } else if (localTime.equals(Scan_hour17)) {
            c.setEnabled(true);
        } else if (localTime.equals(Scan_hour18)) {
            c.setEnabled(true);
        } else if (localTime.equals(Scan_hour19)) {
            c.setEnabled(true);
        } else if (localTime.equals(Scan_hour20)) {
            c.setEnabled(true);
        } else if (localTime.equals(Scan_hour21)) {
            c.setEnabled(true);
        } else if (localTime.equals(Scan_hour22)) {
            c.setEnabled(true);
        } else if (localTime.equals(Scan_hour23)) {
            c.setEnabled(true);
        }
        else if (localTime.equals(Scan_hour24)) {
            c.setEnabled(true);
        } else if (localTime.equals(Scan_hour25)) {
            c.setEnabled(true);
        } else if (localTime.equals(Scan_hour26)) {
            c.setEnabled(true);
        }
        else {
            c.setEnabled(true);
        }












        //Toast.makeText(Convert.this, Double.toString(result),Toast.LENGTH_LONG).show();






    }

    private void o() {
        Intent ssd;
        ssd=new Intent(this,RegisterActivity.class);
        startActivity(ssd);
    }

}