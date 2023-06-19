package com.example.Deportes_Chontalpa.DB;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.Deportes_Chontalpa.R;

import java.util.List;

public class PedidosAdapter extends ArrayAdapter<Pedido> {

    private Context context;

    public PedidosAdapter(Context context, List<Pedido> pedidos) {
        super(context, 0, pedidos);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.lista_item_pedido, parent, false);
        }

        Pedido pedido = getItem(position);
        if (pedido != null) {
            TextView numeroPedido = view.findViewById(R.id.numeroPedido);
            TextView totalPedido = view.findViewById(R.id.totalPedido);
            ListView listaArticulos = view.findViewById(R.id.listaArticulos);

            numeroPedido.setText("Pedido #" + pedido.getNumeroPedido());
            totalPedido.setText("Total: " + pedido.getTotal());

            List<Article> articulos = pedido.getArticulos();
            ArticulosAdapter articulosAdapter = new ArticulosAdapter(context, articulos);
            listaArticulos.setAdapter(articulosAdapter);
        }

        return view;
    }
}



