package com.example.mvppattern.model.dao;

import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mvppattern.model.User;

import java.util.List;

@Dao public interface UserDao {
    @Query("select * from user")
    List<User> getAllUsers();

    @Query("select * from user where email = :email and password = :password")
    User getUser(String email, String password);

    @Insert
    void insert(User... user);
}
