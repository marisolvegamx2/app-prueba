package com.example.innovationtest.ui.login;


import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.innovationtest.data.model.LoginRepository;


public class LoginViewModel extends ViewModel {


    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }


    public void login(String username, LoginActivity.LoginListener listener) {
        int id=0;
        try {

            id= Integer.parseInt(username);

            loginRepository.login(id,listener);

        }catch (NumberFormatException ex){
            Log.d("Haciendo peticion","error");
        }


    }


    // A placeholder username validation check
    public boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }

        return !username.trim().isEmpty();

    }


}