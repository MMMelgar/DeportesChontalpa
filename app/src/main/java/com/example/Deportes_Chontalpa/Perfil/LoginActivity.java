package com.example.Deportes_Chontalpa.Perfil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.Deportes_Chontalpa.R;
import com.example.Deportes_Chontalpa.SS2;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth Auth = FirebaseAuth.getInstance();
    private GoogleSignInClient GoogleSignInclient;
    int RC_SIGN_IN = 1;
    String TAG ="GoogleSignInLoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        GoogleSignInclient = GoogleSignIn.getClient(this,gso);*/
    }

    public void Registro(View view){
        startActivity(new Intent(this, Registro.class));
    }

    public void Recuperacion_Password(View view){
        startActivity(new Intent(this, Recuperacion_Password.class));
    }

    public void Inicio_Sesion(View view){
        TextView i_Text_Error=(TextView)findViewById(R.id.TextError);
        i_Text_Error.setText("");
        String en_Email = ((EditText) findViewById(R.id.Correo_Electronico)).getText().toString();
        String en_Password = ((EditText) findViewById(R.id.Contraseña)).getText().toString();
        if(!en_Email.isEmpty() && !en_Password.isEmpty()){
            login(en_Email, en_Password);
        }else{
            i_Text_Error.setText("Rellene todos los campos antes de continuar");
        }
    }

    public void signInGoogle(View view){
        Intent signInIntent = GoogleSignInclient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            if(task.isSuccessful()){

                try{
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    Log.d(TAG,"firebaseAuthWithGoogle:" + account.getId());
                    firebaseAuthWithGoogle(account.getIdToken());
                } catch (ApiException e){
                    Log.w(TAG,"Google sign in failed",e);
                }
            }else{
                Log.d(TAG, "Error, login no exitoso:" + task.getException().toString());
                Toast.makeText(this, "Ocurrio un error. Intentelo Nuevamente" + task.getException().toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void login(String Email, String Contraseña){
        TextView i_Text_Error=(TextView)findViewById(R.id.TextError);

        Auth.signInWithEmailAndPassword(Email,Contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(LoginActivity.this, SS2.class));
                    LoginActivity.this.finish();
                }else{
                    i_Text_Error.setText("Ocurrio un error\nVerifique los datos e intentelo nuevamente");
                }
            }
        });
    }

    private void firebaseAuthWithGoogle(String idToken){
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken,null);
        Auth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d(TAG, "signInWithCredential: success");
                    startActivity(new Intent(LoginActivity.this, SS2.class));
                    LoginActivity.this.finish();
                } else{
                    Log.w(TAG, "signInWithCredential_failure", task.getException());
                }
            }
        });
    }

}