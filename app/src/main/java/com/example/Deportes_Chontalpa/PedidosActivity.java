package com.example.Deportes_Chontalpa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.Deportes_Chontalpa.DB.Pedido;
import com.example.Deportes_Chontalpa.DB.PedidosAdapter;
import com.example.Deportes_Chontalpa.Perfil.SessionManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PedidosActivity extends AppCompatActivity {

    private ListView listaPedidos;
    private PedidosAdapter pedidosAdapter;
    private List<Pedido> listaDePedidos = new ArrayList<>();
    DatabaseReference usersRef;
    Query query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        listaPedidos = findViewById(R.id.listaPedidos);
        pedidosAdapter = new PedidosAdapter(this, listaDePedidos);
        listaPedidos.setAdapter(pedidosAdapter);

        usersRef = FirebaseDatabase.getInstance().getReference().child("Users");
        String email = SessionManager.getInstance().getUserEmail().replace(".", "_");

        query = usersRef.child(email).child("pedidos");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaDePedidos.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Pedido pedido = snapshot.getValue(Pedido.class);
                    if (pedido != null) {
                        listaDePedidos.add(pedido);
                    }
                }
                pedidosAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}

