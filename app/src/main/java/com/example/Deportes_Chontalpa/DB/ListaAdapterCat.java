package com.example.Deportes_Chontalpa.DB;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.Deportes_Chontalpa.Entidades.DbRegistros;
import com.example.Deportes_Chontalpa.R;
import java.util.ArrayList;

public class ListaAdapterCat extends BaseAdapter {

    private Context context;
    private ArrayList<DbRegistros> notes;

    public ListaAdapterCat(Context context, ArrayList<DbRegistros> notes){
        this.context= context;
        this.notes=notes;
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView= layoutInflater.inflate(R.layout.lista_item_catalogo,null);
        }

        TextView Nombre1= (TextView) convertView.findViewById(R.id.Nombre1);
        TextView Precio1= (TextView) convertView.findViewById(R.id.Precio1);

        Nombre1.setText(notes.get(position).getNombre());
        Precio1.setText(notes.get(position).getPrecio());

        return convertView;
    }

}
