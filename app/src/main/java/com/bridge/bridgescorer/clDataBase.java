package com.bridge.bridgescorer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class clDataBase extends SQLiteOpenHelper {

    private final Context context;
    private static final String DATABASE_NAME = "bridgeScrorer.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "tournois";
    private static final String col_ID = "_id";
    private static final String col_Date = "dateTournoi";
    private static final String col_Libelle = "libelle";
    private static final String col_seance = "seance";
    private static final String col_position = "position";
    private static final String col_NbDonnes = "nbDonnes";
    private static final String col_Adversaires = "adversaires";
    private static final String col_Partenaire = "partenaire";


    private static final String TABLE_NAME2 = "resultats";
    private static final String col2_id = "_id";
    private static final String col2_NumDonne = "numDonne";
    private static final String col2_Declarant = "declarant";
    private static final String col2_Niveau = "niveau";
    private static final String col2_Couleur = "couleur";
    private static final String col2_Contre = "contre";
    private static final String col2_Resultat = "resultat";
    private static final String col2_entCouleur = "entCouleur";
    private static final String col2_entNiveau = "entNiveau";


    public clDataBase(@Nullable Context context) {
        super ( context, DATABASE_NAME, null, DATABASE_VERSION );
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String Query = "CREATE TABLE " + TABLE_NAME +
                "(" + col_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,  "
                + col_Date + " DATE,  "
                + col_Libelle + " TEXT, "
                + col_NbDonnes + " INTEGER, "
                + col_Adversaires + "  TEXT, "
                + col_Partenaire + " TEXT, "
                + col_seance + "  INTEGER,"
                + col_position + " BOOLEAN )";
        db.execSQL ( Query );

        String Query2 = "CREATE TABLE " + TABLE_NAME2 +
                "(" + col2_id + " INTEGER,"
                + col2_NumDonne + " INTEGER,  "
                + col2_Declarant + " INTEGER, "
                + col2_Niveau + " INTEGER, "
                + col2_Couleur + "  INTEGER, "
                + col2_Contre + " INTEGER, "
                + col2_Resultat + "  INTEGER,"
                + col2_entCouleur + " INTEGER, "
                + col2_entNiveau + "  INTEGER)";
        db.execSQL ( Query2 );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public long addResultat(clResultat myResultat) {
        SQLiteDatabase db = this.getWritableDatabase ();
        ContentValues myContent = setValueResultat ( myResultat );
        long result = db.insert ( TABLE_NAME2, null, myContent );
        if (result!=-1) {
            act_liste_resultat myActivity = new act_liste_resultat ();
            myActivity.addListresultat ( myResultat );
        }
        return result;
    }

    public long updateResultat(Integer positionAdapter, clResultat myResultat) {
        SQLiteDatabase db = this.getWritableDatabase ();
        ContentValues myContent = setValueResultat ( myResultat );
        long result = db.update ( TABLE_NAME2, myContent, col_ID + "=" + myResultat.getResIdTounois ()  + " and " + col2_NumDonne +" = " +  myResultat.getResNumeroDonne (), null );
        if (result != -1) {
            act_liste_resultat myActivity = new act_liste_resultat ();
            myActivity.updateListresultat ( positionAdapter,myResultat);
        }
        return result;
    }
public ContentValues  setValueResultat(clResultat svResultat)
{
    ContentValues myContent = new ContentValues ();
    myContent.put ( col2_id, svResultat.getResIdTounois () );
    myContent.put ( col2_NumDonne, svResultat.getResNumeroDonne () );
    myContent.put ( col2_Declarant, svResultat.getResDeclarant () );
    myContent.put ( col2_Niveau, svResultat.getResNiveau () );
    myContent.put ( col2_Couleur, svResultat.getResCouleur () );
    myContent.put ( col2_Contre, svResultat.getResContre () );
    myContent.put ( col2_Resultat, svResultat.getResResultat () );
    myContent.put ( col2_entCouleur, svResultat.getReCouleurEntame () );
    myContent.put ( col2_entNiveau, svResultat.getResNiveauEntame () );
    return myContent;
}

    public long addTournoi(clTournoi myTournoi) {
        SQLiteDatabase db = this.getWritableDatabase ();
        ContentValues myContent = setValueTournoi ( myTournoi  );
        long result = db.insert ( TABLE_NAME, null, myContent );
        if (result != -1) {
            Cursor res = db.rawQuery ( "select last_insert_rowid() as lastID from tournois", null );
            res.moveToFirst ();
            String temp = res.getString ( 0 );

            Integer xxId = Integer.valueOf ( temp );
            myTournoi.setId ( xxId );
            MainActivity myActivity = new MainActivity ();
            MainActivity.tournois.add(myTournoi);
        }
        return result;
    }
    public ContentValues setValueTournoi(clTournoi myTournoi)
    {
        ContentValues myContent = new ContentValues ();
        long xxDateLong = myTournoi.gettouDate ().getTime ();
        myContent.put ( col_Date, xxDateLong );
        myContent.put ( col_Libelle, myTournoi.gettouLibelle () );
        myContent.put ( col_NbDonnes, myTournoi.gettouNbDonnes () );
        myContent.put ( col_Adversaires, myTournoi.gettouAdversaires () );
        myContent.put ( col_Partenaire, myTournoi.gettouPartenaires () );
        myContent.put ( col_position, myTournoi.getTouPosition () );
        myContent.put ( col_seance, myTournoi.gettouSeance () );
        return myContent;
    }

    public long updateTournoi(Integer positionAdapter, clTournoi myTournoi) {
        SQLiteDatabase db = this.getWritableDatabase ();
        ContentValues myContent = new ContentValues ();
        long xxDateLong = myTournoi.gettouDate ().getTime ();
        myContent.put ( col_Date, xxDateLong );
        myContent.put ( col_Libelle, myTournoi.gettouLibelle () );
        myContent.put ( col_NbDonnes, myTournoi.gettouNbDonnes () );
        myContent.put ( col_Adversaires, myTournoi.gettouAdversaires () );
        myContent.put ( col_Partenaire, myTournoi.gettouPartenaires () );
        myContent.put ( col_position, myTournoi.getTouPosition () );
        myContent.put ( col_seance, myTournoi.gettouSeance () );
        long result = db.update ( TABLE_NAME, myContent, col_ID + "=" + myTournoi.touId, null );
        if (result != -1) {
            MainActivity.tournois.set(positionAdapter,myTournoi);
        }
        return result;

    }


    public Cursor listeResultat(Integer myId) {
        SQLiteDatabase db = this.getReadableDatabase ();
        Cursor cursor =  db.rawQuery ( "Select * from resultats where _id = " + myId.toString () + " ORDER by numDonne", null );
        return cursor;
        // SELECT * FROM resultats  where _id =1 order by numDonne
    }

    public Cursor listetournois() {
        SQLiteDatabase db = this.getReadableDatabase ();
        Cursor cursor = db.rawQuery ( "Select * from tournois order by _id DESC", null );
        return cursor;
    }

    public void deleteTournoi(Integer myId) {
        SQLiteDatabase db = this.getWritableDatabase ();
        db.execSQL ( "Delete from resultats where _id = " + myId.toString () + ";" );
        db.execSQL ( "Delete from tournois where _id = " + myId + "; " );
    }

    public void deleteResultat(Integer tournoi, Integer donne ) {
        SQLiteDatabase db = this.getWritableDatabase ();
        db.execSQL ( "Delete from resultats where _id = " + tournoi.toString () + " AND numDonne = " + donne.toString () +";" );
    }
}
