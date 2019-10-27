package com.example.mvppattern.view.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mvppattern.R;

public class LoginFragment extends Fragment implements LoginContract.View{
    public static SharedPreferences preferences = null;
    private EditText edtEmail;
    private EditText edtPassword;
    private Switch mSwitch;
    private LoginPresenter loginPresenter;
    private Button btnLogIn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initViews(view);
        initPresenter();
        registerListeners();
        initPreferences();
        return view;
    }

    private void initPreferences() {
        preferences = this.getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        if (preferences != null){
            String email = preferences.getString("email", null);
            String password = preferences.getString("password", null);
            Boolean isChecked = preferences.getBoolean("switch", false);

            edtEmail.setText(email);
            edtPassword.setText(password);
            mSwitch.setChecked(isChecked);
        }
    }

    private void initPresenter() {
        loginPresenter = new LoginPresenter();
        loginPresenter.setView(this);
    }

    private void registerListeners() {
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mSwitch.isChecked() == true){
                    rememberMe(edtEmail.getText().toString(), edtPassword.getText().toString());
                }
            }
        });
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
    }

    private void checkLogin() {
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        loginPresenter.handleLogin(getContext(), email, password);
    }

    private void initViews(View view) {
        edtEmail = view.findViewById(R.id.edt_email);
        edtPassword = view.findViewById(R.id.edt_password);
        mSwitch = view.findViewById(R.id.switch_remember_me);
        btnLogIn = view.findViewById(R.id.btn_login);
    }

    public static LoginFragment newInstance(){
        return new LoginFragment();
    }

    public void rememberMe(String email, String password){
        preferences = this.getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        preferences.edit().putString("email", email).apply();
        preferences.edit().putString("password", password).apply();
        preferences.edit().putBoolean("switch", mSwitch.isChecked());
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(getContext(), "Login Success! ",Toast.LENGTH_LONG).show();
    }

    @Override
    public void loginFail(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
