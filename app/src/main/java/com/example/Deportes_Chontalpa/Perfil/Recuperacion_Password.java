package com.example.Deportes_Chontalpa.Perfil;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Deportes_Chontalpa.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Recuperacion_Password extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperacion_password);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void restablecer(View view) {
        String enEmail = ((EditText) findViewById(R.id.correoElectronico)).getText().toString();
        TextView iTextError6 = findViewById(R.id.textError6);
        TextView iTextConfirmacion = findViewById(R.id.TextConfirmacion);
        iTextError6.setText("");
        iTextConfirmacion.setText("");

        if (!enEmail.isEmpty()) {
            firebaseAuth.setLanguageCode("es");
            firebaseAuth.sendPasswordResetEmail(enEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        iTextConfirmacion.setText("Se ha enviado un correo para restablecer la contraseña");
                    } else {
                        iTextError6.setText("Ocurrió un error. Por favor, verifique los datos e inténtelo nuevamente");
                    }
                }
            });
        } else {
            iTextError6.setText("Rellene todos los campos antes de continuar");
        }
    }
}
