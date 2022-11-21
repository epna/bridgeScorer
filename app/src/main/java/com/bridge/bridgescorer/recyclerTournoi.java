package com.bridge.bridgescorer;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class recyclerTournoi extends RecyclerView.ViewHolder {
    TextView trnDate, trnLibelle, trnNbDonnes, trnAdversaires, trnPartenaires;

    public recyclerTournoi(@NonNull View itemView) {
        super ( itemView );

        trnDate = itemView.findViewById ( R.id.trnDate  );
        trnLibelle=itemView.findViewById ( R.id.trnLibelle );
        trnNbDonnes = itemView.findViewById ( R.id.trnNbDonnes );
        trnAdversaires=itemView.findViewById ( R.id.trnAdversaires );
        trnPartenaires=itemView.findViewById ( R.id.trnPartenaire );
    }
}
