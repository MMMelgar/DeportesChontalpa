package com.example.Deportes_Chontalpa;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SearchView;

public class Busqueda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Realizar la búsqueda cuando se presione el botón "Buscar" en el teclado
                performSearch(query);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String query) {
                return false;
            }
        });

// Configurar el evento de clic en el botón "Cerrar" del SearchView
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                startActivity(new Intent(Busqueda.this, Home.class));
                finish();
                return false;
            }
        });

    }


    private void performSearch(String query) {
        // Aquí puedes realizar la lógica para realizar la búsqueda en Internet
        // Puedes abrir una página web, hacer una solicitud a una API, etc.
        // Por ejemplo, puedes abrir una URL en el navegador web del dispositivo:
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=" + query));
        startActivity(intent);
    }
}