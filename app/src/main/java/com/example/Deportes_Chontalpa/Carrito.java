package com.example.Deportes_Chontalpa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.Deportes_Chontalpa.DB.Article;
import com.example.Deportes_Chontalpa.DB.CarritoAdapter;
import com.example.Deportes_Chontalpa.DB.User;
import com.example.Deportes_Chontalpa.Perfil.Perfil;
import com.example.Deportes_Chontalpa.Perfil.SessionManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Carrito extends AppCompatActivity {

    private ListView listaCarrito;
    private CarritoAdapter carritoAdapter;
    private List<Article> articulosEnCarrito = new ArrayList<>();
    private Map<String, Integer> cantidadesEnCarrito = new HashMap<>();
    DatabaseReference usersRef, articulosRef;
    Query query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        Verificacion();
    }

    private void Verificacion() {
        if (SessionManager.getInstance().getLogIn()) {
            if (SessionManager.getInstance().getAdmi()) {
                Mensaje("Solo los usuarios pueden añadir articulos al carrito");
            } else {
                usersRef = FirebaseDatabase.getInstance().getReference().child("Users");
                articulosRef = FirebaseDatabase.getInstance().getReference().child("Articulos");
                listaCarrito = findViewById(R.id.lista);
                carritoAdapter = new CarritoAdapter(this, articulosEnCarrito, cantidadesEnCarrito);
                listaCarrito.setAdapter(carritoAdapter);
                CargarDatosCarrito();
            }
        } else {
            startActivity(new Intent(Carrito.this, Perfil.class));
            finish();
        }
    }

    private void CargarDatosCarrito() {
        String email = SessionManager.getInstance().getUserEmail().replace(".", "_");
        query = usersRef.child(email);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    User currentUser = dataSnapshot.getValue(User.class);
                    if (currentUser != null && currentUser.getCarrito() != null) {
                        Map<String, Integer> carritoUsuario = currentUser.getCarrito();
                        articulosRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                articulosEnCarrito.clear();
                                cantidadesEnCarrito.clear();
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    Article article = snapshot.getValue(Article.class);
                                    if (article != null && carritoUsuario.containsKey(article.getNombre()) && article.getArticulosDisponibles() > 0) {
                                        articulosEnCarrito.add(article);
                                        cantidadesEnCarrito.put(article.getNombre(), carritoUsuario.get(article.getNombre()));
                                    }
                                }
                                if (articulosEnCarrito.isEmpty()) {
                                    Article nuevoArticulo = new Article("No hay artículos", null, 0, 0, null, null, null, true, false, 0.0, null, null);
                                    articulosEnCarrito.add(nuevoArticulo);
                                    cantidadesEnCarrito.put(nuevoArticulo.getNombre(), 1);
                                }
                                carritoAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Mensaje("Ocurrió un error");
                                finish();
                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Manejar el error
            }
        });
    }

    private void Mensaje(String msj){
        Toast.makeText(this,msj,Toast.LENGTH_SHORT).show();
    }
}