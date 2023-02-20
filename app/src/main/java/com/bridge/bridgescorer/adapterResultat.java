package com.bridge.bridgescorer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class adapterResultat extends RecyclerView.Adapter<recyclerResultat> {
    Context context;
    List<clResultat> resultats;


    public adapterResultat(Context context, List<clResultat> resultats) {
        this.context = context;
        this.resultats = resultats;
    }

    @Override
    public recyclerResultat onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new recyclerResultat ( LayoutInflater.from ( context ).inflate ( R.layout.resultat_view, parent, false ) );
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerResultat holder, int position) {
        String[] arrResultats = context.getResources ().getStringArray ( R.array.resultat );
        String[] arrDeclarant = context.getResources ().getStringArray ( R.array.declarant );
        String[] arrCouleur = context.getResources ().getStringArray ( R.array.couleur );
        String[] arrContre = context.getResources ().getStringArray ( R.array.contre );
        String[] arrCarte = context.getResources ().getStringArray ( R.array.Carte );


        clContrat xxContrat = new clContrat (
                resultats.get ( position ).getResNumeroDonne (),
                resultats.get ( position ).getResDeclarant (),
                resultats.get ( position ).getResNiveau (),
                resultats.get ( position ).getResCouleur (),
                resultats.get ( position ).getResContre (),
                resultats.get ( position ).getResResultat (), context

        );

        holder.ivVulnerabilite.attrNumDOnne = resultats.get ( position ).getResNumeroDonne ();
        holder.resNumeroDonne.setText ( String.valueOf ( resultats.get ( position ).resNumeroDonne ) );
        if (!act_liste_resultat.cacher) {
            holder.resScoreNS.setText ( "" );
            holder.resScoreEO.setText ( "" );
            holder.resSynthese.setText ( "" );
            String tmp = resultats.get ( position ).resNiveau + arrCouleur[resultats.get ( position ).resCouleur] + arrContre[resultats.get ( position ).resContre];
            tmp += arrResultats[resultats.get ( position ).resResultat] + " par " + arrDeclarant[resultats.get ( position ).resDeclarant];
            holder.resSynthese.setText ( tmp );
            Integer score = xxContrat.computeScore ();
            if (resultats.get ( position ).resDeclarant == clConstant.NORD || resultats.get ( position ).resDeclarant == clConstant.SUD) {
                if (resultats.get ( position ).resResultat > 12)
                    holder.resScoreNS.setText ( String.valueOf ( score ) );
                else
                    holder.resScoreEO.setText ( String.valueOf ( score ) );
            } else {
                if (resultats.get ( position ).resResultat > 12)
                    holder.resScoreEO.setText ( String.valueOf ( score ) );
                else
                    holder.resScoreNS.setText ( String.valueOf ( score ) );
            }
            tmp = null;
            if (resultats.get ( position ).resCouleurEntame > 4) {
                holder.resEntame.setText ( "" );
            } else {
                if (resultats.get ( position ).resNiveauEntame > -1 && resultats.get ( position ).resCouleurEntame > -1) {
                    tmp = arrCarte[resultats.get ( position ).resNiveauEntame] + arrCouleur[resultats.get ( position ).resCouleurEntame];
                    holder.resEntame.setText ( tmp );
                } else
                    holder.resEntame.setText ( "" );
            }
        } else {
            holder.resScoreNS.setText ( "" );
            holder.resScoreEO.setText ( "" );
            holder.resSynthese.setText ( "" );
            holder.resEntame.setText ( "" );
        }
    }

    @Override
    public int getItemCount() {
        return resultats.size ();
    }
}
