package com.bridge.bridgescorer;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.util.Date;

import ernestoyaquello.com.verticalstepperform.Step;

public class stepperDate extends Step<Date> {


  EditText trnDate;
 public stepperDate(String title, String subtitle, String nextButtonText) {
  super ( title, subtitle, nextButtonText );
 }

 @Override
 public Date getStepData() {
  return null;
 }

 @Override
 public String getStepDataAsHumanReadableString() {
  return null;
 }

 @Override
 protected void restoreStepData(Date data) {

 }

 @Override
 protected IsDataValid isStepDataValid(Date stepData) {
  return null;
 }

 @Override
 protected View createStepContentLayout() {

  // We create this step view by inflating an XML layout
  LayoutInflater inflater = LayoutInflater.from(getContext());
  View timeStepContent = inflater.inflate(R.layout.stepper_date, null, false);
  trnDate = timeStepContent.findViewById(R.id.stepperDate);
  return timeStepContent;


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
