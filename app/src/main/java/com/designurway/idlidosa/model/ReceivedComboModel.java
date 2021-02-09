package com.designurway.idlidosa.model;

import com.google.gson.annotations.SerializedName;

public class ReceivedComboModel {

    @SerializedName("product_name")
    private String comboName;

    @SerializedName("description")
    private String comboDescription;

    @SerializedName("image")
    private String comboImage;

    @SerializedName("product_id")
    private String prodId;

    @SerializedName("combo_id")
    private String comboId;

    public ReceivedComboModel(String comboName, String comboDescription, String comboImage, String prodId, String comboId) {
        this.comboName = comboName;
        this.comboDescription = comboDescription;
        this.comboImage = comboImage;
        this.prodId = prodId;
        this.comboId = comboId;
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public String getComboDescription() {
        return comboDescription;
    }

    public void setComboDescription(String comboDescription) {
        this.comboDescription = comboDescription;
    }

    public String getComboImage() {
        return comboImage;
    }

    public void setComboImage(String comboImage) {
        this.comboImage = comboImage;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getComboId() {
        return comboId;
    }

    public void setComboId(String comboId) {
        this.comboId = comboId;
    }
}
