package com.bridge.bridgescorer;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class act_shareFile extends AppCompatActivity {
    public void shareFile(View view, File myFile, String titre) {
        //testShare ( view );
        Intent intent = new Intent ();
        intent.setAction ( Intent.ACTION_SEND );
        intent.setType ( "application/pdf" );
        Uri imageUri = FileProvider.getUriForFile (
                view.getContext (),
                "com.bridge.bridgescorer.provider", //(use your app signature + ".provider" )
                myFile );
        intent.setFlags ( Intent.FLAG_GRANT_READ_URI_PERMISSION );
        intent.putExtra ( android.content.Intent.EXTRA_SUBJECT, titre );
        intent.putExtra ( android.content.Intent.EXTRA_TEXT, "En piéce jointe, les résultats du tournoi. " );
        intent.putExtra ( Intent.EXTRA_STREAM, Intent.FLAG_ACTIVITY_NEW_TASK );
        intent.putExtra ( Intent.EXTRA_STREAM, imageUri );
        view.getContext ().startActivity ( Intent.createChooser ( intent, "Essai share" ) );
    }


    public void testShare(View view) {
        String filename = "SampleFile.pdf";
        String filepath = "MyFileStorage";
        File myExternalFile;


        myExternalFile = new File ( view.getContext ().getExternalFilesDir ( filepath ), filename );
        try {
            FileOutputStream fos = new FileOutputStream ( myExternalFile );
            fos.write ( "olivier2115454545".getBytes () );
            fos.close ();
            //readShare(myExternalFile);
        } catch (IOException e) {
            e.printStackTrace ();
        }


        Intent intent = new Intent ();
        intent.setAction ( Intent.ACTION_SEND );
        intent.setType ( "application/pdf" );
        Uri imageUri = FileProvider.getUriForFile (
                view.getContext (),
                "com.bridge.bridgescorer.provider", //(use your app signature + ".provider" )
                myExternalFile );
        intent.setFlags ( Intent.FLAG_GRANT_READ_URI_PERMISSION );
        intent.putExtra ( Intent.EXTRA_STREAM, Intent.FLAG_ACTIVITY_NEW_TASK );
        intent.putExtra ( Intent.EXTRA_STREAM, imageUri );
        view.getContext ().startActivity ( Intent.createChooser ( intent, "Essai share" ) );


    }

    public void readShare(File myFilexx) {
        try {
            FileInputStream fis = new FileInputStream ( myFilexx );
            DataInputStream in = new DataInputStream ( fis );
            BufferedReader br = new BufferedReader ( new InputStreamReader ( in ) );
            String strLine;
            while ((strLine = br.readLine ()) != null) {
                strLine += br.readLine ();
            }
            in.close ();
        } catch (IOException e) {
            e.printStackTrace ();
        }


    }
}