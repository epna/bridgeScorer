package com.bridge.bridgescorer;


import static android.os.Environment.DIRECTORY_DOWNLOADS;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.ColorLong;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;


public class recyclerTournoi extends RecyclerView.ViewHolder {
    TextView trnDatevv, trnLibelle, trnNbDonnes, trnAdversaires, trnPartenaires, trnSeance, trnPosition;
    ImageButton btnPartage, btnEditTournoi;
    Integer idTournoi;

    public recyclerTournoi(@NonNull View itemView) {
        super ( itemView );


        trnLibelle = itemView.findViewById ( R.id.txtLibelle );
        trnNbDonnes = itemView.findViewById ( R.id.txtNbDonnes );
        trnDatevv = itemView.findViewById ( R.id.txtance );
        trnAdversaires = itemView.findViewById ( R.id.txtAdvversaires );
        trnPartenaires = itemView.findViewById ( R.id.txtPartenaire );
        trnSeance = itemView.findViewById ( R.id.txtSeance );
        trnPosition = itemView.findViewById ( R.id.txtPosition );
        btnPartage = itemView.findViewById ( R.id.btnPartage );
        btnEditTournoi = itemView.findViewById ( R.id.editTournoi );
        itemView.setOnLongClickListener ( new View.OnLongClickListener () {
            @Override
            public boolean onLongClick(View view) {
                int position = xxposition(idTournoi);
                AlertDialog.Builder builder = new AlertDialog.Builder ( view.getContext () );

                SimpleDateFormat format = new SimpleDateFormat ( "E dd MMM yyyy" );
                String tempDate = format.format ( MainActivity.tournois.get ( position ).gettouDate ());

                String question = view.getResources ().getString ( R.string.dialog_start_game ) + MainActivity.tournois.get ( position ).touLibelle + " " + tempDate;
                builder.setMessage ( question )
                        .setPositiveButton ( R.string.start, new DialogInterface.OnClickListener () {
                            public void onClick(DialogInterface dialog, int id) {
                                String libelle = MainActivity.tournois.get ( position ).touLibelle + " " +
                                        tempDate + " supprimé";
                                Snackbar.make ( view,libelle, Snackbar.LENGTH_LONG )
                                        .setTextColor (Color.YELLOW )
                                        .setBackgroundTint ( Color.DKGRAY )
                                        .setAnchorView ( R.id.addTournoi ).show ();

                                clDataBase db = new clDataBase ( view.getContext () );
                                db.deleteTournoi ( MainActivity.tournois.get ( position ).gettouId () );
                                MainActivity.tournois.remove ( position );

                                MainActivity.updateUI ();
                            }
                        } )
                        .setNegativeButton ( R.string.cancel, new DialogInterface.OnClickListener () {
                            public void onClick(DialogInterface dialog, int id) {
                                Snackbar.make ( view,view.getResources ().getString( R.string.deletedResultat), Snackbar.LENGTH_LONG )
                                        .setAnchorView ( R.id.addTournoi )
                                        .setTextColor (Color.YELLOW ).
                                        setBackgroundTint ( Color.DKGRAY )
                                        .show ();
                            }
                        } );
                AlertDialog alert = builder.create ();
                alert.show ();


                return true;
            }
        } );

        itemView.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
               voirListeResultat(view);

            }
        } );
        btnPartage.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                act_permission myActPermission = new act_permission ();
                if (myActPermission.checkPermission ( view)) {

                } else {
                    Activity activity = (Activity) view.getContext ();
                    myActPermission.requestPermission (activity);
                }            generatePDF ( view, idTournoi );
                        }
        } );

        btnEditTournoi.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                voirListeResultat(view);

            }
        } );

/*
        btnDelete.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent (view.getContext (),act_add_tournoi.class);
                clTournoi tournoiAmodifier = MainActivity.tournois.stream ()
                        .filter ( monTournoi -> monTournoi.gettouId () == idTournoi )
                        .findFirst ()
                        .orElse ( null );
                myIntent.putExtra ( "modif",true );
                myIntent.putExtra ( "idTournoi" , idTournoi);
                myIntent.putExtra ( "clTournoi",tournoiAmodifier);
                myIntent.putExtra ( "positionAdapter",getBindingAdapterPosition () );
                view.getContext ().startActivity ( myIntent );

            }
        } );

*/





    }

    private void voirListeResultat(View view) {
        Intent myIntent = new Intent ( view.getContext (), act_liste_resultat.class );
        myIntent.putExtra ( "nbDonne", trnNbDonnes.getText ().toString () );


        clTournoi tournoiAmodifier = MainActivity.tournois.stream ()
                .filter ( monResultat -> monResultat.touId == idTournoi )
                .findFirst ()
                .orElse ( null );
        myIntent.putExtra ("peTournoi", tournoiAmodifier );

        view.getContext ().startActivity ( myIntent );
    }


    Integer xxposition(Integer xxTournoi)
    {
        for(int i = 0; i < MainActivity.tournois.size(); i++){
            if (MainActivity.tournois.get(i).gettouId ().equals(xxTournoi)) {
                return i;
            }
        }
        return -1;
    }



    //=====================================================================
    public void generatePDF(View view, Integer paramIdTournoi) {


        clTournoi xxx = MainActivity.tournois.stream ()
                .filter ( monTournoi -> monTournoi.gettouId () == paramIdTournoi )
                .findFirst ()
                .orElse ( null );
        clDataBase myDB = new clDataBase (view.getContext()  );
        Cursor myCursor = myDB.listeResultat ( paramIdTournoi );
        if (myCursor.getCount () == 0) {
            Snackbar snackbar1 = Snackbar.make(view, R.string.noResultToShare, Snackbar.LENGTH_SHORT)
                    .setAnchorView ( R.id.adViewT )
                    .setTextColor (Color.YELLOW )
                    .setBackgroundTint ( Color.DKGRAY );
            snackbar1.show();
            return;
        }
        int pageHeight = 1120;
        int pagewidth = 792;
        int start =50;
        PdfDocument pdfDocument = new PdfDocument ();
        Paint paint = new Paint ();
        Paint title = new Paint ();
        PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder ( pagewidth, pageHeight, 1 ).create ();
        PdfDocument.Page myPage = pdfDocument.startPage ( mypageInfo );
        Canvas canvas = myPage.getCanvas ();

        title.setTypeface ( Typeface.defaultFromStyle ( Typeface.NORMAL ) );
        title.setColor ( ContextCompat.getColor ( view.getContext (), R.color.black ) );
        title.setTextSize ( 25 );
        title.setTextAlign ( Paint.Align.CENTER );
        canvas.drawText ( xxx.touLibelle, pagewidth/2, start, title );

        title.setTextSize ( 15 );
        String titre = xxx.dateFormatte () + " séance : " + xxx.touSeance + " part. " + xxx.touPartenaires + " adv " + xxx.touAdversaires;
        canvas.drawText ( titre, pagewidth/2, start+30, title );

        String[] arrResultats = view.getResources ().getStringArray ( R.array.resultat );
        String[] arrDeclarant = view.getResources ().getStringArray ( R.array.declarant );
        String[] arrCouleur = view.getResources ().getStringArray ( R.array.couleur );
        String[] arrContre = view.getResources ().getStringArray ( R.array.contre );
        String[] arrCarte = view.getResources ().getStringArray ( R.array.Carte );

        title.setTextSize ( 15 );
        title.setTextAlign ( Paint.Align.LEFT );
        Float yy=110f;

        myCursor.moveToFirst ();
        while (!myCursor.isAfterLast ())
        {
            //==================================================================================
            clContrat xxContrat = new clContrat (
                    myCursor.getInt ( 1), // num donne
                    myCursor.getInt ( 2), // declarant
                    myCursor.getInt ( 3), // niveau
                    myCursor.getInt ( 4 ),//Couleur
                    myCursor.getInt ( 5), // conte
                    myCursor.getInt ( 6 ),view.getContext () // resultat
            );

            yy+=40;
            // rectangle
            paint.setStyle ( Paint.Style.STROKE );
            paint.setColor ( Color.rgb(220, 220, 220) );
            canvas.drawRoundRect(new RectF (120, yy-15, 660, yy+15), 6, 6, paint);

            paint.setColor ( R.color.black );

            // numéro donne
            String tmp = String.valueOf ( myCursor.getInt ( 1 ) );
            canvas.drawText ( tmp,150,yy,paint );



            // Contrat
            tmp = myCursor.getInt ( 3 ) + arrCouleur[myCursor.getInt ( 4 )] + arrContre[myCursor.getInt ( 5 )];
            tmp += arrResultats[myCursor.getInt ( 6 )] + " par " + arrDeclarant[myCursor.getInt ( 2 )];
            canvas.drawText ( tmp,200,yy,paint );

            //score
            Integer score = xxContrat.computeScore ();
            paint.setTextAlign ( Paint.Align.RIGHT );
            if (myCursor.getInt ( 2 )== clConstant.NORD || myCursor.getInt ( 2 ) == clConstant.SUD) {
                if (myCursor.getInt ( 6 ) > 12)
                    canvas.drawText ( String.valueOf ( score ),570, yy , paint);
                else
                    canvas.drawText ( String.valueOf ( score ),620,yy,paint );
            } else {
                if (myCursor.getInt ( 6 ) > 12)
                    canvas.drawText ( String.valueOf ( score ),620,yy,paint );
                else
                    canvas.drawText ( String.valueOf ( score ),570,yy,paint );
            }
            paint.setTextAlign ( Paint.Align.LEFT );
            // entame
            if (myCursor.getInt ( 8  )!= -1 && myCursor.getInt ( 7) !=-1) {
                tmp = arrCarte[myCursor.getInt ( 8 )] + arrCouleur[myCursor.getInt ( 7 )];
                canvas.drawText ( tmp,480, yy, paint  );
            }

            myCursor.moveToNext ();
        }
        //================================================================================================================================
        pdfDocument.finishPage ( myPage );

        // partgae du support
        File file = new File(new File( view.getContext ().getExternalFilesDir(DIRECTORY_DOWNLOADS),"report_tournoi"), "tournoi.pdf");
        String filename = "tournoi.pdf";
        String filepath = "resultats";


        File myExternalFile = new File ( view.getContext ().getExternalFilesDir ( filepath ), filename );
        try {
            pdfDocument.writeTo ( new FileOutputStream ( myExternalFile ) );
        } catch (IOException e) {
            e.printStackTrace ();
        }
        pdfDocument.close ();
        act_shareFile mySharFile = new act_shareFile ();
        String titreShare = xxx.gettouLibelle () + "   " + xxx.dateFormatte ();
        mySharFile.shareFile (view, myExternalFile,titreShare);
    }


}
