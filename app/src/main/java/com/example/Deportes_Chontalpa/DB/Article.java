package com.example.Deportes_Chontalpa.DB;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Article {
    private String key;
    private String nombre;
    private String descripcion;
    private double precio;
    private int articulosDisponibles;
    private String talla;
    private String color;
    private String marca;
    private boolean novedades;
    private boolean ofertas;
    private Double precioNuevo;
    private String categoria;
    private String imageUrl;

    // Constructor vacío necesario para Firebase
    public Article() {
    }

    public Article(String key,String nombre, String descripcion, double precio, int articulosDisponibles, String talla, String color,
                   String marca, boolean novedades, boolean ofertas, Double precioNuevo, String categoria, String imageUrl) {
        this.key=key;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.articulosDisponibles = articulosDisponibles;
        this.talla = talla;
        this.color = color;
        this.marca = marca;
        this.novedades = novedades;
        this.ofertas = ofertas;
        this.precioNuevo = precioNuevo;
        this.categoria = categoria;
        this.imageUrl=imageUrl;
    }

    public void guardarArticulo() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Articulos");
        String articleId = databaseReference.push().getKey();
        databaseReference.child(articleId).setValue(this);
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public int getArticulosDisponibles() {
        return articulosDisponibles;
    }

    public String getTalla() {
        return talla;
    }

    public String getColor() {
        return color;
    }

    public String getMarca() {
        return marca;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isNovedades() {
        return novedades;
    }

    public boolean isOfertas() {
        return ofertas;
    }

    public Double getPrecioNuevo() {
        return precioNuevo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setArticulosDisponibles(int articulosDisponibles) {
        this.articulosDisponibles = articulosDisponibles;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setNovedades(boolean novedades) {
        this.novedades = novedades;
    }

    public void setOfertas(boolean ofertas) {
        this.ofertas = ofertas;
    }

    public void setPrecioNuevo(Double precioNuevo) {
        this.precioNuevo = precioNuevo;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
