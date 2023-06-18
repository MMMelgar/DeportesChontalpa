package com.example.Deportes_Chontalpa.DB;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Article implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeString(nombre);
        dest.writeString(descripcion);
        dest.writeString(Double.toString(precio));
        dest.writeString(Integer.toString(articulosDisponibles));
        dest.writeString(talla);
        dest.writeString(color);
        dest.writeString(marca);
        dest.writeString(Boolean.toString(novedades));
        dest.writeString(Boolean.toString(ofertas));
        if (precioNuevo!=null){
            dest.writeString(Double.toString(precioNuevo));
        }
        dest.writeString(categoria);
        dest.writeString(imageUrl);
    }

    protected Article(Parcel in) {
        key = in.readString();
        nombre = in.readString();
        descripcion = in.readString();
        precio= Double.parseDouble(in.readString());
        articulosDisponibles=Integer.parseInt(in.readString());
        talla = in.readString();
        color = in.readString();
        marca = in.readString();
        novedades = Boolean.parseBoolean(in.readString());
        ofertas = Boolean.parseBoolean(in.readString());
        if (precioNuevo!=null){
            precioNuevo= Double.parseDouble(in.readString());
        }
        categoria = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

}
