package com.example.mvppattern.view.login;

import android.content.Context;

public interface LoginContract {
    interface View{
        void loginSuccess();

        void loginFail(String message);
    }

    interface Presenter{
        void handleLogin(Context context, String email, String password);
}
}
