package com.andalus.wahbatasks.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class User implements Serializable {
    @PrimaryKey
    private long id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "age")
    private int age;
    @ColumnInfo(name = "eyeColor")
    private String eyeColor;

    public User(long id, String name, int age, String eyeColor) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.eyeColor = eyeColor;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEyeColor() {
        return eyeColor;
    }
}
