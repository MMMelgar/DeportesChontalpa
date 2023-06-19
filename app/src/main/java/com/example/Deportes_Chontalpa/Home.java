package com.example.Deportes_Chontalpa;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.Deportes_Chontalpa.DB.Article;
import com.example.Deportes_Chontalpa.DB.ListaAdapterCat;
import com.example.Deportes_Chontalpa.Perfil.Perfil;
import com.example.Deportes_Chontalpa.Perfil.SessionManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    private Spinner categoria, deporte;
    private String selected;
    private ListaAdapterCat adapter;
    private List<Article> articulosList;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Articulos");
        setContentView(R.layout.activity_home);
        Botones();
        Spinners();
        Grid();
    }

    private void Botones(){
        ImageButton btnMenu, btnCarrito, btnPerfil;
        btnMenu = findViewById(R.id.btnMenu);
        btnCarrito = findViewById(R.id.btnCarrito);
        btnPerfil = findViewById(R.id.btnPerfil);

        btnMenu.setOnClickListener(v -> {
            startActivity(new Intent(Home.this, Home.class));
            finish();
        });

        btnCarrito.setOnClickListener(v -> {
            if(SessionManager.getInstance().getLogIn()){
                if(SessionManager.getInstance().getAdmi()){
                    Mensaje("Solo los usuarios pueden acceder al carrito");
                }else{
                    startActivity(new Intent(Home.this, Carrito.class));
                }
            }else{
                startActivity(new Intent(Home.this, Perfil.class));
            }
        });

        btnPerfil.setOnClickListener(v -> startActivity(new Intent(Home.this, Perfil.class)));
    }

    private void Spinners(){
        categoria=findViewById(R.id.spn1);
        deporte=findViewById(R.id.spn2);
        String [] opciones1={"Catalogo", "Novedades", "Ofertas", "Gym", "Deportes"};
        String [] opciones2={"Deportes", "Tenis", "Baloncesto", "Futbol", "Otros"};
        ArrayAdapter<String> adapter1= new ArrayAdapter<>(
                this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                opciones1);
        ArrayAdapter<String> adapter2= new ArrayAdapter<>(
                this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                opciones2);
        categoria.setAdapter(adapter1);
        deporte.setAdapter(adapter2);
        categoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected=categoria.getSelectedItem().toString();
                switch (selected){
                    case "Novedades":
                        deporte.setVisibility(View.GONE);
                        Query queryN= databaseReference.orderByChild("novedades").equalTo(true);
                        queryN.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                articulosList.clear();
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    Article articulo = snapshot.getValue(Article.class);
                                    articulosList.add(articulo);
                                }
                                adapter.notifyDataSetChanged();
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Mensaje("Hubo un error en la base de datos");
                            }
                        });
                        break;
                    case "Ofertas":
                        deporte.setVisibility(View.GONE);
                        Query queryO= databaseReference.orderByChild("ofertas").equalTo(true);
                        queryO.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                articulosList.clear();
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    Article articulo = snapshot.getValue(Article.class);
                                    articulosList.add(articulo);
                                }
                                adapter.notifyDataSetChanged();
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Mensaje("Hubo un error en la base de datos");
                            }
                        });
                        break;
                    case "Gym":
                        deporte.setVisibility(View.GONE);
                        Query queryG= databaseReference.orderByChild("categoria").equalTo("Gym");
                        queryG.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                articulosList.clear();
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    Article articulo = snapshot.getValue(Article.class);
                                    articulosList.add(articulo);
                                }
                                adapter.notifyDataSetChanged();
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Mensaje("Hubo un error en la base de datos");
                            }
                        });
                        break;
                    case "Deportes":
                        deporte.setVisibility(View.VISIBLE);
                        break;
                    case "Catalogo":
                        deporte.setVisibility(View.GONE);
                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                articulosList.clear();
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    Article articulo = snapshot.getValue(Article.class);
                                    articulosList.add(articulo);
                                }
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Mensaje("Hubo un error en la base de datos");
                            }
                        });
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
                        Query queryT= databaseReference.orderByChild("categoria").equalTo("Tenis");
                        queryT.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                articulosList.clear();
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    Article articulo = snapshot.getValue(Article.class);
                                    articulosList.add(articulo);
                                }
                                adapter.notifyDataSetChanged();
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Mensaje("Hubo un error en la base de datos");
                            }
                        });
                        break;
                    case "Baloncesto":
                        Query queryB= databaseReference.orderByChild("categoria").equalTo("Baloncesto");
                        queryB.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                articulosList.clear();
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    Article articulo = snapshot.getValue(Article.class);
                                    articulosList.add(articulo);
                                }
                                adapter.notifyDataSetChanged();
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Mensaje("Hubo un error en la base de datos");
                            }
                        });
                        break;
                    case "Futbol":
                        Query queryF= databaseReference.orderByChild("categoria").equalTo("Futbol");
                        queryF.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                articulosList.clear();
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    Article articulo = snapshot.getValue(Article.class);
                                    articulosList.add(articulo);
                                }
                                adapter.notifyDataSetChanged();
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Mensaje("Hubo un error en la base de datos");
                            }
                        });
                        break;
                    case "Otros":
                        Query queryO= databaseReference.orderByChild("categoria").equalTo("Otros");
                        queryO.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                articulosList.clear();
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    Article articulo = snapshot.getValue(Article.class);
                                    articulosList.add(articulo);
                                }
                                adapter.notifyDataSetChanged();
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Mensaje("Hubo un error en la base de datos");
                            }
                        });
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void Grid(){
        GridView gridView=findViewById(R.id.Lista);
        gridView.setAdapter(adapter());
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            try{
                Article articulo = articulosList.get(position);
                navigateToArticuloDetalle(articulo);
            }catch (Exception e){
                Mensaje("Ha ocurrido un error, intentalo, nuevamente");
            }
        });
    }

    private void navigateToArticuloDetalle(Article articulo) {
        Intent intent = new Intent(Home.this, ArticuloDetalleActivity.class);
        intent.putExtra("articulo", articulo);
        startActivity(intent);
    }

    private ListaAdapterCat adapter(){
        articulosList = new ArrayList<>();
        adapter=new ListaAdapterCat(this,articulosList);
        return adapter;
    }

    private void Mensaje(String msj){
        Toast toast=Toast.makeText(this,msj,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
        toast.show();
    }
}