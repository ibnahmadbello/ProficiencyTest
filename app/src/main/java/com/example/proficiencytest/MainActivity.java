package com.example.proficiencytest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.proficiencytest.adapter.RecyclerViewAdapter;
import com.example.proficiencytest.model.User;
import com.example.proficiencytest.model.UserResult;
import com.example.proficiencytest.network.UserApi;
import com.example.proficiencytest.network.UserService;
import com.example.proficiencytest.presenter.Presenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    RecyclerView recyclerView;
    public RecyclerViewAdapter recyclerViewAdapter;
    ProgressBar progressBar;
    List<User> userList = new ArrayList<>();
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(this);
        progressBar = findViewById(R.id.progress_bar);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new RecyclerViewAdapter(this, userList);

        createInstance();
        recyclerView.setAdapter(recyclerViewAdapter);

    }

    public void createInstance() {
        Retrofit retrofit = UserApi.getRetrofit();
        UserService userService = retrofit.create(UserService.class);
        userService.getUsers().enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                List<User> users = fetchUsers(response);
                recyclerViewAdapter.setData(users);
                progressBar.setVisibility(View.GONE);
                Log.d(TAG, "Adapter: " + users.get(0).getFirstName());
            }

            @Override
            public void onFailure(Call<UserResult> call, Throwable t) {
                Log.d(TAG, "Error message " + t.getMessage());
            }
        });
    }

    private List<User> fetchUsers(Response<UserResult> response) {
        UserResult userResult = response.body();
        Log.d(TAG, "Test" + userResult.getData().toString());
        return userResult.getData();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            Intent intent = new Intent(MainActivity.this, CreateNewUser.class);
            startActivity(intent);
        }

    }
}
