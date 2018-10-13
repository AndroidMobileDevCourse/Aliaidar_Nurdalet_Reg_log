package com.example.ukiba.mapsexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class LoginActivity extends AppCompatActivity {

    private EditText loginEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEditText =  (EditText)findViewById(R.id.loginEditText);
        passwordEditText =  (EditText)findViewById(R.id.passwordEditText);
        loginButton = (Button)findViewById(R.id.loginButton);
        registerButton = (Button)findViewById(R.id.registerButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String login = loginEditText.getText().toString();
               String password = passwordEditText.getText().toString();

               if(!login.equals("") && !password.equals("")){
                   Backendless.UserService.login(login, password, new AsyncCallback<BackendlessUser>() {
                       @Override
                       public void handleResponse(BackendlessUser response) {
                           Toast.makeText(LoginActivity.this, "Log in failed!", Toast.LENGTH_SHORT).show();

                           Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
                           startActivity(intent);
                       }

                       @Override
                       public void handleFault(BackendlessFault fault) {
                           Toast.makeText(LoginActivity.this, "Log in failed!", Toast.LENGTH_SHORT).show();
                       }
                   });
               }else{
                   Toast.makeText(LoginActivity.this, "Login or password is empty", Toast.LENGTH_SHORT).show();
               }
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

    }

}
