package com.example.innovationtest.data.model;

import android.util.Base64;
import android.util.Log;

import androidx.annotation.Nullable;
import com.example.innovationtest.data.remote.APIService;
import com.example.innovationtest.data.remote.ServiceGenerator;
import com.example.innovationtest.ui.login.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;

    private APIService dataSource;


    private Employee user = null;

    // private constructor : singleton access
    private LoginRepository() {

    }

    public static LoginRepository getInstance() {
        if (instance == null) {
            instance = new LoginRepository();
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;

    }

    private void setLoggedInUser(Employee user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public void login(int id, LoginActivity.LoginListener listener) {
       final Call<EmployeeResponse> batch = ServiceGenerator.getApiService().findEmployee(id);
        // final Call<PostResponse>  batch = apiClient.getApiService().autenticarUser(username, password);

        Log.d("Haciendo peticion","www");
        batch.enqueue(new Callback<EmployeeResponse>() {
            @Override
            public void onResponse(@Nullable Call<EmployeeResponse> call, @Nullable Response<EmployeeResponse> response) {

                if(response.code()==500){

                }else

                if (response.isSuccessful() && response.body() != null) {


                    EmployeeResponse logResp = response.body();
                    Log.d("Haciendo peticion",logResp+"");
                    //reviso si está actualizado
                    if(logResp.getStatus().equals("success")) //correcto
                    {

                        setLoggedInUser(logResp.getData());
                        listener.correcto(user);
                    }
                    else //aviso al usuario
                    {
                        listener.incorrecto(null);
                    }

                }else{
                    listener.incorrecto(null);
                }
            }

            @Override
            public void onFailure(@Nullable Call<EmployeeResponse> call, @Nullable Throwable t) {
                if (t != null) {
                    t.printStackTrace();

                    listener.incorrecto(null);
                }
            }
        });
    }

}