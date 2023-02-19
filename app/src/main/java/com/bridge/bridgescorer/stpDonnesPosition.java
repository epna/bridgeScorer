package com.bridge.bridgescorer;

import android.view.LayoutInflater;
import android.view.View;

import com.github.angads25.toggle.interfaces.OnToggledListener;
import com.github.angads25.toggle.model.ToggleableView;
import com.huynn109.IncreaseDecreaseButton;

import ernestoyaquello.com.verticalstepperform.Step;

public class stpDonnesPosition extends Step<repDonnesPosition> {


    IncreaseDecreaseButton trnNbDonnes;
    ToggleableView trnPosition;
    int xxNbDonnes;
    Boolean xxNordSud;
    Boolean modification;

    public stpDonnesPosition(String title, String subtitle, String nextButtonText, boolean modif, repDonnesPosition defaut) {
        super ( title, subtitle, nextButtonText );
        modification = modif;
        if (modif) {
            xxNbDonnes = defaut.repNbDonnes;
            xxNordSud = defaut.repPosition;
        }
    }

    @Override
    public repDonnesPosition getStepData() {
        repDonnesPosition temp = new repDonnesPosition ();
        temp.repNbDonnes = trnNbDonnes.getCurrentNumber ();
        temp.repPosition = trnPosition.isOn ();
        return temp;
    }


    @Override
    public String getStepDataAsHumanReadableString() {
        repDonnesPosition temp = getStepData ();
        String reponse;
        if (temp.repPosition) reponse = "Nord-Sud";
        else reponse = "Est-Ouest";
        reponse = temp.repNbDonnes + " donnes " + reponse;

        return reponse;
    }

    @Override
    protected void restoreStepData(repDonnesPosition data) {

    }

    @Override
    protected IsDataValid isStepDataValid(repDonnesPosition stepData) {
        return null;
    }


    @Override
    protected View createStepContentLayout() {


        LayoutInflater inflater = LayoutInflater.from ( getContext () );
        View view = inflater.inflate ( R.layout.stepper_donne, null, false );
        trnNbDonnes = view.findViewById ( R.id.npDonnes );
        trnPosition = view.findViewById ( R.id.npPosition );
        trnPosition.setOnToggledListener ( new OnToggledListener () {
            @Override
            public void onSwitched(ToggleableView toggleableView, boolean isOn) {


            }

        } );
        // Here we generate the view that will be used by the library as the content of the step.
        // In this case we do it programmatically, but we could also do it by inflating an XML layout.
        //trnNbDonnes = new NumberPicker (getContext());
        trnNbDonnes.setMinNumber ( 8 );
        trnNbDonnes.setMaxNumber ( 32 );
        if (modification) {
            trnNbDonnes.initNumber ( xxNbDonnes );
            trnPosition.setOn ( xxNordSud );
            trnNbDonnes.setEnabled ( true );
        } else {
            trnNbDonnes.initNumber ( 24 );
            trnPosition.setOn ( true );
            trnNbDonnes.setEnabled ( true );
        }

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
