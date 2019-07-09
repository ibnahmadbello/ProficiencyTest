package com.example.proficiencytest.presenter;

import android.widget.Toast;

import com.example.proficiencytest.MainActivity;
import com.example.proficiencytest.model.UserResult;
import com.example.proficiencytest.network.UserApi;
import com.example.proficiencytest.network.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Presenter {

    private MainActivity mainActivity;
    private static final String TAG = Presenter.class.getSimpleName();

    public Presenter(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    public void createInstance(){
        Retrofit retrofit = UserApi.getRetrofit();
        UserService userService = retrofit.create(UserService.class);
        userService.getUsers().enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                Toast.makeText(mainActivity, ""+response.body(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserResult> call, Throwable t) {

            }
        });
    }

}
