package com.example.Deportes_Chontalpa.Perfil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.Deportes_Chontalpa.Home;
import com.example.Deportes_Chontalpa.R;

public class Perfil extends AppCompatActivity implements View.OnClickListener{

    private Button registerButton;
    private Button loginButton;
    private Button viewOrdersButton;
    private Button viewPersonalInfoButton;
    private Button configurationButton;
    private Button requestReturnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        registerButton = findViewById(R.id.register_button);
        loginButton = findViewById(R.id.login_button);
        viewOrdersButton = findViewById(R.id.view_orders_button);
        viewPersonalInfoButton = findViewById(R.id.view_personal_info_button);
        configurationButton = findViewById(R.id.configuration_button);
        requestReturnButton = findViewById(R.id.request_return_button);

        registerButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);
        viewOrdersButton.setOnClickListener(this);
        viewPersonalInfoButton.setOnClickListener(this);
        configurationButton.setOnClickListener(this);
        requestReturnButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_button:
                startActivity(new Intent(Perfil.this, LoginActivity.class));
                break;
            case R.id.login_button:
                startActivity(new Intent(Perfil.this, LoginActivity.class));
                break;
            case R.id.view_orders_button:
                // Lógica para ver los pedidos realizados
                break;
            case R.id.view_personal_info_button:
                // Lógica para ver la información personal
                break;
            case R.id.configuration_button:
                // Lógica para la configuración
                break;
            case R.id.request_return_button:
                // Lógica para solicitar una devolución
                break;
        }
    }
}
