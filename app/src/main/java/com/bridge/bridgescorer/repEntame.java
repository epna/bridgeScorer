package com.bridge.bridgescorer;

import java.io.Serializable;

class repEntame implements Serializable {
        Integer entNiveauEntame, entCouleurEntame;
    public repEntame() {
    }

    public Integer getEntNiveauEntame() {
        return entNiveauEntame;
    }

    public void setEntNiveauEntame(Integer entNiveauEntame) {
        this.entNiveauEntame = entNiveauEntame;
    }

    public Integer getEntCouleurEntame() {
        return entCouleurEntame;
    }

    public void setEntCouleurEntame(Integer entCouleurEntame) {
        this.entCouleurEntame = entCouleurEntame;
    }
}
