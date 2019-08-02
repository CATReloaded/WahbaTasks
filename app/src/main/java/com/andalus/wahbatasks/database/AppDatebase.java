package com.andalus.wahbatasks.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {TaskEntry.class}, version = 1, exportSchema = false)
public abstract class AppDatebase extends RoomDatabase
{
    private static final String DATABSASE_NAME="name";
    private static final Object LOCK=new Object();
    private static  AppDatebase sInstance;

    public static AppDatebase getInstance(Context context)
    {
        if(sInstance == null)
        {
            synchronized (LOCK)
            {
                sInstance= Room.databaseBuilder(context.getApplicationContext(),
                        AppDatebase.class, AppDatebase.DATABSASE_NAME).build();
            }
        }
        return sInstance;
    }

    public abstract TaskDao taskDao();
}
