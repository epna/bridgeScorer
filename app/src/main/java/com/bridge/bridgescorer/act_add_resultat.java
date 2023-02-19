package com.bridge.bridgescorer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;

import ernestoyaquello.com.verticalstepperform.Step;
import ernestoyaquello.com.verticalstepperform.VerticalStepperFormView;
import ernestoyaquello.com.verticalstepperform.listener.StepperFormListener;

public class act_add_resultat extends Activity implements StepperFormListener {
    public stp2NumDonne xxNumDonne;
    public stp2Declarant xxDeclarant;
    public stp2Contrat xxContrat;
    private stp2Resultat xxResultat;
    private stp2Entame xxEntame;
    private Integer idTournoi, idDonne, idAdapter;
public static VerticalStepperFormView vsf_resultat;
    private Boolean toUpdate;

    public static TextView txtEntete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.add_resultat );


        Bundle extras = getIntent ().getExtras ();
        toUpdate = extras.getBoolean ( "peModif" );
        idTournoi = extras.getInt ( "peTournoi", -1 );
        idDonne = extras.getInt ( "peDonne", -1 );
        idAdapter = extras.getInt ( "peAdapter", -1 );
        clResultat updateResultat = (clResultat) extras.getSerializable ( "peResultat" );

        xxNumDonne = new stp2NumDonne ( "Numéro donne", "Obligatoire", "suivant", toUpdate, updateResultat );
        xxDeclarant = new stp2Declarant ( "Déclarant", "Obligatoire", "suivant", toUpdate, updateResultat );
        xxContrat = new stp2Contrat ( "Contrat", "Obligatoire", "suivant", toUpdate, updateResultat );
        xxResultat = new stp2Resultat ( "Résultat", "Obligatoire", "suivant", toUpdate, updateResultat );
        xxEntame = new stp2Entame ( "Entame", "facultatif", "suivant", toUpdate, updateResultat );

        // Find the form view, set it up and initialize it.
        vsf_resultat = findViewById ( R.id.sf_resultat );
        txtEntete = findViewById ( R.id.txt_entete );
        vsf_resultat.setup ( this, xxNumDonne, xxDeclarant, xxContrat, xxResultat, xxEntame )
                .allowNonLinearNavigation ( false )
                .displayBottomNavigation ( false )
                .lastStepNextButtonText ( getString( R.string.okResultat) )
                .displayCancelButtonInLastStep ( true )
                .lastStepCancelButtonText ( getString( R.string.cancelResultat) )
                .init ();
        if (toUpdate) {
            vsf_resultat.removeStep ( 0 );
            txtEntete.setText ( getString ( R.string.enteteResultatUpdate,  idDonne ));
        }
        else
            txtEntete.setText ( getString ( R.string.enteteResultatCreate ));


    }

    @Override
    public void onCompletedForm() {
        // This method will be called when the user clicks on the last confirmation button of the
        // form in an attempt to save or send the data.
        String message = null;
        clDataBase db = new clDataBase ( getBaseContext () );
        if (toUpdate) {
            clResultat myResultat = new clResultat ( idTournoi,
                    idDonne,
                    xxDeclarant.getStepData (),
                    xxContrat.getStepData ().conNiveau,
                    xxContrat.getStepData ().getConCouleur (),
                    xxContrat.getStepData ().getConContre (),
                    xxResultat.getStepData (),
                    xxEntame.getStepData ().entCouleurEntame,
                    xxEntame.getStepData ().entNiveauEntame
            );
            if (db.updateResultat( idAdapter,myResultat ) != -1)
                message = getString ( R.string.majResultatOK,  idDonne);
            else
                message = getString ( R.string.majResultatKO, idDonne);
        } else {
            clResultat myResultat = new clResultat ( idTournoi,
                    xxNumDonne.getStepData (),
                    xxDeclarant.getStepData (),
                    xxContrat.getStepData ().conNiveau,
                    //xxContrat.getStepData ().getConNiveau (),
                    xxContrat.getStepData ().getConCouleur (),
                    xxContrat.getStepData ().getConContre (),
                    xxResultat.getStepData (),
                    xxEntame.getStepData ().entCouleurEntame,
                    xxEntame.getStepData ().entNiveauEntame
            );
            if (db.addResultat ( myResultat ) != 1)
                message = getString ( R.string.addResultatOK ,xxNumDonne.getStepData ()) ;
            else
                message = getString ( R.string.addResultatKO,xxNumDonne.getStepData () ) ;
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
        Snackbar sb = Snackbar.make ( findViewById ( R.id.dummyLayout ), getString ( R.string.cancelAddResultat,
                        idDonne ), Snackbar.LENGTH_SHORT )
                .setTextColor ( Color.RED )
                .setBackgroundTint ( Color.YELLOW )
                .setAction ( "Action", null );
        sb.show ();
        Intent myIntent = new Intent ( act_add_resultat.this, act_liste_resultat.class );

        act_add_resultat.this.startActivity ( myIntent );
    }

    @Override
    public void onStepAdded(int index, Step<?> addedStep) {
        // This will be called when a step is added dynamically through the form method addStep()
    }

    @Override
    public void onStepRemoved(int index) {
        // This will be called when a step is removed dynamically through the form method removeStep().
    }
}


