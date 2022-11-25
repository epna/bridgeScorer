package com.bridge.bridgescorer;

import java.io.Serializable;

class repDonnesPosition implements Serializable {



   public Integer repNbDonnes;
   public Boolean repPosition; // Nord-Sud = true

   public repDonnesPosition(Integer repNbDonnes, Boolean repPosition) {
      this.repNbDonnes = repNbDonnes;
      this.repPosition = repPosition;
   }

   public repDonnesPosition() {

   }

   public Integer getRepNbDonnes() {
      return repNbDonnes;
   }

   public void setRepNbDonnes(Integer repNbDonnes) {
      this.repNbDonnes = repNbDonnes;
   }

   public Boolean getRepPosition() {
      return repPosition;
   }

   public void setRepPosition(Boolean repPosition) {
      this.repPosition = repPosition;
   }
}
