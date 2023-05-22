package com.example.DeportesChontalpa.ui.Productos;

import static android.text.InputType.TYPE_CLASS_NUMBER;
import static android.text.InputType.TYPE_CLASS_TEXT;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.DeportesChontalpa.DB.AdminSQLiteOpenHelper;
import com.example.DeportesChontalpa.R;

public class Productos extends Fragment {

    Spinner spiner, spc;
    EditText t1,t2,t3,t4,t5,t6,t7;
    Button btna;
    View view;
    String selected="", selected2="", Table="", Nombre="";
    AdminSQLiteOpenHelper DB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_productos, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        this.view=view;
        Declaracion();
        Spiner(1);
        btna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB = new AdminSQLiteOpenHelper(getActivity());
                switch (selected){
                    case "Productos":
                        Productos();
                        break;
                    case "Novedades":
                        Categoria(1);
                        break;
                    case "Ofertas":
                        Categoria(2);
                        break;
                    case "Recomendados":
                        Categoria(3);
                        break;
                    default:
                        Mensaje("Selecciona una opcion para continuar");
                        break;
                }
            }
        });
        super.onViewCreated(view,savedInstanceState);
    }

    private void Declaracion(){
        spiner= view.findViewById(R.id.spinner);
        spc= view.findViewById(R.id.spncat);
        t1= view.findViewById(R.id.etxt1);
        t2= view.findViewById(R.id.etxt2);
        t3= view.findViewById(R.id.etxt3);
        t4= view.findViewById(R.id.etxt4);
        t5= view.findViewById(R.id.etxt5);
        t6= view.findViewById(R.id.etxt6);
        t7= view.findViewById(R.id.etxt7);
        btna= view.findViewById(R.id.agregar);
    }

    private void Seleccion(){
        switch (selected){
            case "Productos":
                t1.setHint("Nombre");
                t2.setHint("Descripcion");
                t3.setHint("Precio");
                t4.setHint("Disponibles");
                t5.setText("Talla");
                t6.setText("Color");
                t7.setText("Marca");
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");
                t6.setText("");
                t7.setText("");
                t1.setVisibility(View.VISIBLE);
                t2.setVisibility(View.VISIBLE);
                t3.setVisibility(View.VISIBLE);
                t4.setVisibility(View.VISIBLE);
                t5.setVisibility(View.VISIBLE);
                t6.setVisibility(View.VISIBLE);
                t7.setVisibility(View.VISIBLE);
                spc.setVisibility(View.VISIBLE);
                Spiner(2);
                t2.setInputType(TYPE_CLASS_TEXT);
                t3.setInputType(TYPE_CLASS_NUMBER);
                t4.setInputType(TYPE_CLASS_NUMBER);
                t5.setInputType(TYPE_CLASS_TEXT);
                t6.setInputType(TYPE_CLASS_TEXT);
                t7.setInputType(TYPE_CLASS_TEXT);
                break;
            case "Novedades":
            case "Ofertas":
            case "Recomendados":
                    t1.setHint("Nombre");
                    t1.setText("");
                    t1.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.GONE);
                    t3.setVisibility(View.GONE);
                    t4.setVisibility(View.GONE);
                    t5.setVisibility(View.GONE);
                    t6.setVisibility(View.GONE);
                    t7.setVisibility(View.GONE);
                    spc.setVisibility(View.GONE);
                    break;
            default:
                t1.setVisibility(View.GONE);
                t2.setVisibility(View.GONE);
                t3.setVisibility(View.GONE);
                t4.setVisibility(View.GONE);
                t5.setVisibility(View.GONE);
                t6.setVisibility(View.GONE);
                t7.setVisibility(View.GONE);
                spc.setVisibility(View.GONE);
                break;
        }

    }

    private void Spiner(int v){
        switch (v){
            case 1:
                String [] opciones1 = {"Selecciona una opcion","Productos", "Novedades", "Ofertas", "Recomendados"};
                ArrayAdapter<String> adapter1= new ArrayAdapter<String>(getActivity(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,opciones1);
                spiner.setAdapter(adapter1);
                spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selected= spiner.getSelectedItem().toString();
                        Seleccion();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                break;
            case 2:
                String [] opciones2={"Selecciona una opcion","Gym", "Tenis", "Baloncesto", "Futbol", "Otros"};
                ArrayAdapter<String> adapter2= new ArrayAdapter<String>(getActivity(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,opciones2);
                spc.setAdapter(adapter2);
                spc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selected2= spc.getSelectedItem().toString();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                break;
            default:
                Mensaje("¿Todo bien?");
                break;
        }
    }

    private void Productos(){
        String T1,T2,T3,T4,T5,T6,T7;
        int ID1,ID2,ID3,ID4,ID5;
        T1=t1.getText().toString().trim();
        T2=t2.getText().toString().trim();
        T3=t3.getText().toString().trim();
        T4=t4.getText().toString().trim();
        T5=t5.getText().toString().trim();
        T6=t6.getText().toString().trim();
        T7=t7.getText().toString().trim();
        if(!T1.isEmpty() && !T2.isEmpty() && !T3.isEmpty() && !T4.isEmpty() && !T5.isEmpty() && !T6.isEmpty() && !T7.isEmpty()){
            try{
                if(new Integer(T3)>=0 && new Integer(T4)>=0){
                    if(Verificar("Arti",T1)){
                        Mensaje("Articulo ya existente");
                        t1.requestFocus();
                    }else{
                        try{
                            Table="Talla";
                            Nombre=T5;
                            if(Verificar(Table,Nombre)){
                                ID1 = GetID(Table,Nombre);
                            }else{
                                DB.AddNoteT(Table,Nombre);
                                ID1 = GetID(Table,Nombre);
                            }
                            Table="Color";
                            Nombre=T6;
                            if(Verificar(Table,Nombre)){
                                ID2 = GetID(Table,Nombre);
                            }else{
                                DB.AddNoteT(Table,Nombre);
                                ID2 = GetID(Table,Nombre);
                            }
                            Table="Marca";
                            Nombre=T7;
                            if(Verificar(Table,Nombre)){
                                ID3 = GetID(Table,Nombre);
                            }else{
                                DB.AddNoteT(Table,Nombre);
                                ID3 = GetID(Table,Nombre);
                            }
                            Table="Categoria";
                            Nombre=selected2;
                            if(Verificar(Table,Nombre)){
                                ID4 = GetID(Table,Nombre);
                            }else{
                                DB.AddNoteT(Table,Nombre);
                                ID4 = GetID(Table,Nombre);
                            }
                            if(selected2!="Gym"){
                                Table="Deporte";
                                Nombre=selected2;
                                if(Verificar(Table,Nombre)){
                                    ID5 = GetID(Table,Nombre);
                                }else{
                                    DB.AddNoteT(Table,Nombre);
                                    ID5 = GetID(Table,Nombre);
                                }
                            }else{
                                ID5=-1;
                            }
                            DB.AddNoteT(T1,T2,T3,T4,ID1,ID2,ID3,ID4,ID5);
                            Mensaje("Producto guardado exitosamente");
                        }catch(Exception e){
                            Mensaje("Hubo un error en el guardado. Intentelo nuevamente");
                        }
                    }
                }else{
                    Mensaje("Error. Revisa los datos e Intentelo Nuevamente");
                }
            }catch (Exception e){
                Mensaje("Error. Revisa los datos e Intentelo Nuevamente");
            }
        }

    }

    private void Categoria(int v){
        Table="Categoria";
        Nombre=selected;
        String T1=t1.getText().toString().trim();
        int ID;
        if(Verificar(Table,T1)){
            if(Verificar(Table,Nombre)){
                ID=GetID(Table,Nombre);
            }else{
                DB.AddNoteT(Table,Nombre);
                ID = GetID(Table,Nombre);
            }
            DB.updateT(Table,Nombre,ID,T1);
            Mensaje("Informacion actualizada");
        }else{
            Mensaje("Producto no encontrado, Intentelo nuevamente");
        }
    }

    private boolean Verificar(String Table, String Nombre){
        Cursor c= DB.getNota(Table, Nombre);
        String gettitle="";
        if(c.moveToFirst()){
            do{
                gettitle=c.getString(1);
            }while(c.moveToNext());
        }
        if(gettitle.equals(Nombre)){
            return true;
        }else{
            return false;
        }
    }

    private int GetID(String Table, String Nombre){
        Cursor c= DB.getNota(Table, Nombre);
        int ID=0;
        if(c.moveToFirst()){
            do{
                ID= new Integer (c.getString(0));
            }while(c.moveToNext());
        }
        return(ID);
    }

    public void Mensaje(String msj){
        Toast toast=Toast.makeText(getActivity(),msj,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
        toast.show();
    }

}