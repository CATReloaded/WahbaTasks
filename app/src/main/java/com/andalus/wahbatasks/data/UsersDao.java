package com.andalus.wahbatasks.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.andalus.wahbatasks.models.User;

import java.util.List;

@Dao
public interface UsersDao {

    @Query("SELECT * FROM User")
    public List<User> getAll();

    @Query("SELECT * FROM User WHERE eyeColor = :eyeColor")
    public List<User> getByEyeColor(String eyeColor);

    @Insert
    public void addUser(User user);

    @Update
    public void updateUser(User user);

}
