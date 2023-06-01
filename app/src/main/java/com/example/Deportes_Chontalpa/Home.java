package com.example.Deportes_Chontalpa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Deportes_Chontalpa.Perfil.Perfil;


public class Home extends AppCompatActivity {

    private ImageButton btnMenu, btnCarrito, btnPerfil, btnBusqueda;
    private Spinner categoria, deporte;
    private String selected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Botones();
        Spinners();

    }

    private void Botones(){
        btnMenu = findViewById(R.id.btnMenu);
        btnCarrito = findViewById(R.id.btnCarrito);
        btnPerfil = findViewById(R.id.btnPerfil);
        btnBusqueda = findViewById(R.id.btnBusqueda);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Home.class));
                finish();
            }
        });

        btnCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Carrito.class));
                finish();
            }
        });

        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Perfil.class));
                finish();
            }
        });

        btnBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Perfil.class));
                finish();
            }
        });
    }

    private void Spinners(){
        categoria=(Spinner) findViewById(R.id.spn1);
        deporte=(Spinner) findViewById(R.id.spn2);
        String [] opciones1={"Catalogo", "Novedades", "Ofertas", "Recomendados", "Deportes"};
        String [] opciones2={"Deportes", "Tenis", "Baloncesto", "Futbol", "Otros"};
        ArrayAdapter<String> adapter1= new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,opciones1);
        ArrayAdapter<String> adapter2= new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,opciones2);
        categoria.setAdapter(adapter1);
        deporte.setAdapter(adapter2);
        categoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected=categoria.getSelectedItem().toString();
                switch (selected){
                    case "Novedades":
                        break;
                    case "Ofertas":
                        break;
                    case "Recomendados":
                        break;
                    case "Deportes":
                        deporte.setVisibility(View.VISIBLE);
                        break;
                    case "Catalogo":
                        deporte.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        deporte.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected=deporte.getSelectedItem().toString();
                switch (selected){
                    case "Tenis":
                        break;
                    case "Baloncesto":
                        break;
                    case "Futbol":
                        break;
                    case "Otros":
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

}
