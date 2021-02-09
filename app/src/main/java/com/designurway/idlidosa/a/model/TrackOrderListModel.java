package com.designurway.idlidosa.a.model;

import java.util.List;

public class TrackOrderListModel {

private String status;
private List<TrackOrderListData>data;

public TrackOrderListModel(String status, List<TrackOrderListData> data) {
        this.status = status;
        this.data = data;
        }

public String getStatus() {
        return status;
        }

public void setStatus(String status) {
        this.status = status;
        }

public List<TrackOrderListData> getData() {
        return data;
        }

public void setData(List<TrackOrderListData> data) {
        this.data = data;
        }
        }
