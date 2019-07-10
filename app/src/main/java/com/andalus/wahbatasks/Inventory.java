package com.andalus.wahbatasks;

public class Inventory
{
    int image;
    String name;
    String size;

    public Inventory(int image, String text,String s) {
        this.image = image;
        this.name = text;
        this.size=s;
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
