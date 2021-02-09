package com.designurway.idlidosa.a.model;

import com.google.gson.annotations.SerializedName;

public class DashComboModel {
    @SerializedName("combo_id")
    private String comboId;

    @SerializedName("combo_name")
    private String comboName;

    @SerializedName("image")
    private String comboImage;

    public DashComboModel(String comboId, String comboName, String comboImage) {
        this.comboId = comboId;
        this.comboName = comboName;
        this.comboImage = comboImage;
    }

    public String getComboId() {
        return comboId;
    }

    public void setComboId(String comboId) {
        this.comboId = comboId;
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public String getComboImage() {
        return comboImage;
    }

    public void setComboImage(String comboImage) {
        this.comboImage = comboImage;
    }
}
