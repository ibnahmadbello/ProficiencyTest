package com.example.proficiencytest.presenter;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.proficiencytest.CreateNewUser;
import com.example.proficiencytest.MainActivity;
import com.example.proficiencytest.adapter.RecyclerViewAdapter;
import com.example.proficiencytest.model.NewUser;
import com.example.proficiencytest.model.User;
import com.example.proficiencytest.model.UserResult;
import com.example.proficiencytest.network.UserApi;
import com.example.proficiencytest.network.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Presenter {

    private CreateNewUser userActivity;
    private static final String TAG = Presenter.class.getSimpleName();

    public Presenter(CreateNewUser userActivity) {
        this.userActivity = userActivity;
    }

    public void createInstance() {
        Retrofit retrofit = UserApi.getRetrofit();
        UserService userService = retrofit.create(UserService.class);
        userService.postUser(userActivity.newUser).enqueue(new Callback<NewUser>() {
            @Override
            public void onResponse(Call<NewUser> call, Response<NewUser> response) {
                NewUser newUser = response.body();
                if (response.isSuccessful() && newUser != null) {
                    Toast.makeText(userActivity, String.format("New User %s with job %s was created" +
                                    "at %s with id %s", newUser.getName(), newUser.getJob(),
                            newUser.getCreatedAt(), newUser.getId()),
                            Toast.LENGTH_LONG).show();
                    Log.d(TAG, "saved successfully");
                    userActivity.finish();
                }
            }

            @Override
            public void onFailure(Call<NewUser> call, Throwable t) {
                Toast.makeText(userActivity,
                        "Error is " + t.getMessage()
                        , Toast.LENGTH_LONG).show();
            }
        });

    }

}
