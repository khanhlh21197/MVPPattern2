package com.example.mvppattern.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mvppattern.R;
import com.example.mvppattern.model.User;
import com.example.mvppattern.model.UserDb;
import com.example.mvppattern.model.UserRepository;
import com.example.mvppattern.view.fragment.RootFragment;

public class MainActivity extends AppCompatActivity {

    private Fragment shownFragment;
    private UserRepository userRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null){
            showFragment(RootFragment.newInstance());
        }
//        UserDb userDb = UserDb.getDb(this);
//        userRepository = UserRepository.getInstance(this);
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.rootLayout, fragment);
        transaction.commitNow();
        shownFragment = fragment;
    }
}
