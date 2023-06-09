package com.example.Deportes_Chontalpa;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.se.omapi.Session;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.Deportes_Chontalpa.DB.Article;
import com.example.Deportes_Chontalpa.DB.User;
import com.example.Deportes_Chontalpa.Perfil.Perfil;
import com.example.Deportes_Chontalpa.Perfil.SessionManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticuloDetalleActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView nombre,descripcion,talla, color, marca, precio, disponible, oferta;
    private EditText cantidadEditText;
    private Button agregarCarritoButton, decrementarButton, incrementarButton;
    private int cantidad=1;
    Article articulo;
    DatabaseReference databaseReference;
    Query query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_item_articulo);
        Definir();
        articulo = getIntent().getParcelableExtra("articulo");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        Set();
    }

    private void Definir(){
        imageView = findViewById(R.id.Imagen);
        nombre = findViewById(R.id.Nombre);
        descripcion = findViewById(R.id.Descripcion);
        talla = findViewById(R.id.Talla);
        color = findViewById(R.id.Color);
        marca = findViewById(R.id.Marca);
        precio = findViewById(R.id.Precio);
        disponible = findViewById(R.id.Disponible);
        oferta = findViewById(R.id.Oferta);
        agregarCarritoButton = findViewById(R.id.agregarCarritoButton);
        cantidadEditText = findViewById(R.id.CantidadEditText);
        decrementarButton = findViewById(R.id.DecrementarButton);
        incrementarButton = findViewById(R.id.IncrementarButton);
        decrementarButton.setOnClickListener(v -> {
            if (cantidad > 1) {
                cantidad--;
                cantidadEditText.setText(String.valueOf(cantidad));
            }
        });
        incrementarButton.setOnClickListener(v -> {
            if(articulo.getArticulosDisponibles()>=cantidad+1){
                cantidad++;
                cantidadEditText.setText(String.valueOf(cantidad));
            }else{
                Mensaje("No hay mas articulos disponibles");
                Mensaje("Intentalo mas tarde");
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void Set(){
        if (articulo != null) {
            Picasso.get().load(articulo.getImageUrl()).into(imageView);
            nombre.setText(articulo.getNombre());
            descripcion.setText(articulo.getDescripcion());
            talla.setText("Talla: " + articulo.getTalla());
            color.setText("Color: " + articulo.getColor());
            marca.setText("Marca: " + articulo.getMarca());
            if(articulo.getArticulosDisponibles()>0){
                disponible.setText("Disponible");
                disponible.setTextColor(Color.GREEN);
                cantidadEditText.setText(String.valueOf(cantidad));
                if(articulo.isOfertas()){
                    oferta.setText("OFERTA");
                    precio.setText("Precio: " + articulo.getPrecioNuevo());
                    precio.setTextColor(Color.GREEN);
                }else{
                    oferta.setVisibility(View.GONE);
                    precio.setText("Precio: " + articulo.getPrecio());
                    precio.setTextColor(Color.BLACK);
                }
            }else{
                disponible.setText("No disponible");
                disponible.setTextColor(Color.RED);
                oferta.setVisibility(View.GONE);
                oferta.setVisibility(View.GONE);
                agregarCarritoButton.setVisibility(View.GONE);
            }

            agregarCarritoButton.setOnClickListener(v -> {
                User();
            });
        }else{
            finish();
        }
    }

    private void User(){
        if(SessionManager.getInstance().getLogIn()){
            if(SessionManager.getInstance().getAdmi()){
                Mensaje("Solo los usuarios pueden añadir articulos al carrito");
            }else{
                String email=SessionManager.getInstance().getUserEmail().replace(".", "_");
                query = databaseReference.child(email);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            User currentUser = dataSnapshot.getValue(User.class);
                            if(currentUser.getCarrito()!=null){
                                currentUser.addCarrito(articulo.getNombre(),cantidad);
                                dataSnapshot.getRef().setValue(currentUser.toMap());
                                Mensaje("Artículo añadido al carrito");
                            }else{
                                Map<String, Integer> carrito = new HashMap<>();
                                carrito.put(articulo.getNombre(),cantidad);
                                currentUser.setCarrito(carrito);
                                dataSnapshot.getRef().setValue(currentUser.toMap());
                                Mensaje("Artículo añadido al carrito");
                            }
                        }else{
                            Mensaje("Ocurrio un error, intentalo mas tarde");
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        }else{
            startActivity(new Intent(ArticuloDetalleActivity.this, Perfil.class));
        }
    }

    private void Mensaje(String msj){
        Toast toast=Toast.makeText(this,msj,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
        toast.show();
    }

}
