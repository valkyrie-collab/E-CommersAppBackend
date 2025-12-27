package com.valkyrie.product.model;

import java.util.List;

public class Product {
    private String id;
    private String name;
    private String des;
    private String type;
    private double rating;
    private String sellerId;
    private List<Image> images;

    public Product setId(String id) {
        this.id = id;
        return this;
    }

    public String getId() {
        return id;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setDes(String des) {
        this.des = des;
        return this;
    }

    public String getDes() {
        return des;
    }

    public Product setType(String type) {
        this.type = type;
        return this;
    }

    public String getType() {
        return type;
    }

    public Product setRating(double rating) {
        this.rating = rating;
        return this;
    }

    public double getRating() {
        return rating;
    }

    public Product setSellerId(String sellerId) {
        this.sellerId = sellerId;
        return this;
    }

    public String getSellerId() {
        return sellerId;
    }

    public Product setImages(List<Image> images) {
        this.images = images;
        return this;
    }

    public List<Image> getImages() {
        return images;
    }

}
