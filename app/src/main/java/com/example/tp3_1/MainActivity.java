package com.example.tp3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startChiffre (View view){
        Intent intent = new Intent (MainActivity.this, ChiffrementActivity.class);
        startActivity(intent);
    }

    public void startDechiffre  (View view){
        Intent intent = new Intent (MainActivity.this, DechiffrementActivity.class);
        startActivity(intent);
    }
}