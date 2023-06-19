package com.example.Deportes_Chontalpa.DB;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private String nombre;
    private String email;
    private String telefono;
    private List<String> direcciones;
    private List<String> metodosDePago;
    private Map<String,Integer> carrito;
    private List<Pedido> pedidos;

    public User() {
        carrito = new HashMap<>();
        pedidos = new ArrayList<>();
    }

    public void setCarrito(Map<String, Integer> carrito) {
        this.carrito = carrito;
    }

    public Map<String, Integer> getCarrito() {
        return carrito;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void addCarrito(String nombreArticulo, int cantidad) {
        carrito.put(nombreArticulo, cantidad);
    }
    public void removeArticuloDelCarrito(String nombreArticulo) {
        carrito.remove(nombreArticulo);
    }

    public void guardarUsuario() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        String emailKey = email.replace(".", "_");
        Map<String, Object> usuarioValues = toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(emailKey, usuarioValues);

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
        result.put("pedidos", pedidos);

        return result;
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
