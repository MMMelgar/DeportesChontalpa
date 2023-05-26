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

public class ListaAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<DbRegistros> notes;

    public ListaAdapter(Context context, ArrayList<DbRegistros> notes){
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
            convertView= layoutInflater.inflate(R.layout.lista_item_registro,null);
        }

        TextView Nombre= (TextView) convertView.findViewById(R.id.Nombre);
        TextView Descripcion= (TextView) convertView.findViewById(R.id.Descripcion);
        TextView Talla= (TextView) convertView.findViewById(R.id.Talla);
        TextView Color= (TextView) convertView.findViewById(R.id.Color);
        TextView Marca= (TextView) convertView.findViewById(R.id.Marca);

        Nombre.setText(notes.get(position).getNombre());
        Descripcion.setText(notes.get(position).getDescripcion());
        Talla.setText(notes.get(position).getTalla());
        Color.setText(notes.get(position).getColor());
        Marca.setText(notes.get(position).getMarca());


        return convertView;
    }

}
