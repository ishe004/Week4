package com.cst2335.exercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String Email = "email";
    @Override
    protected void onStart() {
        super.onStart();
        Log.w("MainActivity", "In onStart() - The application is now visible on screen");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.w("MainActivity", "In onResume() - The application is now responding to user input");
    }

    @Override
    protected void onPause() {
        super.onPause();
         Log.w("MainActivity", "In onPause() - The application no longer responds to user input");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w("MainActivity", "In onStop() - The application is no longer visible");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w("MainActivity", "In onDestroy() - Any memory used by the application is freed.");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.w("MainActivity", "In onCreate() - Loading Widgets");
        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        Button loginButton = findViewById(R.id.button);
        EditText text = findViewById(R.id.edittext2);
        EditText emailtext= findViewById(R.id.edittext);
        loginButton.setOnClickListener(clk->{
            Intent nextPage = new Intent(MainActivity.this, SecondActivity.class);
           nextPage.putExtra("EmailAddress",emailtext.getText().toString());
          nextPage.putExtra("Age", 34);
            nextPage.putExtra("Name","Merlin");
            nextPage.putExtra("PostalCode", "K2B 6H5");

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("LoginName",emailtext.getText().toString());
            editor.apply();
            startActivity(nextPage);
        });
        prefs.getString("VariableName","");
        String emailAdress = prefs.getString("LoginName","");
        emailtext.setText(emailAdress);


    }
}