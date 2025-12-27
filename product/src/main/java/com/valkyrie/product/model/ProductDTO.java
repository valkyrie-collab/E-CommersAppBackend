package com.valkyrie.product.model;

import java.util.List;

public class ProductDTO {
    private String id;
    private String name;
    private String des;
    private String type;
    private double rating;
    private String sellerId;
    private List<ImageDTO> images;

    public ProductDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getId() {
        return id;
    }

    public ProductDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductDTO setDes(String des) {
        this.des = des;
        return this;
    }

    public String getDes() {
        return des;
    }

    public ProductDTO setType(String type) {
        this.type = type;
        return this;
    }

    public String getType() {
        return type;
    }

    public ProductDTO setRating(double rating) {
        this.rating = rating;
        return this;
    }

    public double getRating() {
        return rating;
    }

    public ProductDTO setSellerId(String sellerId) {
        this.sellerId = sellerId;
        return this;
    }

    public String getSellerId() {
        return sellerId;
    }

    public ProductDTO setImages(List<ImageDTO> images) {
        this.images = images;
        return this;
    }

    public List<ImageDTO> getImages() {
        return images;
    }

}
