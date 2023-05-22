package com.example.DeportesChontalpa;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SS0 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ss0);

        new Handler().postDelayed(new Runnable(){
            public void run(){
                startActivity(new Intent(SS0.this, SS1.class));
                finish();
            }
        },1000);
    }
}