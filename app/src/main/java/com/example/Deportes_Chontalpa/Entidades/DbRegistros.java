package com.example.Deportes_Chontalpa.Entidades;

public class DbRegistros {

    private String Id;
    private String Nombre;
    private String Descripcion;
    private String Talla;
    private String Color;
    private String Marca;
    private String Disponibles;
    private String Precio;
    private String Categoria;
    private int imageResourse;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getTalla() {
        return Talla;
    }

    public void setTalla(String talla) {
        Talla = talla;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public void setPrecio(String precio){
        Precio= precio;
    }

    public String getPrecio(){return Precio;}

    public void setDisponibles(String disponibles){
        Disponibles= disponibles;
    }

    public String getDisponibles(){return Disponibles;}

    public void setCategoria(String categoria){
        Categoria= categoria;
    }

    public String getCategoria(){return Categoria;}

    public void setImageResourse(int imageResourse) {
        this.imageResourse = imageResourse;
    }

    public int getImageResourse() {
        return imageResourse;
    }
}
