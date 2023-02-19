package com.bridge.bridgescorer;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.shawnlin.numberpicker.NumberPicker;

import java.io.Serializable;

import ernestoyaquello.com.verticalstepperform.Step;

public class stp2Resultat extends Step {
    NumberPicker npResultat;
    Integer xxNiveau=0;

    Boolean xxModification;
    Integer xxResultat;
    public stp2Resultat(String title, String subtitle,String nextButtonText,Boolean toUpdate, clResultat monResultat) {
        super ( title, subtitle, nextButtonText );
        xxModification=toUpdate;
        if (xxModification) xxResultat= monResultat.resResultat;

    }

    @Override
    public Integer getStepData() {
        return npResultat.getValue ()+ (7-xxNiveau-1);

    }

    @Override
    public String getStepDataAsHumanReadableString() {
        String[] arrResulat =getContext ().getResources ().getStringArray ( R.array.resultat );
        return arrResulat[npResultat.getValue ()+ (7-xxNiveau-1)];

    }

    @Override
    protected void restoreStepData(Serializable data) {

    }

    @Override
    protected IsDataValid isStepDataValid(Serializable stepData) {
        return null;
    }

    @Override
    protected View createStepContentLayout() {
        npResultat = new NumberPicker ( getContext () );
        return npResultat;
    }

    @Override
    protected void onStepOpened(boolean animated) {
        String[] arrResulat = getContext ().getResources ().getStringArray ( R.array.resultat );
        String[] arrResulat2 = new String[14];


        if (MainActivity.xxNiveau == null) xxNiveau = 0;
        else xxNiveau = MainActivity.xxNiveau;
        for (int i = 0; i < 14; i++) {
            arrResulat2[i] = arrResulat[i + (7-xxNiveau)];
        }
        npResultat.setMinValue ( 1 );
        npResultat.setMaxValue ( arrResulat2.length );
        npResultat.setDisplayedValues ( arrResulat2 );
        npResultat.setOrientation ( LinearLayout.HORIZONTAL );

        //rr = npResultat.getValue ()+ (7-xxNiveau-1)

        if (xxModification) npResultat.setValue (xxResultat-((7-xxNiveau-1)) ); else npResultat.setValue ( xxNiveau + 7 );
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
