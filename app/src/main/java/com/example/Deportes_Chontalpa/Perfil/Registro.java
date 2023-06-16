package com.example.Deportes_Chontalpa.Perfil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;
import com.example.Deportes_Chontalpa.R;

public class Registro extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void BtnRegistrar(View view) {
        TextView iTextError2 = findViewById(R.id.textError2);
        TextView iTextError5 = findViewById(R.id.textError5);
        iTextError2.setText("");
        iTextError5.setText("");
        String enEmail = ((EditText) findViewById(R.id.Contenido)).getText().toString();
        String enPassword = ((EditText) findViewById(R.id.New_Password)).getText().toString();
        String enUsuario = ((EditText) findViewById(R.id.Titulo)).getText().toString();
        if (!enEmail.isEmpty() && !enPassword.isEmpty() && !enUsuario.isEmpty()) {
            if (enPassword.length() >= 6) {
                registrar(enEmail, enPassword, enUsuario);
            } else {
                iTextError5.setText("La contraseña debe tener al menos 6 caracteres");
            }
        } else {
            iTextError2.setText("Rellene todos los campos antes de continuar");
        }
    }

    private void registrar(String email, String contraseña, String nombre) {
        TextView iTextError2 = findViewById(R.id.textError2);
        firebaseAuth.createUserWithEmailAndPassword(email, contraseña)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("Name", nombre);
                        map.put("Email", email);
                        map.put("Contraseña", contraseña);

                        String id = firebaseAuth.getCurrentUser().getUid();
                        databaseReference.child("Users").child(id).setValue(map)
                                .addOnCompleteListener(task2 -> {
                                    if (task2.isSuccessful()) {
                                        Intent intent = new Intent();
                                        setResult(RESULT_OK,intent);
                                        finish();
                                    }
                                });
                    } else {
                        iTextError2.setText("El registro no pudo realizarse");
                    }
                });
    }
}
