package com.andalus.wahbatasks.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.andalus.wahbatasks.models.User;
import com.andalus.wahbatasks.utils.Constants;

@Database(entities = {User.class}, version = 1)
public abstract class UsersDatabase extends RoomDatabase {
    private static UsersDatabase instance = null;

    public static UsersDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, UsersDatabase.class, Constants.DATABASE_NAME).build();
        }
        return instance;
    }

    public abstract UsersDao getUsersDao();
}
