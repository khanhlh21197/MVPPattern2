package com.example.mvppattern.view.signup;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mvppattern.R;
import com.example.mvppattern.model.User;
import com.example.mvppattern.view.login.LoginPresenter;

public class SignUpFragment extends Fragment implements SignUpContract.View{
    public static SharedPreferences preferences = null;
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtUsername;
    private Switch mSwitch;
    private SignUpPresenter signUpPresenter;
    private Button btnSignUp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        initViews(view);
        registerListeners();
        initPresenter();
        return view;
    }

    private void registerListeners() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
    }

    private void signup() {
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        String username = edtUsername.getText().toString();

        signUpPresenter.handleRegister(getContext(), email, password, username);
    }

    private void initPresenter() {
        signUpPresenter = new SignUpPresenter();
        signUpPresenter.setView(this);
    }

    private void initViews(View view) {
        edtEmail = view.findViewById(R.id.edt_email);
        edtPassword = view.findViewById(R.id.edt_password);
        edtUsername = view.findViewById(R.id.edt_username);
        mSwitch = view.findViewById(R.id.switch_remember_me);
        btnSignUp = view.findViewById(R.id.btn_sign_up);
    }

    public static SignUpFragment newInstance(){
        return new SignUpFragment();
    }

    @Override
    public void registerSuccess() {
        Toast.makeText(getContext(), "SignUp Success!, Login now ",Toast.LENGTH_LONG).show();
    }

    @Override
    public void registerFail(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void sendToLogin(String email, String password) {

    }
}
