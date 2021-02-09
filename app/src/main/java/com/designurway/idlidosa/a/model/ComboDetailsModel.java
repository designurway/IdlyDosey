package com.designurway.idlidosa.a.model;

import com.google.gson.annotations.SerializedName;

public class ComboDetailsModel {
    @SerializedName("product_id")
    private String productId;

    @SerializedName("product_name")
    private String productName;

    private String price;

    private String description;

    private String image;

    public ComboDetailsModel(String productId, String productName, String price, String description, String image) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
