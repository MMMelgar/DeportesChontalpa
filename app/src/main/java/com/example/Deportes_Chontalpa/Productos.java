package com.example.Deportes_Chontalpa;

import static android.text.InputType.TYPE_CLASS_NUMBER;
import static android.text.InputType.TYPE_CLASS_TEXT;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.example.Deportes_Chontalpa.DB.Article;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Productos extends AppCompatActivity {

    Spinner spiner, spc;
    EditText t1,t2,t3,t4,t5,t6,t7;
    Button btna;
    ImageButton btni;
    String selected="", categoria="", imageUrl;
    DatabaseReference databaseReference;
    Query query;
    private ActivityResultLauncher<Intent> selectImageLauncher;
    private Uri selectedImageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Articulos");
        Declaracion();
        Spiner(1);
        btna.setOnClickListener(v -> {
            switch (selected){
                case "Productos":
                    Producto();
                    break;
                case "Ofertas":
                    Ofertas();
                    break;
                default:
                    Mensaje("Selecciona una opcion para continuar");
                    break;
            }
        });
        btni.setOnClickListener(v-> selectImage());
        selectImageLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
            if(result.getResultCode() == RESULT_OK){
                Intent data = result.getData();
                if (data != null){
                    Uri imageUri = data.getData();
                    showSelectedImage(imageUri);
                }
            }
        });
    }
    
    private void Declaracion(){
        spiner= findViewById(R.id.spinner);
        spc= findViewById(R.id.spncat);
        t1= findViewById(R.id.etxt1);
        t2= findViewById(R.id.etxt2);
        t3= findViewById(R.id.etxt3);
        t4= findViewById(R.id.etxt4);
        t5= findViewById(R.id.etxt5);
        t6= findViewById(R.id.etxt6);
        t7= findViewById(R.id.etxt7);
        btna= findViewById(R.id.agregar);
        btni=findViewById(R.id.btnImage);
    }
    
    private void Seleccion(){
        switch (selected){
            case "Productos":
                t1.setHint("Nombre");
                t2.setHint("Descripcion");
                t3.setHint("Precio");
                t4.setHint("Disponibles");
                t5.setHint("Talla");
                t6.setHint("Color");
                t7.setHint("Marca");
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
                btni.setVisibility(View.VISIBLE);
                Spiner(2);
                t2.setInputType(TYPE_CLASS_TEXT);
                t3.setInputType(TYPE_CLASS_NUMBER);
                t4.setInputType(TYPE_CLASS_NUMBER);
                t5.setInputType(TYPE_CLASS_TEXT);
                t6.setInputType(TYPE_CLASS_TEXT);
                t7.setInputType(TYPE_CLASS_TEXT);
                break;
            case "Ofertas":
                t1.setHint("Nombre");
                t1.setText("");
                t2.setHint("Precio Nuevo");
                t2.setText("");
                t1.setVisibility(View.VISIBLE);
                t2.setVisibility(View.VISIBLE);
                t3.setVisibility(View.GONE);
                t4.setVisibility(View.GONE);
                t5.setVisibility(View.GONE);
                t6.setVisibility(View.GONE);
                t7.setVisibility(View.GONE);
                spc.setVisibility(View.GONE);
                btni.setVisibility(View.GONE);
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
                btni.setVisibility(View.GONE);
                break;
        }

    }
    
    private void Spiner(int v){
        switch (v){
            case 1:
                String [] opciones1 = {"Selecciona una opcion","Productos", "Ofertas"};
                ArrayAdapter<String> adapter1= new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,opciones1);
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
                ArrayAdapter<String> adapter2= new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,opciones2);
                spc.setAdapter(adapter2);
                spc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        categoria= spc.getSelectedItem().toString();
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

    private void showSelectedImage(Uri imageUri){
        selectedImageUri = imageUri;
        btni.setImageURI(imageUri);
    }

    private void selectImage(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        selectImageLauncher.launch(intent);
    }

    private void Producto(){
        String T1,T2,T3,T4,T5,T6,T7;
        T1=t1.getText().toString().trim();
        T2=t2.getText().toString().trim();
        T3=t3.getText().toString().trim();
        T4=t4.getText().toString().trim();
        T5=t5.getText().toString().trim();
        T6=t6.getText().toString().trim();
        T7=t7.getText().toString().trim();
        if(!T1.isEmpty() && !T2.isEmpty() && !T3.isEmpty() && !T4.isEmpty() && !T5.isEmpty() && !T6.isEmpty() && !T7.isEmpty() && selectedImageUri!=null){
            try{
                int TT3 = Integer.parseInt(T3);
                int TT4 = Integer.parseInt(T4);
                if(TT3>=0 && TT4>=0){
                    try{
                        query = databaseReference.child("Articulos").orderByChild("nombre").equalTo(T1);
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    Mensaje("Articulo ya existente");
                                    t1.requestFocus();
                                } else {
                                    saveBD(T1, T2, TT3, TT4, T5, T6, T7);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Mensaje("Hubo un error en el guardado. Intentelo nuevamente");
                            }
                        });
                    }catch (Exception e){
                        Mensaje("Ocurrio un eror al guardar, intentalo nuevamente");
                    }
                }else{
                    Mensaje("Error. Revisa los datos e Intentelo Nuevamente");
                }
            }catch (Exception e){
                Mensaje("Error. Revisa los datos e Intentelo Nuevamente");
            }
        }else{
            Mensaje("Por favor llena todos los campos antes de continuar");
        }

    }

    private void saveBD(String T1,String T2, int TT3, int TT4, String T5, String T6, String T7){
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference imageReference = storageReference.child("articulos").child(selectedImageUri.getLastPathSegment());
        UploadTask uploadTask = imageReference.putFile(selectedImageUri);
        uploadTask.addOnSuccessListener(taskSnapshot -> imageReference.getDownloadUrl().addOnSuccessListener(downloadUrl -> {
                imageUrl = downloadUrl.toString();
                String key = databaseReference.child("Articulos").push().getKey();
                Article nuevoArticulo = new Article(key, T1, T2, TT3, TT4, T5, T6, T7, true, false, null, categoria, imageUrl);
                nuevoArticulo.guardarArticulo();
                Limpieza();
            }).addOnFailureListener(e -> Mensaje("Error al obtener la URL de la imagen"))).addOnFailureListener(e -> Mensaje("Error al subir la imagen"));
    }
    
    private void Ofertas(){
        String T1,T2;
        T1=t1.getText().toString().trim();
        T2=t2.getText().toString().trim();
        query = databaseReference.child("Articulos").child("nombre").orderByChild("nombre").equalTo(T1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    Mensaje("Articulo no existente");
                    t1.requestFocus();
                } else {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        DatabaseReference articleRef = snapshot.getRef();
                        articleRef.child("novedades").setValue(false);
                        articleRef.child("ofertas").setValue(true);
                        articleRef.child("precioNuevo").setValue(Integer.parseInt(T2));
                    }
                    Mensaje("Producto guardado exitosamente");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Mensaje("Hubo un error en el guardado. Intentelo nuevamente");
            }
        });
    }

    private void Limpieza(){
        Mensaje("Guardado");
        btni.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.baseline_image_24));
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        t5.setText("");
        t6.setText("");
        t7.setText("");
    }

    public void Mensaje(String msj){
        Toast toast=Toast.makeText(this,msj,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
        toast.show();
    }

}