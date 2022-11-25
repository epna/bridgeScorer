package com.bridge.bridgescorer;

import java.util.Date;

class tournoi {

    String trnLibelle;
    String trnDate;
    int trnNbDonnes;
    String trnAdversaires;
    String trnPartenaires;

    public tournoi(String trnLibelle, String trnDate, int trnNbDonnes, String trnAdversaires, String trnPartenaires) {
        this.trnLibelle = trnLibelle;
        this.trnDate = trnDate;
        this.trnNbDonnes = trnNbDonnes;
        this.trnAdversaires = trnAdversaires;
        this.trnPartenaires = trnPartenaires;
    }

    public String getTrnLibelle() {
        return trnLibelle;
    }

    public void setTrnLibelle(String trnLibelle) {
        this.trnLibelle = trnLibelle;
    }

    public String getTrnDate() {
        return trnDate;
    }

    public void setTrnDate(String trnDate) {
        this.trnDate = trnDate;
    }

    public int getTrnNbDonnes() {
        return trnNbDonnes;
    }

    public void setTrnNbDonnes(int trnNbDonnes) {
        this.trnNbDonnes = trnNbDonnes;
    }

    public String getTrnAdversaires() {
        return trnAdversaires;
    }

    public void setTrnAdversaires(String trnAdversaires) {
        this.trnAdversaires = trnAdversaires;
    }

    public String getTrnPartenaires() {
        return trnPartenaires;
    }

    public void setTrnPartenaires(String trnPartenaires) {
        this.trnPartenaires = trnPartenaires;
    }
}
