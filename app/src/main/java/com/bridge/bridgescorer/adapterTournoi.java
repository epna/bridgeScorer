package com.bridge.bridgescorer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapterTournoi extends RecyclerView.Adapter<recyclerTournoi> {
   @NonNull

   Context context;
   List<tournoi> tournois;

   public adapterTournoi(@NonNull Context context, List<tournoi> tournois) {
      this.context = context;
      this.tournois = tournois;
   }

   @Override
   public recyclerTournoi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      return new recyclerTournoi ( LayoutInflater.from ( context ).inflate ( R.layout.tournoi_view, parent,false ) );
   }

   @Override
   public void onBindViewHolder(@NonNull recyclerTournoi holder, int position) {
      holder.trnLibelle.setText ( tournois.get ( position ).getTrnLibelle () );
      holder.trnDate.setText ( tournois.get ( position ).getTrnDate () );
      holder.trnNbDonnes.setText ( tournois.get ( position ).getTrnNbDonnes () );
      holder.trnAdversaires.setText ( tournois.get ( position ).getTrnAdversaires () );
      holder.trnPartenaires.setText ( tournois.get ( position ).getTrnPartenaires () );

   }

   @Override
   public int getItemCount() {
      return tournois.size ();
   }
}
