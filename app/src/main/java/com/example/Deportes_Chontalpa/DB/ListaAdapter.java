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
        this.context = context;
        this.notes = notes;
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
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.lista_item_articulo, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.Nombre = convertView.findViewById(R.id.Nombre);
            viewHolder.Descripcion = convertView.findViewById(R.id.Descripcion);
            viewHolder.Talla = convertView.findViewById(R.id.Talla);
            viewHolder.Color = convertView.findViewById(R.id.Color);
            viewHolder.Marca = convertView.findViewById(R.id.Marca);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        DbRegistros note = notes.get(position);
        viewHolder.Nombre.setText(note.getNombre());
        viewHolder.Descripcion.setText(note.getDescripcion());
        viewHolder.Talla.setText(note.getTalla());
        viewHolder.Color.setText(note.getColor());
        viewHolder.Marca.setText(note.getMarca());

        return convertView;
    }

    static class ViewHolder {
        TextView Nombre;
        TextView Descripcion;
        TextView Talla;
        TextView Color;
        TextView Marca;
    }
}

