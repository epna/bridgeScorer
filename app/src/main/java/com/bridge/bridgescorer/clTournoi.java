package com.bridge.bridgescorer;

import androidx.work.impl.utils.SerialExecutor;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

class clTournoi implements Serializable {
    String touLibelle;
    Date touDate;
    int touNbDonnes;
    String touAdversaires;
    String touPartenaires;
    int touSeance;
    Boolean touPosition;
    Integer touId;



    public clTournoi(String touLibelle, Date  touDate, int touNbDonnes, String touAdversaires, String touPartenaires, int touSeance, boolean touPosition, Integer touId ) {
        this.touLibelle = touLibelle;
        this.touDate = touDate;
        this.touNbDonnes = touNbDonnes;
        this.touAdversaires = touAdversaires;
        this.touPartenaires = touPartenaires;
        this.touSeance = touSeance;
        this.touPosition = touPosition;
        this.touId=touId;

    }

    public Boolean getTouPosition() {
        return touPosition;
    }

    public void setTouPosition(Boolean touPosition) {
        this.touPosition = touPosition;
    }

    public Integer gettouId() {
        return touId;
    }

    public void setId(Integer id) {
        this.touId = touId;
    }


    public String gettouLibelle() {
        return touLibelle;
    }
    public void settouLibelle(String touLibelle) {
        this.touLibelle = touLibelle;
    }
    public Date gettouDate() {
        return touDate;
    }
    public void settouDate(Date  touDate) {
        this.touDate = touDate;
    }
    public int gettouNbDonnes() {
        return touNbDonnes;
    }
    public void settouNbDonnes(int touNbDonnes) {
        this.touNbDonnes = touNbDonnes;
    }
    public String gettouAdversaires() {
        return touAdversaires;
    }
    public void settouAdversaires(String touAdversaires) {
        this.touAdversaires = touAdversaires;
    }
    public String gettouPartenaires() {
        return touPartenaires;
    }
    public void settouPartenaires(String touPartenaires) {
        this.touPartenaires = touPartenaires;
    }
    public int gettouSeance() {
        return touSeance;
    }
    public void settouSeance(int touSeance) {
        this.touSeance = touSeance;
    }
    String strDateSeance() {
        return gettouDate () + " Séance : " + gettouSeance ();

    }
    String strAdvPart() {
        return "Adv. : " + gettouAdversaires () + " Part.  : " + gettouPartenaires ();
    }

    String dateFormatte() {
        SimpleDateFormat format = new SimpleDateFormat ( "E dd MMM yyyy" );
        String tempDate = format.format ( gettouDate () );
        return tempDate;
    }


}
