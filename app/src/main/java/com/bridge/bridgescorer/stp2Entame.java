package com.bridge.bridgescorer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.shawnlin.numberpicker.NumberPicker;


import ernestoyaquello.com.verticalstepperform.Step;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import nl.bryanderidder.themedtogglebuttongroup.ThemedButton;
import nl.bryanderidder.themedtogglebuttongroup.ThemedToggleButtonGroup;

public class stp2Entame extends Step<repEntame> {
    Boolean xxModif;
    Integer xxCouleurEntame,xxNiveauEntame;
    ThemedToggleButtonGroup btnCouleurEntame, btnNiveauEntame;
    ThemedButton btnTrefle, btnCarreau, btnCoeur, btnPique;

    public stp2Entame(String title, String subtitle, String nextButtonText, Boolean modification, clResultat monResultat) {
        super ( title, subtitle, nextButtonText );
        xxModif = modification;
        if (modification) {
            xxCouleurEntame = monResultat.resCouleurEntame;
            xxNiveauEntame = monResultat.resNiveauEntame;

        } else {
            xxCouleurEntame = -1;
            xxNiveauEntame = -1;
        }
    }
    @Override
    public repEntame getStepData() {

        repEntame monEntame = new repEntame (  );
        if (xxCouleurEntame>-1 && xxNiveauEntame >-1) {
            monEntame.entCouleurEntame = xxCouleurEntame;
            monEntame.entNiveauEntame = xxNiveauEntame;
        }
        else {
            monEntame.entCouleurEntame = -1;
            monEntame.entNiveauEntame = -1;
        }
        return  monEntame;
    }

    @Override
    public String getStepDataAsHumanReadableString() {
        String[] arrCarte = getContext ().getResources ().getStringArray ( R.array.Carte );
        String[] arrCouleur = getContext ().getResources ().getStringArray ( R.array.couleur );
        String tmp = "";
        if (xxCouleurEntame>-1 && xxNiveauEntame >-1) {

         tmp = arrCarte[xxNiveauEntame]
                 + arrCouleur[xxCouleurEntame];
     }
     return tmp;
    }
    @Override
    protected void restoreStepData(repEntame data) {

    }

    @Override
    protected IsDataValid isStepDataValid(repEntame stepData) {
        return null;
    }


    @Override
    protected View createStepContentLayout() {

        LayoutInflater inflater = LayoutInflater.from ( getContext () );
        View view = inflater.inflate ( R.layout.stp2_entame, null, false );
        String[] arrCarte = getContext ().getResources ().getStringArray ( R.array.Carte );
        String[] arrCouleur = getContext ().getResources ().getStringArray ( R.array.couleur );

        btnTrefle = view.findViewById ( R.id.btnTrefle);
        btnCarreau = view.findViewById ( R.id.btnCarreau);
        btnCoeur = view.findViewById ( R.id.btnCoeur);
        btnPique = view.findViewById ( R.id.btnPique);
        btnCouleurEntame = view.findViewById(R.id.btnCouleurEntame);
        btnNiveauEntame = view.findViewById (R.id.btnNiveauEntame);
        btnTrefle.setText (arrCouleur[clConstant.TREFLE]);
        btnCarreau.setText (arrCouleur[clConstant.CARREAU]);
        btnCoeur.setText (arrCouleur[clConstant.COEUR]);
        btnPique.setText (arrCouleur[clConstant.PIQUE]);
        if (xxModif && xxCouleurEntame != -1 && xxNiveauEntame !=-1)
        {
            btnCouleurEntame.selectButton (setValueCouleur ( xxCouleurEntame )  );
            btnNiveauEntame.selectButton ( setValueNiveau(xxNiveauEntame) );

        }



        btnNiveauEntame.setOnSelectListener ( new Function1<ThemedButton, Unit> () {
            @Override
            public Unit invoke(ThemedButton themedButton) {
                xxNiveauEntame= niveauEntame (  );
                return null;

            }
        } );

        btnCouleurEntame.setOnSelectListener ( new Function1<ThemedButton, Unit> () {
            @Override
            public Unit invoke(ThemedButton themedButton) {
                xxCouleurEntame= couleurEntame ( );
                return null;

            }
        } );

        //if (xxModif) NPNiveauEntame.setValue (xxNiveauEntame);else NPNiveauEntame.setValue (2);
        return view;
    }

    private int setValueNiveau(Integer xxNiveauEntame) {
        if (xxNiveauEntame==0) return R.id.btn2;
        if (xxNiveauEntame==1) return R.id.btn3;
        if (xxNiveauEntame==2) return R.id.btn4;
        if (xxNiveauEntame==3) return R.id.btn5;
        if (xxNiveauEntame==4) return R.id.btn6;
        if (xxNiveauEntame==5) return R.id.btn7;
        if (xxNiveauEntame==6) return R.id.btn8;
        if (xxNiveauEntame==7) return R.id.btn9;
        if (xxNiveauEntame==8) return R.id.btn10;
        if (xxNiveauEntame==9) return R.id.btnV;
        if (xxNiveauEntame==10) return R.id.btnD;
        if (xxNiveauEntame==11) return R.id.btnR;
        if (xxNiveauEntame==12) return R.id.btnA;
        return -1;
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


    Integer couleurEntame ()
    {
        if (btnCouleurEntame.getSelectedButtons ().get ( 0 ).getId () == R.id.btnTrefle)
            return 0;
        if (btnCouleurEntame.getSelectedButtons ().get ( 0 ).getId () == R.id.btnCarreau)
            return 1;
        if (btnCouleurEntame.getSelectedButtons ().get ( 0 ).getId () == R.id.btnCoeur)
            return 2;
        if (btnCouleurEntame.getSelectedButtons ().get ( 0 ).getId () == R.id.btnPique)
            return 3;
        return-1;
    }
    Integer niveauEntame ()
    {
        if (btnNiveauEntame.getSelectedButtons ().get ( 0 ).getId () == R.id.btn2) return 0;
        if (btnNiveauEntame.getSelectedButtons ().get ( 0 ).getId () == R.id.btn3) return 1;
        if (btnNiveauEntame.getSelectedButtons ().get ( 0 ).getId () == R.id.btn4) return 2;
        if (btnNiveauEntame.getSelectedButtons ().get ( 0 ).getId () == R.id.btn5) return 3;
        if (btnNiveauEntame.getSelectedButtons ().get ( 0 ).getId () == R.id.btn6) return 4;
        if (btnNiveauEntame.getSelectedButtons ().get ( 0 ).getId () == R.id.btn7) return 5;
        if (btnNiveauEntame.getSelectedButtons ().get ( 0 ).getId () == R.id.btn8) return 6;
        if (btnNiveauEntame.getSelectedButtons ().get ( 0 ).getId () == R.id.btn9) return 7;
        if (btnNiveauEntame.getSelectedButtons ().get ( 0 ).getId () == R.id.btn10) return 8;
        if (btnNiveauEntame.getSelectedButtons ().get ( 0 ).getId () == R.id.btnV) return 9;
        if (btnNiveauEntame.getSelectedButtons ().get ( 0 ).getId () == R.id.btnD) return 10;
        if (btnNiveauEntame.getSelectedButtons ().get ( 0 ).getId () == R.id.btnR) return 11;
        if (btnNiveauEntame.getSelectedButtons ().get ( 0 ).getId () == R.id.btnA) return 12;
        return-1;
    }

    Integer setValueCouleur (Integer maCouleur )
    {
        if (maCouleur == 0) return R.id.btnTrefle;
        if (maCouleur == 1) return R.id.btnCarreau;
        if (maCouleur == 2) return R.id.btnCoeur;
        if (maCouleur == 3) return R.id.btnPique;
        return -1;
    }
}
