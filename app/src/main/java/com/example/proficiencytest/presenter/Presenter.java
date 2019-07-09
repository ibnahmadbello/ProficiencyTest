package com.example.proficiencytest.presenter;

import android.util.Log;
import android.widget.Toast;

import com.example.proficiencytest.MainActivity;
import com.example.proficiencytest.adapter.RecyclerViewAdapter;
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

    private MainActivity mainActivity;
    private static final String TAG = Presenter.class.getSimpleName();
    List<User> userList = new ArrayList<>();
//    RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(Presenter.this, userList);

    public Presenter(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    public void createInstance(){
        Retrofit retrofit = UserApi.getRetrofit();
        UserService userService = retrofit.create(UserService.class);
        userService.getUsers().enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                List<User> users = fetchUsers(response);
//                recyclerViewAdapter.setData(users);
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
