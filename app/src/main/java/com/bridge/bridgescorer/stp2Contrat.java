package com.bridge.bridgescorer;

import android.view.LayoutInflater;
import android.view.View;

import com.shawnlin.numberpicker.NumberPicker;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ernestoyaquello.com.verticalstepperform.Step;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import nl.bryanderidder.themedtogglebuttongroup.ThemedButton;
import nl.bryanderidder.themedtogglebuttongroup.ThemedToggleButtonGroup;

public class stp2Contrat extends Step<repContrat> {

    ThemedToggleButtonGroup xxBtnNiveau, xxBtnCouleur, xxBtnContre;
    Boolean xxModification;
    Integer xxNiveau, xxCouleur, xxContre;
    List<ThemedButton> buttonsNiveau;
    List<ThemedButton> buttonsCouleur;
    List<ThemedButton> buttonsContre;

    public stp2Contrat(String title, String subtitle, String nextButtonText, Boolean toUpdate, clResultat monResultat) {
        super ( title, subtitle, nextButtonText );
        xxModification = toUpdate;
        if (xxModification) {
            xxNiveau = monResultat.getResNiveau ();
            xxCouleur = monResultat.getResCouleur ();
            xxContre = monResultat.getResContre ();
        } else {
            xxNiveau = 4;
            xxCouleur = 0;
            xxContre = 0;
        }
    }

    @Override
    public repContrat getStepData() {

        repContrat myRepContrat = new repContrat ();
        myRepContrat.conContre= xxContre;
        myRepContrat.conCouleur= xxCouleur;
        myRepContrat.conNiveau= xxNiveau;
        MainActivity.xxNiveau=xxNiveau;
        return myRepContrat;

    }

    @Override
    public String getStepDataAsHumanReadableString() {
        String[] arrContre = getContext ().getResources ().getStringArray ( R.array.contre );
        String[] arrCouleur = getContext ().getResources ().getStringArray ( R.array.couleur );


        String tmp = xxNiveau
                + arrCouleur[ xxCouleur]
                + arrContre[xxContre];
        return tmp;

    }

    @Override
    protected void restoreStepData(repContrat data) {

       /* data.conCouleur= xxBtnCouleur.getValue ();
        data.conContre= xxBtnContre.getValue ();
        data.conNiveau= xxBtnNiveau.getValue ();*/


    }

    @Override
    protected IsDataValid isStepDataValid(repContrat stepData) {
        return null;
    }

    @Override
    protected View createStepContentLayout() {
        LayoutInflater inflater = LayoutInflater.from ( getContext () );
        View view = inflater.inflate ( R.layout.stp2_contrat, null, false );
        String[] arrCouleur = getContext ().getResources ().getStringArray ( R.array.couleur );

        xxBtnNiveau = view.findViewById ( R.id.btnGroupNiveau );
        xxBtnCouleur = view.findViewById ( R.id.btnGroupCouleur );
        xxBtnContre = view.findViewById ( R.id.btnGroupContre );

        buttonsNiveau = xxBtnNiveau.getButtons ();
        buttonsCouleur = xxBtnCouleur.getButtons ();
        buttonsContre = xxBtnContre.getButtons ();
        ThemedButton myButton = buttonsCouleur.get(0);
        myButton.setText (arrCouleur[clConstant.TREFLE] );
        myButton = buttonsCouleur.get(1);
        myButton.setText (arrCouleur[clConstant.CARREAU] );
        myButton = buttonsCouleur.get(2);
        myButton.setText (arrCouleur[clConstant.COEUR] );
        myButton = buttonsCouleur.get(3);
        myButton.setText (arrCouleur[clConstant.PIQUE] );


        xxBtnNiveau.selectButton ( setNiveau ( xxNiveau ) );
        xxBtnNiveau.setOnSelectListener ( new Function1<ThemedButton, Unit> () {
                                              @Override
                                              public Unit invoke(ThemedButton themedButton) {
                                                  MainActivity.xxNiveau = getNiveau ( themedButton.getId () );
                                                  xxNiveau =getNiveau ( themedButton.getId () );
                                                  return null;
                                              }
                                          }
        );

        // Couleur
        xxBtnCouleur.selectButton ( setCouleur ( xxCouleur ) );
        xxBtnCouleur.setOnSelectListener ( new Function1<ThemedButton, Unit> () {
                                              @Override
                                              public Unit invoke(ThemedButton themedButton) {
                                                  xxCouleur = getCouleur ( themedButton.getId ());
                                                  return null;
                                              }
                                          }
        );

        // Contre
        xxBtnContre.selectButton ( setContre ( xxContre ) );
        xxBtnContre.setOnSelectListener ( new Function1<ThemedButton, Unit> () {
                                               @Override
                                               public Unit invoke(ThemedButton themedButton) {
                                                   xxContre = getContre ( themedButton.getId ());
                                                   return null;
                                               }
                                           }
        );
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
    private Integer setNiveau(int id) {
        if (id == 1) return R.id.btn1;
        if (id == 2) return R.id.btn2;
        if (id == 3) return R.id.btn3;
        if (id == 4) return R.id.btn4;
        if (id == 5) return R.id.btn5;
        if (id == 6) return R.id.btn6;
        if (id == 7) return R.id.btn7;
        return 0;
    }

    private Integer getNiveau(int id) {
        if (id == R.id.btn1) return 1;
        if (id == R.id.btn2) return 2;
        if (id == R.id.btn3) return 3;
        if (id == R.id.btn4) return 4;
        if (id == R.id.btn5) return 5;
        if (id == R.id.btn6) return 6;
        if (id == R.id.btn7) return 7;
        return 0;
    }

    private Integer getCouleur(int id) {
        if (id == R.id.btnTrefle) return 0;
        if (id == R.id.btnCarreau) return 1;
        if (id == R.id.btnCoeur) return 2;
        if (id == R.id.btnPique) return 3;
        return -1;
    }
    private Integer setCouleur(int id) {
        if (id == 0) return R.id.btnTrefle;
        if (id == 1) return R.id.btnCarreau;
        if (id == 2) return R.id.btnCoeur;
        if (id == 3) return R.id.btnPique;
        return -1;
    }

    private Integer getContre(int id) {
        if (id == R.id.btnNonContre) return 0;
        if (id == R.id.btnContre) return 1;
        if (id == R.id.btnSurContre) return 2;
        return -1;
    }
    private Integer setContre(int id) {
        if (id ==0) return  R.id.btnNonContre;
        if (id == 1) return R.id.btnContre;
        if (id == 2) return R.id.btnSurContre;
        return -1;
    }
}

