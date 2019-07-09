package com.example.proficiencytest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.proficiencytest.model.NewUser;
import com.example.proficiencytest.presenter.Presenter;

public class CreateNewUser extends AppCompatActivity implements View.OnClickListener {

    public Button saveUserButton;
    public EditText nameEditText, jobEditText;

    Presenter presenter;
    public NewUser newUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_user);

        saveUserButton = findViewById(R.id.save_user);
        nameEditText = findViewById(R.id.name);
        jobEditText = findViewById(R.id.job);

        saveUserButton.setOnClickListener(this);

        presenter = new Presenter(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.save_user) {
            if (nameEditText.length() != 0 && jobEditText.length() != 0)
                newUser = new NewUser(nameEditText.getText().toString(), jobEditText.getText().toString());
            presenter.createInstance();
        }
    }
}
