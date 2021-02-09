package com.designurway.idlidosa.model;

import com.google.gson.annotations.SerializedName;

public class MenuDetailsModel
{
    @SerializedName("product_id")
    private String id;

    @SerializedName(value = "product_name")
    private String name;

    private String price;

    private String image;

    private String description;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }
}
