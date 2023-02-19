package com.bridge.bridgescorer;

import java.io.Serializable;

public class clResultat implements Serializable {


    int resIdTounois, resNumeroDonne, resDeclarant, resNiveau,resCouleur,resContre,  resResultat,resNiveauEntame, resCouleurEntame ;


    public clResultat(int resIdTounois,
                      int resNumeroDonne,
                      int resDeclarant, int resNiveau, int resCouleur, int resContre, int resResultat,   int resCouleurEntame, int resNiveauEntame) {
        this.resIdTounois = resIdTounois;
        this.resNumeroDonne = resNumeroDonne;
        this.resDeclarant = resDeclarant;
        this.resNiveau = resNiveau;
        this.resCouleur = resCouleur;
        this.resContre = resContre;
        this.resResultat = resResultat;
        this.resNiveauEntame = resNiveauEntame;
        this.resCouleurEntame = resCouleurEntame;

    }

    public int getResNiveauEntame() {
        return resNiveauEntame;
    }

    public void setResNiveauEntame(int resNiveauEntame) {
        this.resNiveauEntame = resNiveauEntame;
    }

    public int getReCouleurEntame() {
        return resCouleurEntame;
    }

    public void setReCouleurEntame(int reCouleurEntame) {
        this.resCouleurEntame = reCouleurEntame;
    }

    public int getResIdTounois() {
        return resIdTounois;
    }

    public void setResIdTounois(int resIdTounois) {
        this.resIdTounois = resIdTounois;
    }

    public int getResNumeroDonne() {
        return resNumeroDonne;
    }

    public void setResNumeroDonne(int resNumeroDonne) {
        this.resNumeroDonne = resNumeroDonne;
    }

    public int getResDeclarant() {
        return resDeclarant;
    }

    public void setResDeclarant(int resDeclarant) {
        this.resDeclarant = resDeclarant;
    }

    public int getResNiveau() {
        return resNiveau;
    }

    public void setResNiveau(int resNiveau) {
        this.resNiveau = resNiveau;
    }

    public int getResCouleur() {
        return resCouleur;
    }

    public void setResCouleur(int resCouleur) {
        this.resCouleur = resCouleur;
    }

    public int getResContre() {
        return resContre;
    }

    public void setResContre(int resContre) {
        this.resContre = resContre;
    }

    public int getResResultat() {
        return resResultat;
    }

    public void setResResultat(int resResultat) {
        this.resResultat = resResultat;
    }




}
