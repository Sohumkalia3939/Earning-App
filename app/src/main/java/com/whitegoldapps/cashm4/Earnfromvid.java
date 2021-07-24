package com.whitegoldapps.cashm4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import dev.shreyaspatil.MaterialDialog.MaterialDialog;
import dev.shreyaspatil.MaterialDialog.interfaces.DialogInterface;

import static com.whitegoldapps.cashm4.R.*;


public class Earnfromvid extends AppCompatActivity  {
    RewardedVideoAd rewardedVideoAd;

    private CardView videocard2,videacard3,share;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    private RewardedVideoAd mRewardedVideoAd;
private int l=0;
    TextView c;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;
    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_earnfromvid);


        Button r;
        r=findViewById(id.Rateus);



        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.yourURL.com"));
                startActivity(intent);
            }
        });










        videocard2=findViewById(id.card2);
share=findViewById(R.id.carf);

        c=findViewById(id.clicks);
        videacard3=findViewById(id.card3);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });

share.setOnClickListener(new View.OnClickListener() {
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









        videacard3.setOnClickListener(new View.OnClickListener() {






            @Override
            public void onClick(View v) {
l++;
c.setText("TAPTAP ads clicks :"+Integer.toString(l)+"/100");

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    int coinval=0;
                    coinval = coinval + 2;
                  //  coinval++;
                    if (l>100){
refile();
                        Toast.makeText(Earnfromvid.this, "Tap click to 0", Toast.LENGTH_SHORT).show();
                    }
if (l==10) {


    DocumentReference documentReference = FirebaseFirestore.getInstance()
            .collection("users")
            .document(user.getUid());
    Map<String, Object> edited = new HashMap<>();
    edited.put("money", FieldValue.increment(coinval));

    documentReference.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
        @Override
        public void onSuccess(Void aVoid) {
            Toast.makeText(Earnfromvid.this, "2 silver Coins credited", Toast.LENGTH_SHORT).show();
            // startActivity(new Intent(getApplicationContext(),MainActivity.class));
            //     finish();
        }

    });
}


                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }


                if (fAuth.getCurrentUser() != null) {

                }
                else{
                    o();



                }

















            }
        });
        ImageView back1=findViewById(id.back);
        //fAuth = FirebaseAuth.getInstance();
        //FirebaseUser fuser = fAuth.getCurrentUser();

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = fAuth.getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference();

//        if (fAuth.getCurrentUser() != null) {
//
//        }
//        else{
//            Intent df;
//            df=new Intent(this,RegisterActivity.class);
//            startActivity(df);
//        }










//        }



//        if (fAuth.getCurrentUser() != null) {
//            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            finish();
//        }
        ;
//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {}
//        });
//        AdRequest adRequest = new AdRequest.Builder().build();
//
//        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest,
//                new InterstitialAdLoadCallback() {
//                    @Override
//                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
//                        // The mInterstitialAd reference will be null until
//                        // an ad is loaded.
//                        mInterstitialAd = interstitialAd;
//                        Log.i(TAG, "onAdLoaded");
//                    }
//
//                    @Override
//                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//                        // Handle the error
//                        Log.i(TAG, loadAdError.getMessage());
//                        mInterstitialAd = null;
//                    }
//                });




        videocard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



//                if (mInterstitialAd.isLoaded()) {
//                    mInterstitialAd.show();
//                    int coinval=0;
//                    coinval = coinval + 1;
//
//                    DocumentReference documentReference=FirebaseFirestore.getInstance()
//                            .collection("users")
//                            .document(user.getUid());
//                    Map<String,Object> edited = new HashMap<>();
//
//                    edited.put("money", FieldValue.increment(coinval));
//                    documentReference.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            Toast.makeText(Earnfromvid.this, "Coin credited", Toast.LENGTH_SHORT).show();
//                            // startActivity(new Intent(getApplicationContext(),MainActivity.class));
//                            //     finish();
//
//                        }
//                    });
//
//
//
//                } else {
//                    Log.d("TAG", "The interstitial wasn't loaded yet.");
//                }
//
//
//                if (fAuth.getCurrentUser() != null) {
//
//                }
//                else{
//                    o();
//                }




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

    private void o(){
//        Intent sa;
//        sa=new Intent(this,RegisterActivity.class);
//        startActivity(sa);
        MaterialDialog mDialog = new MaterialDialog.Builder(this)
                .setMessage("Bro you will need to Signup so we can process your silver coins")
                .setCancelable(false)
                .setPositiveButton("Signup/Login with facebook", new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        un();
                       }
                })
                .setNegativeButton("Signup Rewards", new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        re();
                       // dialogInterface.dismiss();

                    }
                })
                .build();

        // Show Dialog
        mDialog.show();


    }

private void refile(){
    Intent ssd;
    ssd=new Intent(this,Earnfromvid.class);
    startActivity(ssd);



}
private void un(){
        Intent sa;
        sa=new Intent(this,RegisterActivity.class);
        startActivity(sa);
}



    private void re(){
        Intent sas;
        sas=new Intent(this,Wallet.class);
        startActivity(sas);
    }













}