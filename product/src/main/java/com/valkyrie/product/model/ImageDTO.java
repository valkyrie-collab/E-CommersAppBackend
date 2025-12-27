package com.valkyrie.product.model;

import jakarta.persistence.Lob;

public class ImageDTO {
    private int id;
    private String name;
    private String type;
    @Lob
    private byte[] data;

    public ImageDTO setId(int id) {
        this.id = id;
        return this;
    }

    public int getId() {
        return id;
    }

    public ImageDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public ImageDTO setType(String type) {
        this.type = type;
        return this;
    }

    public String getType() {
        return type;
    }

    public ImageDTO setData(byte[] data) {
        this.data = data;
        return this;
    }

    public byte[] getData() {
        return data;
    }
}
