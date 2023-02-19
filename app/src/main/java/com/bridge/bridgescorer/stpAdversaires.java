package com.bridge.bridgescorer;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.io.Serializable;

import ernestoyaquello.com.verticalstepperform.Step;

public class stpAdversaires extends Step {

    private EditText trnAdversaires;
    public Boolean xxModif;
    public String  xxDefaut;

    public stpAdversaires(String title, String subtitle, String nextButtonText, Boolean modif, String defaut ) {
        super ( title, subtitle, nextButtonText );
        xxModif=modif;
        if (modif) xxDefaut= defaut;

    }



    @Override
    public Serializable getStepData() {

        return trnAdversaires.getText ().toString ();
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
        trnAdversaires = new EditText(getContext());
        trnAdversaires.setSingleLine(true);
        trnAdversaires.setHint("adversaire");
        if (xxModif) trnAdversaires.setText ( xxDefaut );
        trnAdversaires.addTextChangedListener(new TextWatcher () {

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

        return trnAdversaires;

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
