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

public class RegistrationActivity extends AppCompatActivity {

    private EditText loginEditText;
    private EditText passwordEditText;
    private EditText nameEditText;
    private EditText surnameEditText;

    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        loginEditText =    (EditText)findViewById(R.id.loginEditText);
        passwordEditText = (EditText)findViewById(R.id.passwordEditText);
        nameEditText =     (EditText)findViewById(R.id.nameEditText);
        surnameEditText =  (EditText)findViewById(R.id.surnameEditText);

        registerButton = (Button)findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = loginEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String name = nameEditText.getText().toString();
                String surname = surnameEditText.getText().toString();

                if(!login.equals("") && !password.equals("")) {
                    BackendlessUser user = new BackendlessUser();
                    user.setPassword(password);
                    user.setProperty("name", login);
                    user.setProperty("firstname", name);
                    user.setProperty("lastname", surname);
                    Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser response) {
                            Toast.makeText(RegistrationActivity.this, "You registered!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegistrationActivity.this, MapsActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(RegistrationActivity.this, "Registration failed!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    Toast.makeText(RegistrationActivity.this, "Login or password is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
