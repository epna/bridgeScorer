package com.bridge.bridgescorer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class dataBase extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME="bridgeScrorer.db";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="tournois";

    private static final String col_ID = "_id";
    private static final String col_Date = "dateTournoi";
    private static final String col_Libelle = "libelle";
    private static final String col_seance = "libelle";
    private static final String col_position = "libelle";
    private static final String col_NbDonnes = "nbDonnes";
    private static final String col_Adversaires = "adversaires";
    private static final String col_Partenaire = "partenaire";

    public dataBase(@Nullable Context context) {
        super ( context, DATABASE_NAME, null, DATABASE_VERSION );
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String Query = "CREATE TABLE " + TABLE_NAME +
                "(" + col_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,  "
                + col_Date + " DATE,  "
                + col_Libelle + " TEXT, "
                + col_NbDonnes + " INTEGER, "
                + col_Adversaires + "  TEXT, "
                + col_Partenaire + " TEXT, "
                + col_seance +"  INTEGER,"
                + col_position + " BOOLEAN )";
        db.execSQL ( Query );



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }

    public void addTournoi(Date xxDate,String xxLibelle, Integer xxNbDonnes, String xxAdversaires, String xxPartenaire, Boolean xxPosition, Integer xxSeance){

        SQLiteDatabase db = this.getWritableDatabase ();
        ContentValues myContent = new ContentValues ();
        myContent.put(col_Date, String.valueOf ( xxDate ) );
        myContent.put(col_Libelle, xxLibelle);
        myContent.put(col_NbDonnes, xxNbDonnes);
        myContent.put(col_Adversaires, xxAdversaires);
        myContent.put(col_Partenaire, xxPartenaire);
        myContent.put(col_position,xxPosition);
        myContent.put(col_seance,xxSeance);

        long result = db.insert ( TABLE_NAME,null,myContent );
        if (result !=-1) {
            Toast.makeText ( context, "Tournoi créé ", Toast.LENGTH_SHORT ).show ();
        }
        else
        {
            Toast.makeText ( context, "Erreur création tournoi", Toast.LENGTH_SHORT ).show ();
        }

        SimpleDateFormat format = new SimpleDateFormat("DD dd MMM yyyy");
        String strDate = format.format(xxDate);
        MainActivity cccc = new MainActivity ();
        cccc.addListTournoi (xxLibelle, strDate ,xxNbDonnes,xxAdversaires,xxPartenaire  );
    }
    public Cursor listetournois ()
    {
        SQLiteDatabase db = this.getReadableDatabase ();
        Cursor cursor = db.rawQuery ( "Select * from tournois order by _id DESC " ,null);
        return cursor;
    }


}
