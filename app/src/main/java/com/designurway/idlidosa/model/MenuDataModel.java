package com.designurway.idlidosa.model;

import java.util.ArrayList;

public class MenuDataModel {
    private String status;
    private ArrayList<Menumodel> data;

    public MenuDataModel(String status, ArrayList<Menumodel> data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Menumodel> getData() {
        return data;
    }

    public void setData(ArrayList<Menumodel> data) {
        this.data = data;
    }
}
