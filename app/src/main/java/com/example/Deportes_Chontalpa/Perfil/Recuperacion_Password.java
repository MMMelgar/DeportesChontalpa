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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperacion_password);
    }

    public void restablecer(View view){
        FirebaseAuth Auth = FirebaseAuth.getInstance();

        String en_Email = ((EditText) findViewById(R.id.correoElectronico)).getText().toString();
        TextView i_Text_Error6=(TextView)findViewById(R.id.textError6);
        i_Text_Error6.setText("");
        TextView i_Text_Confirmacion=(TextView)findViewById(R.id.TextConfirmacion);
        i_Text_Confirmacion.setText("");

        if(!en_Email.isEmpty()){
            Auth.setLanguageCode("es");
            Auth.sendPasswordResetEmail(en_Email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        i_Text_Confirmacion.setText("Se ha enviado un correo para restablecer la contraseña");
                    }else{
                        i_Text_Error6.setText("Ocurrio un error\n Por favor verifique los datos e intentelo nuevamente");
                    }
                }
            });
        }else{
            i_Text_Error6.setText("Rellene todos los campos antes de continuar");
        }
    }
}