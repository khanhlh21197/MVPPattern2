package com.example.mvppattern.view.signup;

import android.content.Context;

public interface SignUpContract {
    interface View{
        void registerSuccess();

        void registerFail(String message);

        void sendToLogin(String email, String password);
    }

    interface Presenter{
        void handleRegister(Context context, String email, String username, String password);
    }
}
