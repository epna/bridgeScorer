package com.bridge.bridgescorer;

import android.app.DatePickerDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.travijuu.numberpicker.library.NumberPicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ernestoyaquello.com.verticalstepperform.Step;

public class stpDateSeance extends Step<repDateSeance> {


    TextView trnDate;
    NumberPicker trnSeance;
    DatePickerDialog picker;

    public stpDateSeance(String title, String subtitle, String nextButtonText) {
        super ( title, subtitle, nextButtonText );
    }

    @Override
    public repDateSeance getStepData() {
        repDateSeance laReponse = new repDateSeance();
        Date date=null ;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            date = format.parse(trnDate.getText ().toString ());
            System.out.println(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        laReponse.repDate = date;
        laReponse.repSeance = trnSeance.getMax ();
        return laReponse;
    }
    @Override
    public String getStepDataAsHumanReadableString() {

        return null;
    }
    @Override
    protected void restoreStepData(repDateSeance data) {

    }
    @Override
    protected IsDataValid isStepDataValid(repDateSeance stepData) {
        return null;
    }
    @Override
    protected View createStepContentLayout() {
        // We create this step view by inflating an XML layout
        LayoutInflater inflater = LayoutInflater.from ( getContext () );
        View timeStepContent = inflater.inflate ( R.layout.stepper_date, null, false );
        trnDate = timeStepContent.findViewById ( R.id.stepperDate );
        trnSeance = timeStepContent.findViewById ( R.id.npSeance );
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        trnDate.setText(day + "-" + (month + 1) + "-" + year);
        trnDate.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                picker = new DatePickerDialog(getContext (),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                trnDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        } );
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
