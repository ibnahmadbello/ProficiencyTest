package com.example.proficiencytest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

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

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    Presenter presenter;

    RecyclerView recyclerView;
    public RecyclerViewAdapter recyclerViewAdapter;
    List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new RecyclerViewAdapter(this, userList);
//        presenter = new Presenter(this);
//        presenter.createInstance();
        createInstance();
        recyclerView.setAdapter(recyclerViewAdapter);

    }

    public void createInstance(){
        Retrofit retrofit = UserApi.getRetrofit();
        UserService userService = retrofit.create(UserService.class);
        userService.getUsers().enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                List<User> users = fetchUsers(response);
                recyclerViewAdapter.setData(users);
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

}
