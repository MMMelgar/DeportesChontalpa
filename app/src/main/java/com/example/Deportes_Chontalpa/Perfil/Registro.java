package com.example.Deportes_Chontalpa.Perfil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Deportes_Chontalpa.SS2;
import com.example.Deportes_Chontalpa.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {

    DatabaseReference Database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }

    public void BtnRegistrar(View view) {
        TextView i_Text_Error2=(TextView)findViewById(R.id.textError2);
        Database = FirebaseDatabase.getInstance().getReference();
        TextView i_Text_Error5=(TextView)findViewById(R.id.textError5);
        i_Text_Error2.setText("");
        i_Text_Error5.setText("");
        String en_Email = ((EditText) findViewById(R.id.Contenido)).getText().toString();
        String en_Password = ((EditText) findViewById(R.id.New_Password)).getText().toString();
        String en_Usuario = ((EditText) findViewById(R.id.Titulo)).getText().toString();
        if(!en_Email.isEmpty() && !en_Password.isEmpty() && !en_Usuario.isEmpty()){
            if(en_Password.length() >= 6){
                registrar(en_Email, en_Password, en_Usuario);
            }else {
                i_Text_Error5.setText("La contraseña debe tener al menos 6 caracteres");
            }
        }else{
            i_Text_Error2.setText("Rellene todos los campos antes de continuar");
        }
    }

    private void registrar(String Email, String Contraseña, String Nombre){
        TextView i_Text_Error2=(TextView)findViewById(R.id.textError2);
        FirebaseAuth Auth = FirebaseAuth.getInstance();

        Auth.createUserWithEmailAndPassword(Email, Contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Map<String, Object> map = new HashMap<>();
                    map.put("Name", Nombre);
                    map.put("Email", Email);
                    map.put("Contraseña", Contraseña);

                    String id = Auth.getCurrentUser().getUid();
                    Database.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){
                                startActivity(new Intent(Registro.this, SS2.class));
                                finish();
                            }
                        }
                    });
                }else{
                    i_Text_Error2.setText("El registro no pudo realizarse");
                }
            }
        });
    }

}