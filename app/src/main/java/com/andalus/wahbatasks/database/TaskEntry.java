package com.andalus.wahbatasks.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "task")
public class TaskEntry
{
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "eyeColor")
    private String eyeColor;

    @Ignore
    public TaskEntry( String name, String eyeColor) {
        this.name = name;
        this.eyeColor = eyeColor;
    }

    public TaskEntry(int id, String name, String eyeColor) {
        this.id = id;
        this.name = name;
        this.eyeColor = eyeColor;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }
}
