package com.bridge.bridgescorer;

import android.app.DatePickerDialog;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.huynn109.IncreaseDecreaseButton;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ernestoyaquello.com.verticalstepperform.Step;

public class stpDateSeance extends Step<repDateSeance> {


    TextView trnDate;
    IncreaseDecreaseButton trnSeance;
    DatePickerDialog picker;
    private final Boolean xxModif;
    private Date xxDateSeance ;


    public stpDateSeance(String title, String subtitle, String nextButtonText, Boolean modif,Date dateSeance) {
        super ( title, subtitle, nextButtonText );
        xxModif= modif;
        if (modif) xxDateSeance=dateSeance;
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
        laReponse.repSeance = trnSeance.getCurrentNumber ();
        return laReponse;
    }
    @Override
    public String getStepDataAsHumanReadableString() {
        repDateSeance tmp = getStepData ();
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-YYYY");
        String strDate = format.format(tmp.repDate);
        String strReadable = strDate + " Séance " + tmp.repSeance.toString ();
        return strReadable;
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
        trnDate = timeStepContent.findViewById ( R.id.txtDate );
        trnSeance = timeStepContent.findViewById ( R.id.npSeance );
        final Calendar cldr = Calendar.getInstance();



        Date dateSeance= null ;
        if (xxModif)  dateSeance= xxDateSeance;    else dateSeance= Calendar.getInstance ().getTime ();


        int day          = Integer.parseInt ( (String) DateFormat.format("dd",   dateSeance) ); // 20
        int month          = Integer.parseInt ( (String) DateFormat.format("MM",   dateSeance) ); // 20
        int year          = Integer.parseInt ( (String) DateFormat.format("yyyy",   dateSeance) ); // 20

        String temp = day + "-" + (month) + "-" + year;
        trnSeance.setMinNumber ( 1 );
        trnSeance.setMaxNumber ( 4 );
        trnSeance.initNumber ( 1 );
        trnDate.setText(temp);

        trnDate.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                picker = new DatePickerDialog(getContext (),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                trnDate.setText(dayOfMonth + "-" + (monthOfYear+1 ) + "-" + year);
                            }
                        }, year, month-1, day);
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
