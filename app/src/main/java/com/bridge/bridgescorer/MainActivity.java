package com.bridge.bridgescorer;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    public static adapterTournoi adapter;
    public static List<clTournoi> tournois = new ArrayList<clTournoi> ();
    public static Integer xxNiveau;
    AdView mAdView;
    Context context;


    private static final int PERMISSION_REQUEST_CODE = 200;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        ExtendedFloatingActionButton fab = findViewById ( R.id.addTournoi );
        RecyclerView recyclerTournoi = findViewById ( R.id.recyclerTournoi );
        recyclerTournoi.setLayoutManager ( new LinearLayoutManager ( this ) );
        recyclerTournoi.setAdapter ( new adapterTournoi ( this, tournois ) );
        adapter = new adapterTournoi ( this, tournois );
        recyclerTournoi.setAdapter ( adapter );
        Toolbar myToolBar = findViewById ( R.id.mySuperToolBar );
        setSupportActionBar ( myToolBar );
        ActionBar actionBar = getSupportActionBar ();
        assert actionBar != null;


        // pub
        MobileAds.initialize(this, new OnInitializationCompleteListener () {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adViewT);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        // pub

        fab.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent ( MainActivity.this, act_add_tournoi.class );
                myIntent.putExtra ( "modif", false );
                MainActivity.this.startActivity ( myIntent );
            }
        } );
        context=this;



        tournois.clear ();
        clDataBase xxDatabase = new clDataBase ( context );
        Cursor cursor = xxDatabase.listetournois ();
        if (cursor.getCount () == 0) return;
        while (cursor.moveToNext ()) {
            Date maDate = new Date (cursor.getLong(1));
            tournois.add ( new clTournoi (
                    cursor.getString ( 2 ),
                    maDate,
                    cursor.getInt ( 3 ),
                    cursor.getString ( 4 ),
                    cursor.getString ( 5 ),
                    cursor.getInt ( 6 ),
                    (cursor.getInt ( 7 ) == 1),
                    cursor.getInt ( 0 )

            ) );
        }

    }


    protected void onPostResume() {

        super.onPostResume ();
        tournois.clear ();
        clDataBase xxDatabase = new clDataBase ( context );
        Cursor cursor = xxDatabase.listetournois ();
        if (cursor.getCount () == 0) return;
        while (cursor.moveToNext ()) {
            Date maDate = new Date (cursor.getLong(1));
            tournois.add ( new clTournoi (
                    cursor.getString ( 2 ),
                    maDate,
                    cursor.getInt ( 3 ),
                    cursor.getString ( 4 ),
                    cursor.getString ( 5 ),
                    cursor.getInt ( 6 ),
                    (cursor.getInt ( 7 ) == 1),
                    cursor.getInt ( 0 )

            ) );
        }
        adapter.notifyDataSetChanged ();
    }






   public static void updateUI()
   {
       adapter.notifyDataSetChanged ();

   }

}