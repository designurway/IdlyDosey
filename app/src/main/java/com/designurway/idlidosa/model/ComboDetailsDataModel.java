package com.designurway.idlidosa.model;

import java.util.ArrayList;

public class ComboDetailsDataModel {
    private String status;
    private ArrayList<ComboDetailsModel> data;

    public ComboDetailsDataModel(String status, ArrayList<ComboDetailsModel> data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<ComboDetailsModel> getData() {
        return data;
    }

    public void setData(ArrayList<ComboDetailsModel> data) {
        this.data = data;
    }
}
