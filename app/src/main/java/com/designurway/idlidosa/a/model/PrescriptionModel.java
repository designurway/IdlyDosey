package com.designurway.idlidosa.a.model;

public class PrescriptionModel {
    private String prescriptionName;
    private String prescriptionQunatity;

    public PrescriptionModel(String prescriptionName, String prescriptionQunatity) {
        this.prescriptionName = prescriptionName;
        this.prescriptionQunatity = prescriptionQunatity;
    }

    public String getPrescriptionName() {
        return prescriptionName;
    }

    public void setPrescriptionName(String prescriptionName) {
        this.prescriptionName = prescriptionName;
    }

    public String getPrescriptionQunatity() {
        return prescriptionQunatity;
    }

    public void setPrescriptionQunatity(String prescriptionQunatity) {
        this.prescriptionQunatity = prescriptionQunatity;
    }
}
