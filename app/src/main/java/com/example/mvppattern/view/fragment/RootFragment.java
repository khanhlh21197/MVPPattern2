package com.example.mvppattern.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mvppattern.R;
import com.example.mvppattern.view.login.LoginFragment;
import com.example.mvppattern.view.signup.SignUpFragment;

public class RootFragment extends Fragment implements View.OnClickListener {
    TextView tvSignUp;
    TextView tvLogin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.root_fragment, container, false);
        initViews(view);
        registerListeners();
        return view;
    }

    private void registerListeners() {
        tvSignUp.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
    }

    private void initViews(View view) {
        tvLogin = view.findViewById(R.id.tv_login);
        tvSignUp = view.findViewById(R.id.tv_sign_up);
    }


    public static RootFragment newInstance(){
        return new RootFragment();
    }

    private void showFragment(Fragment fragment){
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootLayout, fragment, fragment.getTag())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_login:
                showFragment(LoginFragment.newInstance());
                break;
            case R.id.tv_sign_up:
                showFragment(SignUpFragment.newInstance());
                break;
        }
    }
}
