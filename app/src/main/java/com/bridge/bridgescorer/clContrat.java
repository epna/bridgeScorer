package com.bridge.bridgescorer;
import android.content.Context;
class clContrat {

    Integer déclarant, niveau, couleur, contre, numDonne, resultat;
    // 0=Personne 1=NS 2=EO 3=Tous
    //Integer[] mVulnerabilite = { 0, 1, 2, 3, 1, 1, 2, 3, 0, 2, 3, 0, 1, 3, 0, 1, 2};
    Integer[][] prime = new Integer[10][10];
    Integer[][] surlevees = new Integer[10][10];
    Integer[][][] chutes = new Integer[5][5][5];
    private Context context;

    public clContrat(Integer numDonne, Integer déclarant, Integer niveau, Integer couleur, Integer contre, Integer resultat, Context context) {
        this.déclarant = déclarant;
        this.niveau = niveau;
        this.couleur = couleur;
        this.contre = contre;
        this.numDonne = numDonne;
        this.resultat = resultat;
        this.context = context;

        prime[0][0] = 50; // partielle
        prime[0][1] = 50;
        prime[1][0] = 300; //manche
        prime[1][1] = 500;
        prime[2][0] = 800; //petit Chelem
        prime[2][1] = 1250;
        prime[3][0] = 1300; // grand Chelem
        prime[3][1] = 2000;


        // surleveés
        // 1 = contré 2 = XX 0=NV 1=V
        surlevees[0][0] = 0;
        surlevees[0][1] = 100;
        surlevees[1][1] = 200;
        surlevees[1][0] = 100;
        surlevees[1][1] = 200;
        surlevees[2][0] = 200;
        surlevees[2][1] = 400;

        chutes[0][0][1] = 50;
        chutes[0][0][2] = 100;
        chutes[0][0][3] = 150;
        chutes[0][0][4] = 50;

        chutes[1][0][1] = 100;
        chutes[1][0][2] = 200;
        chutes[1][0][3] = 300;
        chutes[1][0][4] = 100;

        chutes[0][1][1] = 100;
        chutes[0][1][2] = 300;
        chutes[0][1][3] = 500;
        chutes[0][1][4] = 300;

        chutes[1][1][1] = 200;
        chutes[1][1][2] = 500;
        chutes[1][1][3] = 800;
        chutes[1][1][4] = 300;

        chutes[0][2][1] = 200;
        chutes[0][2][2] = 600;
        chutes[0][2][3] = 1000;
        chutes[0][2][4] = 600;

        chutes[1][2][1] = 400;
        chutes[1][2][2] = 1000;
        chutes[1][2][3] = 1600;
        chutes[1][2][4] = 600;


    }

    public clContrat() {

    }

    public Integer getResultat() {
        return resultat;
    }

    public void setResultat(Integer resultat) {
        this.resultat = resultat;
    }

    public Integer getDéclarant() {
        return déclarant;
    }

    public void setDéclarant(Integer déclarant) {
        this.déclarant = déclarant;
    }

    public Integer getNiveau() {
        return niveau;
    }

    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }

    public Integer getCouleur() {
        return couleur;
    }

    public void setCouleur(Integer couleur) {
        this.couleur = couleur;
    }

    public Integer getContre() {
        return contre;
    }

    public void setContre(Integer contre) {
        this.contre = contre;
    }

    public Integer getNumDonne() {
        return numDonne;
    }

    public void setNumDonne(Integer numDonne) {
        this.numDonne = numDonne;
    }

    public Integer computeScore() {
        // cas du passe
        if (getNiveau () == 0) return 0;
        int xxResultat = getResultat () - 13;
        int xxVulnerable;
        int indexNumDonne = getNumDonne ();
        while (indexNumDonne > 16) indexNumDonne = indexNumDonne - 16;
        if (vulnerable ( indexNumDonne, getDéclarant () )) xxVulnerable = 1;
        else xxVulnerable = 0;
        Integer score = 0;
        if (xxResultat > -1) {

            if ((typeContrat ( getNiveau (), typeCouleur ( getCouleur () ) ) == clConstant.GRANDCHELEM) ||
                    (typeContrat ( getNiveau (), typeCouleur ( getCouleur () ) ) == clConstant.PETITCHELEM) ||
                    (typeContrat ( getNiveau (), typeCouleur ( getCouleur () ) ) == clConstant.MANCHE)) {
            score = scoreLevees (getCouleur(), getNiveau ());


            if (getContre ()>0 )score = (score * getContre () * 2);
            score += prime[typeContrat ( getNiveau (), typeCouleur ( getCouleur () ) ) ][xxVulnerable];
            //les surlevées
                if (getContre ()>0)score += (xxResultat * surlevees[getContre ()][xxVulnerable]); else
                {
                    if (typeCouleur ( getCouleur () ) == clConstant.clMINEUR)
                        score += xxResultat  * 20;
                    if (typeCouleur ( getCouleur () ) == clConstant.clMAJEUR)
                        score += xxResultat * 30;
                    if (typeCouleur ( getCouleur () ) == clConstant.clSANSATOUT)
                        score += xxResultat * 30;
                }
                if (getContre ()>0)score += 50*getContre ();

            return score;

        }


        // partielle non contrée

        if ((getContre () == 0) &&
                ((typeCouleur ( getCouleur () ) == clConstant.clMINEUR && getNiveau () < 5) ||
                        (typeCouleur ( getCouleur () ) == clConstant.clMAJEUR && getNiveau () < 4) ||
                        (typeCouleur ( getCouleur () ) == clConstant.clSANSATOUT && getNiveau () < 3))) {

            if (typeCouleur ( getCouleur () ) == clConstant.clMINEUR)
                score += (((getNiveau () + xxResultat)) * 20);
            if (typeCouleur ( getCouleur () ) == clConstant.clMAJEUR)
                score += (((getNiveau () + xxResultat)) * 30);
            if (typeCouleur ( getCouleur () ) == clConstant.clSANSATOUT)
                score += (40 + (((getNiveau ()) - 1) * 30)) + (xxResultat * 30);
            score += prime[0][0];
            return score;
        }

        // Partielle contrée mais fait pas la manche
        if ((getContre () > 0) &&
                ((typeCouleur ( getCouleur () ) == clConstant.clMINEUR && getNiveau () * getContre () * 2 < 5) ||
                        (typeCouleur ( getCouleur () ) == clConstant.clMAJEUR && getNiveau () * getContre () * 2 < 4) ||
                        (typeCouleur ( getCouleur () ) == clConstant.clSANSATOUT && getNiveau () * getContre () * 2 < 3))) {

            score=scoreLevees ( getCouleur (),getNiveau () );
            score = (score * getContre () * 2);
            score += prime[0][0];
            //les surlevées
            score += (xxResultat * surlevees[getContre ()][xxVulnerable]);
            if (getContre ()>0)score += 50*getContre ();
            return score;
        }

        // Partielle contrée et fait la manche
        if ((getContre () > 0) &&
                ((typeCouleur ( getCouleur () ) == clConstant.clMINEUR && getNiveau () * getContre () * 2 > 4) ||
                        (typeCouleur ( getCouleur () ) == clConstant.clMAJEUR && getNiveau () * getContre () * 2 > 3) ||
                        (typeCouleur ( getCouleur () ) == clConstant.clSANSATOUT && getNiveau () * getContre () * 2 > 2))) {

            score=scoreLevees ( getCouleur (),getNiveau () );
            score = (score * getContre () * 2);
            score += prime[1][xxVulnerable];
            //les surlevées
            score += (xxResultat * surlevees[getContre ()][xxVulnerable]);
            if (getContre ()>0)score += 50*getContre ();
            return score;
        }


    } else //contrat chuté

    {
        if (xxResultat == -1) score = chutes[xxVulnerable][getContre ()][1];
        if (xxResultat == -2) score = chutes[xxVulnerable][getContre ()][2];
        if (xxResultat == -3) score = chutes[xxVulnerable][getContre ()][3];
        if (xxResultat < -3)
            score = chutes[xxVulnerable][getContre ()][3] + (((-1 * xxResultat) - 3) * chutes[xxVulnerable][getContre ()][4]);
        return score;
    }
        return 999;
}
Integer scoreLevees(Integer xxCouleur, Integer xxNiveau)
{
    if (typeCouleur ( xxCouleur) == clConstant.clMINEUR)
        return xxNiveau * 20;
    if (typeCouleur ( xxCouleur ) == clConstant.clMAJEUR)
        return xxNiveau * 30;
    if (typeCouleur ( xxCouleur ) == clConstant.clSANSATOUT)
        return 40+((xxNiveau-1) * 30);
    return 999;
}
    Boolean vulnerable(int xxNumDonne, int xxDeclarant) {
        int[] arrVulnerabilite = context.getResources ().getIntArray ( R.array.vulnerabilite );
        if (arrVulnerabilite[xxNumDonne - 1] == clConstant.PERSONNE) return false;
        if (arrVulnerabilite[xxNumDonne - 1] == clConstant.TOUS) return true;
        if (arrVulnerabilite[xxNumDonne - 1] == clConstant.NS)
            return ((xxDeclarant == clConstant.NORD || xxDeclarant == clConstant.SUD));
        if (arrVulnerabilite[xxNumDonne - 1] == clConstant.EO)
            return (((xxDeclarant == clConstant.EST || xxDeclarant == clConstant.OUEST)));
        return false;
    }

    Integer typeContrat(Integer xxNiveau, Integer xxTypeCouleur) {
        if (xxNiveau == 7) return clConstant.GRANDCHELEM;
        if (xxNiveau == 6) return clConstant.PETITCHELEM;
        if (xxTypeCouleur == clConstant.clMAJEUR && (xxNiveau == 4 || xxNiveau == 5))
            return clConstant.MANCHE;
        if (xxTypeCouleur == clConstant.clMINEUR && (xxNiveau == 5)) return clConstant.MANCHE;
        if (xxTypeCouleur == clConstant.clSANSATOUT && (xxNiveau == 3 || xxNiveau == 4 || xxNiveau == 5))
            return clConstant.MANCHE;
        return clConstant.PARTIELLE;
    }

    Integer typeCouleur(Integer xxCouleur) {
        if (xxCouleur == clConstant.TREFLE || xxCouleur == clConstant.CARREAU)
            return clConstant.clMINEUR;
        if (xxCouleur == clConstant.COEUR || xxCouleur == clConstant.PIQUE)
            return clConstant.clMAJEUR;
        if (xxCouleur == clConstant.SANSATOUT) return clConstant.clSANSATOUT;
        return 0;
    }
}
