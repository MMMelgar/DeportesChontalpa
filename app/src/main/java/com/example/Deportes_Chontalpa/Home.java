package com.example.Deportes_Chontalpa;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.Deportes_Chontalpa.DB.AdminSQLiteOpenHelper;
import com.example.Deportes_Chontalpa.DB.ListaAdapterCat;
import com.example.Deportes_Chontalpa.Entidades.DbRegistros;
import com.example.Deportes_Chontalpa.Perfil.Perfil;
import com.google.firebase.FirebaseApp;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    private ImageButton btnMenu, btnCarrito, btnPerfil, btnBusqueda;
    private Spinner categoria, deporte;
    private String selected, Nombre;
    private GridView lista;
    AdminSQLiteOpenHelper DB;
    private ListaAdapterCat adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_home);
        Botones();
        Spinners();
        Lista();
    }

    private void Botones(){
        btnMenu = findViewById(R.id.btnMenu);
        btnCarrito = findViewById(R.id.btnCarrito);
        btnPerfil = findViewById(R.id.btnPerfil);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Home.class));
                finish();
            }
        });

        btnCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Carrito.class));
            }
        });

        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Perfil.class));
            }
        });
    }

    private void Spinners(){
        categoria=(Spinner) findViewById(R.id.spn1);
        deporte=(Spinner) findViewById(R.id.spn2);
        String [] opciones1={"Catalogo", "Novedades", "Ofertas", "Recomendados", "Deportes"};
        String [] opciones2={"Deportes", "Tenis", "Baloncesto", "Futbol", "Otros"};
        ArrayAdapter<String> adapter1= new ArrayAdapter<String>(
                this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                opciones1);
        ArrayAdapter<String> adapter2= new ArrayAdapter<String>(
                this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                opciones2);
        categoria.setAdapter(adapter1);
        deporte.setAdapter(adapter2);
        categoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected=categoria.getSelectedItem().toString();
                switch (selected){
                    case "Novedades":
                        //startActivity(new Intent(Home.this, Novedades.class));
                        break;
                    case "Ofertas":
                        //startActivity(new Intent(Home.this, Ofertas.class));
                        break;
                    case "Recomendados":
                        //startActivity(new Intent(Home.this, Recomendados.class));
                        break;
                    case "Deportes":
                        deporte.setVisibility(View.VISIBLE);
                        break;
                    case "Catalogo":
                        deporte.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        deporte.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected=deporte.getSelectedItem().toString();
                switch (selected){
                    case "Tenis":
                        //startActivity(new Intent(Home.this, Tenis.class));
                        break;
                    case "Baloncesto":
                        //startActivity(new Intent(Home.this, Baloncesto.class));
                        break;
                    case "Futbol":
                        //startActivity(new Intent(Home.this, Futbol.class));
                        break;
                    case "Otros":
                        //startActivity(new Intent(Home.this, Otros.class));
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void Lista(){
        lista=(GridView) findViewById(R.id.Lista);
        lista.setAdapter(adapter());
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try{
                    DbRegistros m= (DbRegistros) adaptador.getItem(position);
                    Nombre=m.getNombre();
                    //Inserta logica para mandar a la pantalla para ver cada articulo
                }catch (Exception e){
                    Mensaje("Ha ocurrido un error, intentalo, nuevamente");
                }
            }
        });
    }

    private ListaAdapterCat adapter(){
        DB = new AdminSQLiteOpenHelper(this);
        ArrayList<DbRegistros> modelo = new ArrayList<>();
        DbRegistros m;
        Cursor c=DB.getNotas("Arti");
        if(!c.moveToLast()){
            m=new DbRegistros();
            m.setId("0");
            m.setNombre("No hay datos");
            m.setPrecio("$0");
            modelo.add(m);
            m.setId("1");
            m.setNombre("No hay datos");
            m.setPrecio("$0");
            modelo.add(m);
        }else{
            do{
                m=new DbRegistros();
                m.setId(c.getString(0));
                m.setNombre(c.getString(1));
                m.setPrecio(c.getString(3));
            }while(c.moveToPrevious());
        }
        adaptador=new ListaAdapterCat(this, modelo);
        return adaptador;
    }

    private void Mensaje(String msj){
        Toast toast=Toast.makeText(this,msj,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
        toast.show();
    }

}