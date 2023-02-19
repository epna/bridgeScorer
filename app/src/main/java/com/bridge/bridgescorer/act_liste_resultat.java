package com.bridge.bridgescorer;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class act_liste_resultat extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    public static adapterResultat myAdapter;
    public static List<clResultat> resultats = new ArrayList<clResultat> ();
    public static Boolean cacher = false;
    Toolbar myToolBar;
    AdView mAdView;
    public Integer idTournoi;
    public static Integer nbDonnes;
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId ()) {
            case R.id.hideShow:
                cacher = !cacher;
                if (cacher) {

                } else {

                }
                myAdapter.notifyDataSetChanged ();
                break;
            default:
                return super.onOptionsItemSelected ( item );
        }
        return true;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.liste_resultat );

        ExtendedFloatingActionButton fab2 = findViewById ( R.id.addResultat );
        RecyclerView recyclerresultat = findViewById ( R.id.recyclerResultat );
        recyclerresultat.setLayoutManager ( new LinearLayoutManager ( this ) );
        recyclerresultat.setAdapter ( new adapterResultat ( this, resultats ) );

        MobileAds.initialize ( this, new OnInitializationCompleteListener () {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        } );
        mAdView = findViewById ( R.id.adViewR );
        AdRequest adRequest = new AdRequest.Builder ().build ();
        mAdView.loadAd ( adRequest );

        myToolBar = findViewById ( R.id.mySuperToolBar );
        setSupportActionBar ( myToolBar );
        ActionBar actionBar = getSupportActionBar ();
        actionBar.setDisplayHomeAsUpEnabled ( true );

        myAdapter = new adapterResultat ( this, resultats );
        recyclerresultat.setAdapter ( myAdapter );

        Bundle extras = getIntent ().getExtras ();
        clTournoi monTournoi = (clTournoi) extras.getSerializable ( "peTournoi" );
        idTournoi = monTournoi.touId;
        fab2.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent ( act_liste_resultat.this, act_add_resultat.class );
                myIntent.putExtra ( "peTournoi", idTournoi);
                act_liste_resultat.this.startActivity ( myIntent );
                //view.setVisibility(View.GONE);
            }
        } );






        nbDonnes= monTournoi.touNbDonnes;
        String temp = monTournoi.touId.toString ();
        String tempLib = monTournoi.touLibelle;


        SimpleDateFormat format = new SimpleDateFormat ( "E dd MMM yyyy" );
        String tempDate = format.format ( monTournoi.gettouDate ());

        String tempSeance = String.valueOf ( monTournoi.touSeance);
        TextView ligne1 = findViewById ( R.id.TxtDetailTournoi1 );
        //TextView ligne2 = findViewById ( R.id.TxtDetailTournoi2 );
        ligne1.setText ( tempLib + "\n" + tempDate + "  Séance : " + tempSeance);
        //ligne2.setText ( tempDate + "  Séance : " + tempSeance );


        resultats.clear ();
        // Remplir recycler resultats
        clDataBase xxDatabase = new clDataBase ( this );
        Cursor cursor = xxDatabase.listeResultat ( Integer.valueOf ( temp ) );
        if (cursor.getCount () == 0) return;
        while (cursor.moveToNext ()) {
            resultats.add ( new clResultat (
                    cursor.getInt ( 0 ), // id tournoi
                    cursor.getInt ( 1 ), // numdonne
                    cursor.getInt ( 2 ), // declarant
                    cursor.getInt ( 3 ), // niveau
                    cursor.getInt ( 4 ), // couleur
                    cursor.getInt ( 5 ), // contre
                    cursor.getInt ( 6), // resultat
                    cursor.getInt ( 7 ), // entCouleur
                    cursor.getInt ( 8 ) // entNiveau

            ) );
        }
    }

    public void addListresultat(clResultat aCreer) {
        resultats.add ( aCreer );
    }
    public void updateListresultat(Integer idAdapter, clResultat aModifier) {

        resultats.set(idAdapter,aModifier);
    }

    // creates the comparator for comparing stock value
    class sortIdTournoi implements Comparator<clResultat> {
        @Override
        public int compare(clResultat t0, clResultat t1) {
            if (t0.resNumeroDonne == t1.resNumeroDonne)
                return 0;
            else if (t0.resNumeroDonne > t1.resNumeroDonne)
                return 1;
            else return -1;
        }
    }

    protected void onPostResume() {
        super.onPostResume ();
        Collections.sort ( resultats, new sortIdTournoi () );
        cacher = false;
        myAdapter.notifyDataSetChanged ();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater ();
        inflater.inflate ( R.menu.menulisteresultats, menu );
        return true;
    }


    public static  void updateUI() {
    }


}