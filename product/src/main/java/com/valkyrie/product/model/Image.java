package com.valkyrie.product.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String type;
    @Lob
    private byte[] data;
    private String productId;
    
    public Image setId(int id) {
        this.id = id;
        return this;
    }

    public int getId() {
        return id;
    }

    public Image setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public Image setType(String type) {
        this.type = type;
        return this;
    }

    public String getType() {
        return type;
    }

    public Image setData(byte[] data) {
        this.data = data;
        return this;
    }

    public byte[] getData() {
        return data;
    }

    public Image setProductId(String productId) {
        this.productId = productId;
        return this;
    }

    public String getProductId() {
        return productId;
    }

}
