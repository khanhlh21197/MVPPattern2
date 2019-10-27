package com.example.mvppattern.view.signup;

import android.content.Context;

import com.example.mvppattern.model.User;

public class SignUpPresenter implements SignUpContract.Presenter {

    private SignUpContract.View mView;
    private SignUpManager manager = new SignUpManager();

    public void setView(SignUpContract.View view){
        mView = view;
    }

    @Override
    public void handleRegister(Context context, String email, String username, String password) {
        if (email.isEmpty() || username.isEmpty() || password.isEmpty()){
            mView.registerFail("Please enter the email and password");
        }else{
            User user = new User(email, password, username);
            if (manager.register(context, user)) {
                mView.registerSuccess();
            }else{
                mView.registerFail("User already exist !");
            }
        }
    }
}
