package com.bridge.bridgescorer;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.io.Serializable;

import ernestoyaquello.com.verticalstepperform.Step;

public class stpPartenaire extends Step {
    private EditText trnPartenaire;
    private final Boolean xxModif;
    private String xxPartenaire;

    public stpPartenaire(String title, String subtitle, String nextButtonText,Boolean modification, String defaut) {
        super ( title, subtitle, nextButtonText );
        xxModif=modification;
        if (modification) xxPartenaire=defaut;

    }

    @Override
    public Serializable getStepData() {

        return trnPartenaire.getText ().toString ();
    }

    @Override
    public String getStepDataAsHumanReadableString() {


        return getStepData ().toString ();
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




        // Here we generate the view that will be used by the library as the content of the step.
        // In this case we do it programmatically, but we could also do it by inflating an XML layout.
        trnPartenaire = new EditText(getContext());
        trnPartenaire.setSingleLine(true);
        trnPartenaire.setHint("Partenaire");
        if(xxModif) trnPartenaire.setText (xxPartenaire ); else trnPartenaire.setText ( "" );
        trnPartenaire.addTextChangedListener(new TextWatcher () {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Whenever the user updates the user name text, we update the state of the step.
                // The step will be marked as completed only if its data is valid, which will be
                // checked automatically by the form with a call to isStepDataValid().
                markAsCompletedOrUncompleted(true);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return trnPartenaire;

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
