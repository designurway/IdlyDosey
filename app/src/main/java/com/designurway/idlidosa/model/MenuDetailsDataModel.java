package com.designurway.idlidosa.model;

import java.util.ArrayList;

public class MenuDetailsDataModel {
    private String status;
    private ArrayList<MenuDetailsModel> data;

    public MenuDetailsDataModel(String status, ArrayList<MenuDetailsModel> data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<MenuDetailsModel> getData() {
        return data;
    }

    public void setData(ArrayList<MenuDetailsModel> data) {
        this.data = data;
    }
}
