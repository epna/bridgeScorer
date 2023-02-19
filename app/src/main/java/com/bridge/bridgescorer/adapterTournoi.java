package com.bridge.bridgescorer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;




public class adapterTournoi extends RecyclerView.Adapter<recyclerTournoi> {
      Context context;
      List<clTournoi> tournois;


   public adapterTournoi(Context context, List<clTournoi> tournois) {
      this.context = context;
      this.tournois = tournois;
   }

   @Override
   public recyclerTournoi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from ( context ).inflate ( R.layout.tournoi_view, parent,false );
      recyclerTournoi xxx = new recyclerTournoi (view  );
      return xxx;
   }

   @Override
   public void onBindViewHolder(@NonNull recyclerTournoi holder, int position) {

      holder.trnLibelle.setText ( tournois.get ( position ).gettouLibelle () );

      //SimpleDateFormat monFormat = new SimpleDateFormat ("MMM dd,yyyy hh:mm a");
      //String newFormat = monFormat.format(tournois.get ( position ).gettouDate ());
      SimpleDateFormat format = new SimpleDateFormat ( "E dd MMM yyyy" );
      String strDate = format.format ( tournois.get ( position ).gettouDate () );
      holder.trnDatevv.setText (strDate);
      holder.trnAdversaires.setText ( "Adv : " + tournois.get ( position ).gettouAdversaires () );
      holder.trnPartenaires.setText ( "Part. : " +tournois.get ( position ).gettouPartenaires () );
      holder.trnNbDonnes.setText( tournois.get ( position ).gettouNbDonnes () + " donnes " );
      holder.trnSeance.setText ( "Séance : " + tournois.get ( position ).gettouSeance () );
      holder.trnPosition.setText (  (tournois.get(position).touPosition ) ? "Nord-Sud" : "Est-Ouest" );
      holder.idTournoi = tournois.get(position).touId;

   }

   @Override
   public int getItemCount() {
      return tournois.size ();
   }

}
