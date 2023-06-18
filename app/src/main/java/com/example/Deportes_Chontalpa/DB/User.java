package com.example.Deportes_Chontalpa.DB;

import com.example.Deportes_Chontalpa.Perfil.SessionManager;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private String userId;
    private String nombre;
    private String email;
    private String telefono;
    private List<String> direcciones;
    private List<String> metodosDePago;
    private List<String> carrito;

    public User() {
        carrito=new ArrayList<>();
    }

    public void setCarrito(List<String> carrito){
        this.carrito=carrito;
    }

    public List<String> getCarrito(){
        return(carrito);
    }

    public void addCarrito(String articulo){
        carrito.add(articulo);
    }

    public void guardarUsuario() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        userId = databaseReference.push().getKey();
        SessionManager.getInstance().setUserId(userId);
        Map<String, Object> usuarioValues = toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(userId, usuarioValues);

        databaseReference.updateChildren(childUpdates);
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("nombre", nombre);
        result.put("email", email);
        result.put("telefono", telefono);
        result.put("direcciones", direcciones);
        result.put("metodosDePago", metodosDePago);
        result.put("carrito", carrito);

        return result;
    }

    public String getUserId(){
        return userId;
    }
    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
