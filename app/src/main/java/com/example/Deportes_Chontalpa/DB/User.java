package com.example.Deportes_Chontalpa.DB;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String nombre;
    private String correo;
    private String telefono;
    private List<String> direcciones;
    private List<String> metodosDePago;
    private List<String> carrito;

    // Constructor vacío necesario para Firebase
    public User() {
        carrito=new ArrayList<>();
    }

    public List<String> getCarrito(){
        return(carrito);
    }

    public void addCarrito(String articulo){
        carrito.add(articulo);
    }

    public User(String correo) {
        this.correo = correo;
    }
    public void guardarUsuario() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        String userId = databaseReference.push().getKey();
        databaseReference.child(userId).setValue(this);
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public List<String> getDirecciones() {
        return direcciones;
    }

    public List<String> getMetodosDePago() {
        return metodosDePago;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDirecciones(List<String> direcciones) {
        this.direcciones = direcciones;
    }

    public void setMetodosDePago(List<String> metodosDePago) {
        this.metodosDePago = metodosDePago;
    }


}
