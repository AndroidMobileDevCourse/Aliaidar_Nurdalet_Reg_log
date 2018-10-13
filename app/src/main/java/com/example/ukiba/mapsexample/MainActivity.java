package com.example.ukiba.mapsexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.backendless.Backendless;

public class MainActivity extends AppCompatActivity {

    public static final String APP_ID = "F46AF062-F5B2-237C-FFAD-C4F5CA7D6A00";
    public static final String SECRET_KEY = "0E058A23-3319-E776-FFFA-B6CFE6571A00";
    public static final String VERSION = "v1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Backendless.initApp(this, APP_ID, SECRET_KEY);
        if(Backendless.UserService.loggedInUser().equals("")){
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }else{
            System.out.println("ID OF USER -> " + Backendless.UserService.loggedInUser());
        }
    }
}
