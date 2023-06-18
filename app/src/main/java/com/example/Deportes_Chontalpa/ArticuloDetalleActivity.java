package com.example.Deportes_Chontalpa;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.Deportes_Chontalpa.DB.Article;
import com.squareup.picasso.Picasso;

public class ArticuloDetalleActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView nombre,descripcion,talla, color, marca, precio, disponible, oferta;
    private Button agregarCarritoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_item_articulo);

        Definir();
        Article articulo = getIntent().getParcelableExtra("articulo");
        Set(articulo);
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
    }

    @SuppressLint("SetTextI18n")
    private void Set(Article articulo){
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
            }

            agregarCarritoButton.setOnClickListener(v -> {
                // Aquí puedes realizar la acción correspondiente al agregar al carrito
            });
        }else{
            finish();
        }
    }
}
