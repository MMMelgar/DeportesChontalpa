package com.example.Deportes_Chontalpa;

/*import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;*/
import androidx.appcompat.app.AppCompatActivity;

public class Carrito extends AppCompatActivity {
/*
    AdminSQLiteOpenHelper DB;
    Cursor cursor;
    ListView lista;
    TextView total;
    int C,Total;
    private ListaAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        //Llamado al metodo definirLista()
        definirLista();
    }

    /**
     * Metodo encargado de hacer la conexion con la BD
     *//*
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
            m.setNombre("No hay productos en el carrito");
            modelo.add(m);
        }
        adaptador=new ListaAdapter(this,modelo);
    }

    public ArrayList<DbRegistros> Datos(Cursor c, ArrayList<DbRegistros> modelo){
        DbRegistros m= new DbRegistros();
        do{
            Cursor cc=DB.getNota("Arti",c.getString(1));
            if(cc.moveToLast()){
                m.setId(c.getString(0));
                m.setNombre(c.getString(1));
                m.setDescripcion(c.getString(2));
                m.setPrecio(c.getString(3));
                m.setDisponibles(c.getString(4));
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
                modelo.add(m);
            }
        }while(c.moveToPrevious());
        return modelo;
    }*/

    /**
     * Metodo encargado de definir los datos del ListView correspondiente
     *//*
    public void definirLista() {
        try {
            ejecutarQuery();
            cursor.moveToFirst();
            lista = findViewById(R.id.lista);
            total = findViewById(R.id.txt_total);
            int acum = 0;
            ArrayList<String> producto = new ArrayList<String>();
            do {
                int cant = Integer.parseInt(cursor.getString(1));
                int precio = Integer.parseInt(cursor.getString(2));
                producto.add(cursor.getString(0) + " \nCantidad: " + cant + " \n" +
                        "Precio unitario: $" + precio +
                        " \nSubtotal: $" + (precio * cant));
                acum = acum + (precio * cant);
            } while (cursor.moveToNext());//Fin Do-While
            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, producto);
            lista.setAdapter(adaptador);
            total.setText(total.getText() + "$" + acum);
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(this, "NO HAY ARTICULOS EN EL CARRITO",
                    Toast.LENGTH_LONG).show();
        }
    }*/

    /**
     * //limpiar el carrito
     *
     * @param view ParÃ¡metro por defecto
     */
    /*public void borrarPedido(View view) {
        //Captura de error de Activity
        try {
            //Se habilita BD para escucha
            HelperBD helper = new HelperBD(this);
            SQLiteDatabase db = helper.getReadableDatabase();
            //Se ejecuta una sentencia SQL que elimina todos los registros de la tabla
            db.execSQL(EstructuraBD.SQL_DELETE_REGISTERS);
            onCreate(null);
        } catch (Exception e) {//En caso de error de Activity producido
            //Se informa por pantalla que el carrito ha sido vaciado
            Toast.makeText(this, "SE HA VACIADO EL CARRITO", Toast.LENGTH_LONG).show();
            //Se inicia la Activity MainActivity
            Intent intento = new Intent(this, MainActivity.class);
            startActivity(intento);
        }//Fin Try-Catch
    }//Fin borrarPedido()*/
}