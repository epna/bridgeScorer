package com.bridge.bridgescorer;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.github.angads25.toggle.model.ToggleableView;
import com.travijuu.numberpicker.library.NumberPicker;

import ernestoyaquello.com.verticalstepperform.Step;

public class stpDonnesPosition extends Step<repDonnesPosition> {


  NumberPicker trnNbDonnes;
  ToggleableView trnPosition;
 public stpDonnesPosition(String title, String subtitle, String nextButtonText) {
  super ( title, subtitle, nextButtonText );
 }

 @Override
 public repDonnesPosition getStepData() {
  repDonnesPosition temp = new repDonnesPosition (  );

  temp.repNbDonnes = trnNbDonnes.getValue ();
  temp.repPosition = trnPosition.isOn ();
  return temp;
  }



 @Override
 public String getStepDataAsHumanReadableString() {
   repDonnesPosition temp = getStepData ();
   String reponse;
   if (temp.repPosition) reponse = "Nord-Sud"; else reponse = "Est-Ouest";
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
  trnPosition = view.findViewById ( R.id.npSeance );

  // Here we generate the view that will be used by the library as the content of the step.
  // In this case we do it programmatically, but we could also do it by inflating an XML layout.
  trnNbDonnes = new NumberPicker (getContext());
  trnNbDonnes.setMin ( 8 );
  trnNbDonnes.setMin ( 32 );
  trnNbDonnes.setValue ( 24 );




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
