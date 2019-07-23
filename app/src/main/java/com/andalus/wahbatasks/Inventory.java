package com.andalus.wahbatasks;

import java.io.Serializable;

public class Inventory implements Serializable
{
    private int image;
    private String name;
    private String size;

    public Inventory(int image, String text,String size) {
        this.image = image;
        this.name = text;
        this.size=size;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }
}
