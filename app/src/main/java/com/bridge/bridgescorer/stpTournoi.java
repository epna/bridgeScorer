package com.bridge.bridgescorer;

import android.app.Activity;
import android.os.Bundle;

import ernestoyaquello.com.verticalstepperform.Step;
import ernestoyaquello.com.verticalstepperform.VerticalStepperFormView;
import ernestoyaquello.com.verticalstepperform.listener.StepperFormListener;

public class stpTournoi extends Activity implements StepperFormListener {




    public com.bridge.bridgescorer.stpAdversaires xxAdversaires;
    public com.bridge.bridgescorer.stpLibelle xxLibelle;
    public com.bridge.bridgescorer.stpPartenaire xxPartenaire;
    private stpDateSeance xxDateSeance;
    private stpDonnesPosition xxNbDonnesPosition ;

    private VerticalStepperFormView verticalStepperForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_tournoi);

        // Create the steps.
        xxAdversaires = new stpAdversaires ("Adversaires","Facultatif","Suivant");
        xxLibelle = new stpLibelle ("Libéllé","", "suivant");
        xxPartenaire = new stpPartenaire ("Partenaire","","Suivant" );
        xxDateSeance = new stpDateSeance ("Date tournoi","","Suivant" );
        xxNbDonnesPosition = new stpDonnesPosition ("Nb donnes","","Suivant" );


        // Find the form view, set it up and initialize it.
        verticalStepperForm = findViewById(R.id.stepper_form);
        verticalStepperForm
                .setup(this, xxLibelle, xxDateSeance, xxNbDonnesPosition,xxAdversaires,xxPartenaire)
                .init();
    }

    @Override
    public void onCompletedForm() {
        // This method will be called when the user clicks on the last confirmation button of the
        // form in an attempt to save or send the data.

        dataBase db = new dataBase ( getBaseContext () );

        db.addTournoi (
                xxDateSeance.getStepData ().repDate,
                xxLibelle.getStepData ().toString (),
                xxNbDonnesPosition.getStepData ().repNbDonnes,
                xxAdversaires.getStepData ().toString (),
                xxPartenaire.getStepData ().toString (),
                xxNbDonnesPosition.getStepData ().repPosition,
                xxDateSeance.getStepData ().repSeance

                );
        finish ();
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


