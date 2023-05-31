package com.example.Deportes_Chontalpa.Entidades;

import com.example.Deportes_Chontalpa.R;

public class Producto {
    private String nombre;
    private String descripcion;
    private int imagenID;
    private int precio ;

    public final static Producto calzado[]={
            new Producto("  CALZADO ","TENNIS", R.drawable.lager,800),

    };

    public Producto() {
    }

    public Producto(String nombre, String descripcion, int imagenID, int precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenID = imagenID;
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImagenID() {
        return imagenID;
    }

    public void setImagenID(int imagenID) {
        this.imagenID = imagenID;
    }

    public String getNombre() {

        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String toString() {
        return nombre;
    }

    public static Producto[] getCalzado() {
        return cervezas;
    }


}
