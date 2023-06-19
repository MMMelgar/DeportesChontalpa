package com.example.Deportes_Chontalpa.DB;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.Deportes_Chontalpa.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArticulosAdapter extends ArrayAdapter<Article> {

    private Context context;

    public ArticulosAdapter(Context context, List<Article> articulos) {
        super(context, 0, articulos);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.lista_item_carrito, parent, false);
        }
        Article articulo = getItem(position);
        if (articulo != null) {
            ImageView imagen = view.findViewById(R.id.Imagen);
            TextView nombre = view.findViewById(R.id.Nombre);
            TextView precio = view.findViewById(R.id.Precio);
            TextView cantidad = view.findViewById(R.id.Cantidad);
            TextView disponible = view.findViewById(R.id.Disponible);
            cantidad.setVisibility(View.GONE);
            disponible.setVisibility(View.GONE);
            Picasso.get().load(articulo.getImageUrl()).into(imagen);
            nombre.setText(articulo.getNombre());
            precio.setText("Precio: " + articulo.getPrecio());
        }

        return view;
    }
}

