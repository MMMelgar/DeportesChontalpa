package com.example.Deportes_Chontalpa.DB;

import android.content.Context;
import android.graphics.Color;
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
import java.util.Map;

public class CarritoAdapter extends ArrayAdapter<Article> {

    private Context context;
    private Map<String, Integer> cantidadMap;
    double totalArticulo, total;

    public CarritoAdapter(Context context, List<Article> articles, Map<String, Integer> cantidadMap) {
        super(context, 0, articles);
        this.context = context;
        this.cantidadMap = cantidadMap;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.lista_item_carrito, parent, false);
        }

        Article article = getItem(position);
        if (article != null) {
            ImageView imagen = view.findViewById(R.id.Imagen);
            TextView nombre = view.findViewById(R.id.Nombre);
            TextView precio = view.findViewById(R.id.Precio);
            TextView cantidad = view.findViewById(R.id.Cantidad);
            TextView disponible = view.findViewById(R.id.Disponible);
            TextView Total =view.findViewById(R.id.txt_total);

            Picasso.get().load(article.getImageUrl()).into(imagen);
            nombre.setText(article.getNombre());
            if(article.isOfertas()){
                precio.setText(String.valueOf(article.getPrecioNuevo()));
                if (cantidadMap != null && cantidadMap.containsKey(article.getNombre())) {
                    int cantidadArticulo = cantidadMap.get(article.getNombre());
                    cantidad.setText(String.valueOf(cantidadArticulo));
                    totalArticulo = cantidadArticulo * article.getPrecio();
                } else {
                    cantidad.setText("0");
                }
            }else{
                precio.setText(String.valueOf(article.getPrecio()));
                if (cantidadMap != null && cantidadMap.containsKey(article.getNombre())) {
                    int cantidadArticulo = cantidadMap.get(article.getNombre());
                    cantidad.setText(String.valueOf(cantidadArticulo));
                    totalArticulo = cantidadArticulo * article.getPrecioNuevo();
                } else {
                    cantidad.setText("0");
                }
            }
            if (article.getArticulosDisponibles() > 0) {
                disponible.setText("Disponible");
                disponible.setTextColor(Color.GREEN);
            } else {
                disponible.setText("No disponible");
                disponible.setTextColor(Color.RED);
            }
            total=total+totalArticulo;
            Total.setText(String.valueOf(total));
        }
        return view;
    }

}
