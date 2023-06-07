package com.example.Deportes_Chontalpa.Perfil;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import com.example.Deportes_Chontalpa.R;
import com.example.Deportes_Chontalpa.SS2;

import androidx.appcompat.app.AppCompatActivity;

public class Perfil extends AppCompatActivity implements View.OnClickListener {
    private Button registerButton;
    private Button loginButton;
    private Button viewOrdersButton;
    private Button viewPersonalInfoButton;
    private Button configurationButton;
    private Button requestReturnButton;
    private Button logoutButton;
    private LinearLayout loggedInLayout;

    private boolean isLoggedIn = false;

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
        logoutButton = findViewById(R.id.logout_button);
        loggedInLayout = findViewById(R.id.logged_in_layout);

        registerButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);
        viewOrdersButton.setOnClickListener(this);
        viewPersonalInfoButton.setOnClickListener(this);
        configurationButton.setOnClickListener(this);
        requestReturnButton.setOnClickListener(this);
        logoutButton.setOnClickListener(this);

        updateUI();
    }

    private void updateUI() {
        if (isLoggedIn) {
            registerButton.setVisibility(View.GONE);
            loginButton.setVisibility(View.GONE);
            loggedInLayout.setVisibility(View.VISIBLE);
            animateView(loggedInLayout, 0f, 1f);
        } else {
            registerButton.setVisibility(View.VISIBLE);
            loginButton.setVisibility(View.VISIBLE);
            loggedInLayout.setVisibility(View.GONE);
        }
    }

    private void animateView(View view, float startAlpha, float endAlpha) {
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", startAlpha, endAlpha);
        alphaAnimator.setDuration(500);
        alphaAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        alphaAnimator.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_button:
                startActivity(new Intent(Perfil.this, Registro.class));
                break;
            case R.id.login_button:
                startActivity(new Intent(Perfil.this, LoginActivity.class));
                updateUI();
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
            case R.id.logout_button:

                isLoggedIn = false;
                updateUI();
                break;
        }
    }
}

