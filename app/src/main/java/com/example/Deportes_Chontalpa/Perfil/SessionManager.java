package com.example.Deportes_Chontalpa.Perfil;

import androidx.annotation.NonNull;
import com.example.Deportes_Chontalpa.DB.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SessionManager {

    private static SessionManager instance;
    private String userEmail;
    protected Boolean Admi, LogIn;
    DatabaseReference databaseReference;
    Query query;

    private User user;

    private SessionManager() {
        Admi=false;
        LogIn=false;
    }

    public static synchronized SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Boolean getAdmi() {
        return Admi;
    }

    public Boolean getLogIn() {
        return LogIn;
    }

    public void setAdmi() {
        this.Admi=true;
    }

    public void Logout(){
        LogIn=false;
        userEmail=null;
        Admi=false;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        LogIn=true;
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        query = databaseReference.child("Users").orderByChild("correo").equalTo(userEmail);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    user = new User(userEmail);
                    user.guardarUsuario();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
