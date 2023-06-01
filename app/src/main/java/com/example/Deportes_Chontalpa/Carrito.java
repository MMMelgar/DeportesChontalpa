package com.example.Deportes_Chontalpa;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Deportes_Chontalpa.DB.AdminSQLiteOpenHelper;
import com.example.Deportes_Chontalpa.DB.ListaAdapter;
import com.example.Deportes_Chontalpa.Entidades.DbRegistros;

import java.util.ArrayList;

public class Carrito extends AppCompatActivity {

    AdminSQLiteOpenHelper DB;
    ListView lista;
    TextView total;
    int C,Total;
    private ListaAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        definirLista();
    }

    public void ejecutarQuery() {
        DB = new AdminSQLiteOpenHelper(this);
        Cursor c=DB.getNotas("Carrito");
        C=0;
        Total=0;
        ArrayList<DbRegistros> modelo = new ArrayList<>();
        if(c.moveToLast()){
            modelo = Datos(c, modelo);
        }else{
            DbRegistros m= new DbRegistros();
            m.setNombre("No hay articulos en el carrito");
            modelo.add(m);
        }
        adaptador=new ListaAdapter(this,modelo);
    }

    public ArrayList<DbRegistros> Datos(Cursor c, ArrayList<DbRegistros> modelo){
            do{
                Cursor cc=DB.getNota("Arti",c.getString(1));
                if(cc.moveToLast()){
                    DbRegistros m= new DbRegistros();
                    m.setId(cc.getString(0));
                    m.setNombre(cc.getString(1));
                    m.setDescripcion(cc.getString(2));
                    m.setPrecio(cc.getString(3));
                    m.setDisponibles(cc.getString(4));
                    Cursor c1=DB.getNota("Talla",cc.getString(5));
                    if(c1.moveToLast()){
                        m.setTalla(c1.getString(2));
                    }else{
                        m.setTalla("N/A");
                    }
                    c1=DB.getNota("Color",cc.getString(6));
                    if(c1.moveToLast()){
                        m.setColor(c1.getString(2));
                    }else{
                        m.setColor("N/A");
                    }
                    c1=DB.getNota("Marca",cc.getString(7));
                    if(c1.moveToLast()){
                        m.setMarca(c1.getString(2));
                    }else{
                        m.setMarca("N/A");
                    }
                    c1=DB.getNota("Categoria",cc.getString(8));
                    if(c1.moveToLast()){
                        m.setCategoria(c1.getString(2));
                    }else{
                        m.setCategoria("N/A");
                    }
                    m.setCantidad(c.getString(2));
                    modelo.add(m);
                    Total=Total+(Integer.parseInt(m.getPrecio())*Integer.parseInt(m.getCantidad()));
                }
            }while(c.moveToPrevious());
        return modelo;
    }

    public void definirLista() {
        try {
            ejecutarQuery();
            lista = findViewById(R.id.lista);
            total = findViewById(R.id.txt_total);
            lista.setAdapter(adaptador);
            total.setText(total.getText() + "$" + Total);
        } catch (Exception e) {
            Mensaje("NO HAY ARTICULOS EN EL CARRITO");
        }
    }

    public void borrarPedido(View view) {
        try {
            DB.deleteTablas("Carrito");
            onCreate(null);
        } catch (Exception e) {
            Mensaje("SE HA VACIADO EL CARRITO");
            Intent intento = new Intent(this, Home.class);
            startActivity(intento);
        }
    }

    private void Mensaje(String msj){
        Toast toast=Toast.makeText(this,msj,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
        toast.show();
    }
}