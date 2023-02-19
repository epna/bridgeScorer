package com.bridge.bridgescorer;

import java.io.Serializable;
import java.util.List;

import nl.bryanderidder.themedtogglebuttongroup.ThemedButton;

public class repContrat implements Serializable {
    public Integer conNiveau;
    public Integer conCouleur;
    public Integer conContre;

    public repContrat() {

    }



    public Integer getConCouleur() {
        return conCouleur;
    }

    public void setConCouleur(Integer conCouleur) {
        this.conCouleur = conCouleur;
    }

    public Integer getConContre() {
        return conContre;
    }

    public void setConContre(Integer conContre) {
        this.conContre = conContre;
    }

    public repContrat(List<ThemedButton> conNiveau, Integer conCouleur, Integer conContre) {
        this.conNiveau = 2;
        this.conCouleur = conCouleur;
        this.conContre = conContre;
    }
}
