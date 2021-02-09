package com.designurway.idlidosa.model;

import java.util.ArrayList;

public class DashComboDataModel {
    private String status;
    private ArrayList<DashComboModel> data;

    public DashComboDataModel(String status, ArrayList<DashComboModel> data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<DashComboModel> getData() {
        return data;
    }

    public void setData(ArrayList<DashComboModel> data) {
        this.data = data;
    }
}
