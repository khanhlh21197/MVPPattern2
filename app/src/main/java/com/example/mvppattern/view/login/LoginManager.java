package com.example.mvppattern.view.login;

import android.content.Context;

import com.example.mvppattern.model.User;
import com.example.mvppattern.model.UserRepository;

public class LoginManager {
    public Boolean login(Context context, String email, String password){
        User user = UserRepository.getInstance(context).getUser(email, password);
        if (user == null){
            return false;
        }
        return true;
    }
}
