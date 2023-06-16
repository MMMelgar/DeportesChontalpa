package com.example.Deportes_Chontalpa.Perfil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.Deportes_Chontalpa.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private GoogleSignInClient googleSignInClient;
    private static final int RC_SIGN_IN = 1;
    private static final String TAG = "GoogleSignInLoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    public void Recuperacion_Password(View view) {
        startActivity(new Intent(this, Recuperacion_Password.class));
    }

    public void Inicio_Sesion(View view) {
        TextView iTextError = findViewById(R.id.TextError);
        iTextError.setText("");

        String enEmail = ((EditText) findViewById(R.id.Correo_Electronico)).getText().toString();
        String enPassword = ((EditText) findViewById(R.id.Contraseña)).getText().toString();

        if (!enEmail.isEmpty() && !enPassword.isEmpty()) {
            login(enEmail, enPassword);
        } else {
            iTextError.setText("Rellene todos los campos antes de continuar");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }

    private void login(String email, String password) {
        TextView iTextError = findViewById(R.id.TextError);

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(
                this, task -> {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent();
                        setResult(RESULT_OK,intent);
                        finish();
                    } else {
                        iTextError.setText("Ocurrió un error. Verifique los datos e intente nuevamente");
                    }
                });
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this,
                task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "signInWithCredential: success");
                    } else {
                        Log.w(TAG, "signInWithCredential_failure", task.getException());
                    }
                });
    }
}
