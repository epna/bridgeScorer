package com.bridge.bridgescorer;

import android.view.LayoutInflater;
import android.view.View;

import ernestoyaquello.com.verticalstepperform.Step;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import nl.bryanderidder.themedtogglebuttongroup.ThemedButton;
import nl.bryanderidder.themedtogglebuttongroup.ThemedToggleButtonGroup;

public class stp2NumDonne extends Step<Integer> {
    //public NumberPicker myNumberPicker;
    String [] arrNumDonneDispo = new String[37];
    Integer xxNumDonne=0;
    ThemedToggleButtonGroup myGroup;
    Boolean xxModification;

    public stp2NumDonne(String title, String subtitle,String nextButtonText, Boolean toUpdate, clResultat monResultat) {
        super ( title, subtitle , nextButtonText);
        if (toUpdate)
        xxNumDonne=monResultat.getResNumeroDonne ();
        xxModification= toUpdate;
    }

    @Override
    public Integer getStepData() {
        return  xxNumDonne;
    }

    @Override
    public String getStepDataAsHumanReadableString() {
        return String.valueOf (xxNumDonne);
    }

    @Override
    protected void restoreStepData(Integer data) {

    }

    @Override
    protected IsDataValid isStepDataValid(Integer stepData) {
        return new IsDataValid(xxNumDonne>0, getContext ().getString( R.string.requiredDonne));
        //return myGroup.isSelected ();
        //return null;
    }

    @Override
    protected View createStepContentLayout() {
        LayoutInflater inflater = LayoutInflater.from ( getContext () );
        View view = inflater.inflate ( R.layout.stp2_numero_donne, null, false );
        myGroup = view.findViewById ( R.id.donnes );


        for (int i=36;i>act_liste_resultat.nbDonnes;i--)
        {
            myGroup.removeViewAt ( i-1 );
        }
        for (int i=act_liste_resultat.nbDonnes;i>0;i--)
        {
            final int j = i ;
            clResultat myResultat  = act_liste_resultat.resultats.stream ()
                    .filter(resultat ->String.valueOf ( j ).equals (  String.valueOf ( resultat.getResNumeroDonne ())))
                    .findFirst ()
                    .orElse ( null );
            if (myResultat != null) myGroup.removeViewAt ( i-1 );


        }


        myGroup.setOnSelectListener ( new Function1<ThemedButton, Unit> () {
            @Override
            public Unit invoke(ThemedButton themedButton) {

                xxNumDonne=Integer.parseInt ( themedButton.getText () );
                act_add_resultat.txtEntete.setText (getContext ().getString (  R.string.createDonne2, xxNumDonne ));
                markAsCompletedOrUncompleted(true);
                return null;
            }
        } );

        return view;
    }

    @Override
    protected void onStepOpened(boolean animated) {

    }

    @Override
    protected void onStepClosed(boolean animated) {

    }

    @Override
    protected void onStepMarkedAsCompleted(boolean animated) {

    }

    @Override
    protected void onStepMarkedAsUncompleted(boolean animated) {

    }
}
