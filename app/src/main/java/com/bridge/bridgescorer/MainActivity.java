package com.bridge.bridgescorer;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    //private ActivityMainBinding binding;
    adapterTournoi adapter;
    public static List<tournoi> tournois = new ArrayList<tournoi> ();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );


        setContentView ( R.layout.activity_main );
        FloatingActionButton fab = findViewById ( R.id.addTournoi );
        RecyclerView recyclerTournoi = findViewById ( R.id.recyclerTournoi );
        recyclerTournoi.setLayoutManager ( new LinearLayoutManager ( this ) );
        recyclerTournoi.setAdapter ( new adapterTournoi ( getApplicationContext (), tournois ) );
        //recyclerTournoi.addItemDecoration(dividerItemDecoration);
        adapter = new adapterTournoi ( this, tournois );
        //adapter.setClickListener(this);
        recyclerTournoi.setAdapter ( adapter );

        fab.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent ( MainActivity.this, stpTournoi.class );
                MainActivity.this.startActivity ( myIntent );
                dataBase db = new dataBase ( MainActivity.this );
            }
        } );
        dataBase xxDatabase = new dataBase (  this);
        Cursor cursor =xxDatabase.listetournois ();
        if (cursor.getCount ()==0) return;

        while (cursor.moveToNext ())
        {
            tournois.add ( new tournoi ( cursor.getString ( 1 ),cursor.getString ( 2 ), cursor.getInt ( 3 ),cursor.getString ( 4 ),cursor.getString ( 5 )  ) );
        }


    }
    @Override
    protected void onPostResume() {
        adapter.notifyDataSetChanged ();
        super.onPostResume ();
    }
    public void addListTournoi(String xxLibelle, String xxDate, Integer xxNbDonnes, String xxAdversaires, String xxPartenaire) {
        tournois.add ( new tournoi ( xxLibelle, xxDate, xxNbDonnes, xxAdversaires, xxPartenaire ) );
    }


}