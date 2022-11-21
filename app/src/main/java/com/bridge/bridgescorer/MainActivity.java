package com.bridge.bridgescorer;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bridge.bridgescorer.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );

        RecyclerView recyclerTournoi = findViewById ( R.id.recyclerTournoi );
        List<tournoi>tournois = new ArrayList<tournoi> ();
        tournois.add (new tournoi ( "libelle1", "01-02-2022",32,"koko","mimi" )  );
        tournois.add (new tournoi ( "libelle1", "01-02-2022",24,"koko","mimi2" )  );
        recyclerTournoi.setLayoutManager ( new LinearLayoutManager ( this ) );
        recyclerTournoi.setAdapter (new adapterTournoi ( getApplicationContext () ,tournois));



    }
}