package com.example.mvppattern.view.login;

import android.content.Context;

public class LoginPresenter implements LoginContract.Presenter {
    LoginContract.View mView;
    LoginManager manager = new LoginManager();

    public void setView(LoginContract.View view){
        mView = view;
    }

    @Override
    public void handleLogin(Context context, String email, String password) {
        if (email.isEmpty() || password.isEmpty()){
            mView.loginFail("Enter email and password!");
        }else{
            if (manager.login(context, email, password)){
                mView.loginSuccess();
            }else{
                mView.loginFail("Wrong password or email!");
            }
        }

    }
}
