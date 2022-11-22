package com.bridge.bridgescorer;

import android.app.Activity;
import android.os.Bundle;

import ernestoyaquello.com.verticalstepperform.Step;
import ernestoyaquello.com.verticalstepperform.VerticalStepperFormView;
import ernestoyaquello.com.verticalstepperform.listener.StepperFormListener;

public class stepperTournoi extends Activity implements StepperFormListener {




    public stepperAdversaires stpAdversaires;
    public stepperLibelle stpLibelle;
    public stepperPartenaire stpPartenaire;
    private stepperDate stpDate;
    private stepperNbDonnes stpNbDonnes;

    private VerticalStepperFormView verticalStepperForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_tournoi);

        // Create the steps.
        stpAdversaires = new stepperAdversaires ("Adversaires","Facultatif","Suivant");
        stpLibelle = new stepperLibelle ("Libéllé","", "suivant");
        stpPartenaire = new stepperPartenaire ("Partenaire","","Suivant" );
        stpDate = new stepperDate ("Date tournoi","","Suivant" );
        stpNbDonnes = new stepperNbDonnes ("Nb donnes","","Suivant" );

        // Find the form view, set it up and initialize it.
        verticalStepperForm = findViewById(R.id.stepper_form);
        verticalStepperForm
                .setup(this, stpLibelle, stpDate, stpNbDonnes,stpAdversaires,stpPartenaire)
                .init();
    }

    @Override
    public void onCompletedForm() {
        // This method will be called when the user clicks on the last confirmation button of the
        // form in an attempt to save or send the data.
    }

    @Override
    public void onCancelledForm() {
        // This method will be called when the user clicks on the cancel button of the form.
    }


    @Override
    public void onStepAdded(int index, Step<?> addedStep) {
        // This will be called when a step is added dynamically through the form method addStep().
    }

    @Override
    public void onStepRemoved(int index) {
        // This will be called when a step is removed dynamically through the form method removeStep().
    }
}


