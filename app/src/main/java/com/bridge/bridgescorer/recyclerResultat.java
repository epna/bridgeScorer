package com.bridge.bridgescorer;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.List;


public class recyclerResultat extends RecyclerView.ViewHolder  {
    TextView resNumeroDonne,  resEntame,  resScoreEO,resScoreNS, resSynthese ;
    vuldonneur ivVulnerabilite;
    public recyclerResultat(@NonNull View itemView) {
        super ( itemView );


        resNumeroDonne = itemView.findViewById ( R.id.txtNumeroDonne );
        resSynthese= itemView.findViewById ( R.id.txtSynthese );
        resScoreEO=itemView.findViewById ( R.id.txtScoreEO );
        resScoreNS=itemView.findViewById ( R.id.txtScoreNS );
        ivVulnerabilite=itemView.findViewById ( R.id.ivVulnerabilite );
        resEntame=itemView.findViewById ( R.id.txtEntame );

        itemView.setOnLongClickListener ( new View.OnLongClickListener () {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder ( view.getContext () );
                String question = view.getResources ().getString ( R.string.deleteResulat,resNumeroDonne.getText () ) ;
                builder.setMessage ( question )
                        .setPositiveButton ( R.string.start, new DialogInterface.OnClickListener () {
                            public void onClick(DialogInterface dialog, int id) {
                                String libelle = view.getResources ().getString ( R.string.deletedResulat,resNumeroDonne.getText ().toString ()) ;
                                Snackbar.make ( view, libelle, Snackbar.LENGTH_LONG )
                                        .setTextColor ( Color.YELLOW )
                                        .setBackgroundTint ( Color.DKGRAY )
                                        .setAnchorView ( R.id.addResultat ).show ();

                                //
                                clDataBase db = new clDataBase ( view.getContext () );
                                Integer tmpTournoi = act_liste_resultat.resultats.get ( 0 ).getResIdTounois ();
                                Integer tmpDonne = Integer.parseInt ( resNumeroDonne.getText ().toString ());
                                db.deleteResultat (tmpTournoi,tmpDonne);
                                act_liste_resultat.resultats.remove ( xxposition ( tmpDonne ) );
                                act_liste_resultat.myAdapter.notifyDataSetChanged ();


                            }
                        } )
                        .setNegativeButton ( R.string.cancel, new DialogInterface.OnClickListener () {
                            public void onClick(DialogInterface dialog, int id) {
                                Snackbar.make ( view, view.getResources ().getString( R.string.deleteCanceled), Snackbar.LENGTH_LONG )
                                        .setAnchorView ( R.id.addResultat )
                                        .setTextColor ( Color.YELLOW ).
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
                Intent myIntent = new Intent ( view.getContext (), act_add_resultat.class );
                Integer idTournoi = act_liste_resultat.resultats.get ( 1 ).resIdTounois;
                myIntent.putExtra ( "peTournoi", idTournoi );
                myIntent.putExtra ( "peDonne", Integer.valueOf (resNumeroDonne.getText ().toString () ));
                myIntent.putExtra ( "peModif",true );
                myIntent.putExtra ( "peAdapter",getBindingAdapterPosition ());

                clResultat resultatAmodifier = act_liste_resultat.resultats.stream ()
                        .filter ( monResultat -> monResultat.getResNumeroDonne () == Integer.parseInt (resNumeroDonne.getText ().toString () ) )
                        .findFirst ()
                        .orElse ( null );
                myIntent.putExtra ("peResultat", resultatAmodifier );
                view.getContext ().startActivity ( myIntent );
            }
        } );
    }
    Integer xxposition(Integer xxDonne)
    {
        for(int i = 0; i < act_liste_resultat.resultats.size(); i++){
            if (act_liste_resultat.resultats.get(i).getResNumeroDonne ()==xxDonne) {
                return i;
            }
        }
        return -1;
    }



}
