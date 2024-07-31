package com.example.innovationtest.ui.login;

import android.app.Activity;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.innovationtest.MainActivity;
import com.example.innovationtest.R;
import com.example.innovationtest.data.model.Employee;
import com.example.innovationtest.databinding.ActivityLoginBinding;
import com.example.innovationtest.ui.Constantes;


public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;
    ProgressBar loadingProgressBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = binding.userid;

        final Button loginButton = binding.login;
        loadingProgressBar = binding.loading;



        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginDataChanged(usernameEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // loadingProgressBar.setVisibility(View.VISIBLE);
                if (loginViewModel.isUserNameValid(usernameEditText.getText().toString())) {

                    loginViewModel.login(usernameEditText.getText().toString(), new LoginListener());
                }
                else
                    Toast.makeText(LoginActivity.this,R.string.invalid_username, Toast.LENGTH_LONG).show();

            }
        });
    }

    private void updateUiWithUser(Employee usuario) {
        String welcome = getString(R.string.welcome) + usuario.getEmployee_name();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(Constantes.LOGGEADO){ //ya inicié sesión no pido iniciar
            entrar();
            //  finish();
        }
    }

    public void entrar() {
        Constantes.LOGGEADO = true;
        loadingProgressBar.setVisibility(View.GONE);

        Log.d("LoginActivity","entre");
        //mando a la siguiente actividad
        Intent intento=new Intent(this, MainActivity.class);
        startActivity(intento);
        finish();
    }

    public void loginDataChanged(String username) {
        if (!loginViewModel.isUserNameValid(username)) {
            Toast.makeText(this,R.string.invalid_username, Toast.LENGTH_LONG).show();
        }
    }

    public class LoginListener {

        public void incorrecto(String response) {
            //muestro error
            loadingProgressBar.setVisibility(View.GONE);
            showLoginFailed(R.string.login_failed);

        }

        public void correcto(Employee usuario) {

            if (usuario != null) {
                updateUiWithUser(usuario);

                entrar();
            } else {
                loadingProgressBar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, getString(R.string.invalid_username), Toast.LENGTH_LONG).show();
            }


        }
    }

}