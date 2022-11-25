package com.bridge.bridgescorer;

import java.io.Serializable;
import java.util.Date;

class repDateSeance implements Serializable {
    public Date repDate;
    public Integer repSeance;

    public repDateSeance(Date repDate, Integer repSeance) {
        this.repDate = repDate;
        this.repSeance = repSeance;
    }

    public repDateSeance() {

    }

    public Date getRepDate() {
        return repDate;
    }

    public void setRepDate(Date repDate) {
        this.repDate = repDate;
    }

    public Integer getRepSeance() {
        return repSeance;
    }

    public void setRepSeance(Integer repSeance) {
        this.repSeance = repSeance;
    }
}
