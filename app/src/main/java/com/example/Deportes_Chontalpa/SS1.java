package com.example.Deportes_Chontalpa;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SS1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ss1);

        new Handler().postDelayed(new Runnable(){
            public void run(){
                startActivity(new Intent(SS1.this, Home.class));
                finish();
            }
        },4000);
    }
}