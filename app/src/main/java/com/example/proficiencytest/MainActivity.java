package com.example.proficiencytest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.proficiencytest.presenter.Presenter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new Presenter(this);
        presenter.createInstance();
    }
}
