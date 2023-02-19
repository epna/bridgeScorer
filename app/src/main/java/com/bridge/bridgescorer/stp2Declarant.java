package com.bridge.bridgescorer;

import android.view.LayoutInflater;
import android.view.View;

import ernestoyaquello.com.verticalstepperform.Step;
import nl.bryanderidder.themedtogglebuttongroup.ThemedToggleButtonGroup;

public class stp2Declarant extends Step<Integer> {
    public ThemedToggleButtonGroup groupPositions;
    Boolean xxModification;
    Integer xxDeclarant;
    Integer selectedButton=0;
    public stp2Declarant(String title, String subtitle,String nextButtonText, Boolean toUpdate, clResultat monResultat ) {
        super ( title, subtitle,nextButtonText );
        xxModification=toUpdate;
        if (toUpdate) xxDeclarant=monResultat.resDeclarant;
    }

    @Override
    public Integer getStepData() {
        //markAsCompletedOrUncompleted(true);
        if (R.id.sud==selectedButton) return 1 ;
        if (R.id.nord==selectedButton) return 0 ;
        if (R.id.est==selectedButton) return 2 ;
        if (R.id.ouest==selectedButton) return 3;
        return 0;
    }

    @Override
    public String getStepDataAsHumanReadableString() {
        String tmp="";
        if (R.id.sud==selectedButton) tmp="Sud";
        if (R.id.nord==selectedButton) tmp="Nord";
        if (R.id.est==selectedButton) tmp="Est";
        if (R.id.ouest==selectedButton) tmp="Ouest";

        return tmp;
    }

    @Override
    protected void restoreStepData(Integer data) {

    }

    @Override
    protected IsDataValid isStepDataValid(Integer stepData) {
        Boolean  isNameValid = (groupPositions.getSelectedButtons ().size ()>0);
        String errorMessage = !isNameValid ? "Déclarant obligatoire " : "";
        return new IsDataValid(isNameValid, errorMessage);

    }

    @Override
    protected View createStepContentLayout() {
        // We create this step view by inflating an XML layout
        LayoutInflater inflater = LayoutInflater.from ( getContext () );
        View view = inflater.inflate ( R.layout.stp2_declarant, null, false );
        groupPositions = view.findViewById (R.id.declarant);
        if (xxModification)
        {
            if (xxDeclarant==0) groupPositions.selectButton (R.id.nord  );
            if (xxDeclarant==1) groupPositions.selectButton (R.id.sud  );
            if (xxDeclarant==2) groupPositions.selectButton (R.id.est  );
            if (xxDeclarant==3) groupPositions.selectButton (R.id.ouest  );
        }

        groupPositions.setOnSelectListener ( themedButton -> {
            selectedButton= themedButton.getId ();
            markAsCompletedOrUncompleted(true);
            return null;

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
