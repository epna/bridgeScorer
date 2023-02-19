package com.bridge.bridgescorer;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Date;

import ernestoyaquello.com.verticalstepperform.Step;
import ernestoyaquello.com.verticalstepperform.VerticalStepperFormView;
import ernestoyaquello.com.verticalstepperform.listener.StepperFormListener;

public class act_add_tournoi extends Activity implements StepperFormListener {
    public com.bridge.bridgescorer.stpAdversaires xxAdversaires;
    public stpLibelle xxLibelle;
    public stpPartenaire xxPartenaire;
    private stpDateSeance xxDateSeance;
    private stpDonnesPosition xxNbDonnesPosition;
    private Boolean modif;
    private Integer idTournoi;
    private Integer positionAdapter;
    public  static TextView txt_entete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.add_tournoi );
        // Create the steps.

        Bundle extras = getIntent ().getExtras ();
        modif = extras.getBoolean ( "modif" );
        idTournoi = extras.getInt ( "idTournoi", -1 );
        positionAdapter = extras.getInt ( "positionAdapter", -1 );
        clTournoi tournoiModif = (clTournoi) extras.getSerializable ( "clTournoi" );
        String Defaut;

        if (modif) Defaut = tournoiModif.gettouAdversaires ();
        else Defaut = "";
        xxAdversaires = new stpAdversaires ( "Adversaires", "Facultatif", "Suivant", modif, Defaut );

        if (modif) Defaut = tournoiModif.gettouLibelle ();
        else Defaut = "";
        xxLibelle = new stpLibelle ( "Libéllé", "", "suivant", modif, Defaut );

        if (modif) Defaut = tournoiModif.gettouPartenaires ();
        else Defaut = "";
        xxPartenaire = new stpPartenaire ( "Partenaire", "Facultatif", "Suivant", modif, Defaut );

        Date dateDefaut = null;
        if (modif) dateDefaut = tournoiModif.gettouDate ();
        else dateDefaut = null;
        xxDateSeance = new stpDateSeance ( "Date tournoi", "", "Suivant", modif, dateDefaut );

        repDonnesPosition myDonnesPosition = new repDonnesPosition ();
        if (modif) {
            myDonnesPosition.repPosition = tournoiModif.getTouPosition ();
            myDonnesPosition.repNbDonnes = tournoiModif.gettouNbDonnes ();
        } else myDonnesPosition = null;
        xxNbDonnesPosition = new stpDonnesPosition ( "Nb donnes", "Facultatif", "Suivant", modif, myDonnesPosition );
        // Find the form view, set it up and initialize it.
        VerticalStepperFormView verticalStepperForm = findViewById ( R.id.sf_tournoi );
        verticalStepperForm
                .setup ( this, xxLibelle, xxDateSeance, xxNbDonnesPosition, xxAdversaires, xxPartenaire )
                .allowNonLinearNavigation ( false )
                .displayBottomNavigation ( false )
                .lastStepNextButtonText ( "Valider" )
                .displayCancelButtonInLastStep ( true )
                .lastStepCancelButtonText ( "Annuler" )
                .init ();

        txt_entete=findViewById ( R.id.txt_entete );
        if (modif) txt_entete.setText (getString( R.string.modifTournoi) +tournoiModif.gettouLibelle ()   );
        else
            txt_entete.setText ( getString( R.string.createTournoi)  );
    }

    @Override
    public void onCompletedForm() {
        clDataBase db = new clDataBase ( getBaseContext () );
        String message = null;
        if (modif) {
            clTournoi myTournoi = new clTournoi (
                    xxLibelle.getStepData ().toString (),
                    xxDateSeance.getStepData ().repDate,
                    xxNbDonnesPosition.getStepData ().repNbDonnes,
                    xxAdversaires.getStepData ().toString (),
                    xxPartenaire.getStepData ().toString (),
                    xxDateSeance.getStepData ().repSeance,
                    xxNbDonnesPosition.getStepData ().repPosition, idTournoi );

            if (db.updateTournoi ( positionAdapter, myTournoi ) != -1)
                message = getString ( R.string.updateTournoiOK,xxLibelle.getStepData ().toString () );
            else message = getString ( R.string.updateTournoiKO , xxLibelle);
        } else {
            clTournoi myTournoi = new clTournoi (
                    xxLibelle.getStepData ().toString (),
                    xxDateSeance.getStepData ().repDate,
                    xxNbDonnesPosition.getStepData ().repNbDonnes,
                    xxAdversaires.getStepData ().toString (),
                    xxPartenaire.getStepData ().toString (),
                    xxDateSeance.getStepData ().repSeance,
                    xxNbDonnesPosition.getStepData ().repPosition,
                    idTournoi );
            if (db.addTournoi ( myTournoi ) != -1)
                message = getString ( R.string.addTournoiOK );
            else message = getString ( R.string.addTournoiKO );
        }
        Snackbar sb = Snackbar.make ( findViewById ( R.id.dummyLayout ), message, Snackbar.LENGTH_SHORT )
                .setTextColor ( Color.GREEN )
                .setBackgroundTint ( Color.BLUE )
                .setAction ( "Action", null );
        sb.addCallback ( new Snackbar.Callback () {

            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                super.onDismissed ( transientBottomBar, event );
                finish ();
            }
        } );
        sb.show ();
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


