package com.example.Deportes_Chontalpa.DB;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.Deportes_Chontalpa.R;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ListaAdapterCat extends BaseAdapter {

    private final Context context;
    private final List<Article> articulosList;

    public ListaAdapterCat(Context context, List<Article> articulosList){
        this.context= context;
        this.articulosList=articulosList;
    }

    @Override
    public int getCount() {
        return articulosList.size();
    }

    @Override
    public Object getItem(int position) {
        return articulosList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lista_item_catalogo, parent, false);
        }

        ImageView imageView = view.findViewById(R.id.Imagen1);
        TextView nombreTextView = view.findViewById(R.id.Nombre1);
        TextView precioTextView = view.findViewById(R.id.Precio1);

        Article articulo = articulosList.get(position);

        nombreTextView.setText(articulo.getNombre());
        if(articulo.isOfertas()){
            precioTextView.setText("$ "+ articulo.getPrecioNuevo());
            precioTextView.setTextColor(Color.GREEN);
        }else{
            precioTextView.setText("$ "+ articulo.getPrecio());
            precioTextView.setTextColor(Color.WHITE);
        }
        Picasso.get().load(articulo.getImageUrl()).into(imageView);

        return view;
    }

}
