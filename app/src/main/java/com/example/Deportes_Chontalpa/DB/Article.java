package com.example.Deportes_Chontalpa.DB;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Article implements Parcelable {
    private String nombre,descripcion, talla, color, marca, categoria, imageUrl;
    private double precio, precioNuevo;
    private boolean novedades, ofertas;
    private int articulosDisponibles;

    public Article() {
    }

    public Article(String nombre, String descripcion, double precio, int articulosDisponibles, String talla, String color,
                   String marca, boolean novedades, boolean ofertas, Double precioNuevo, String categoria, String imageUrl) {
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
        this.imageUrl = imageUrl;
    }

    public void guardarArticulo() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Articulos");
        Map<String, Object> articulosValues = toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(nombre.replace(" ","_"), articulosValues);
        databaseReference.updateChildren(childUpdates);
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nombre", nombre);
        result.put("descripcion", descripcion);
        result.put("precio", precio);
        result.put("articulosDisponibles", articulosDisponibles);
        result.put("talla", talla);
        result.put("color", color);
        result.put("marca", marca);
        result.put("novedades", novedades);
        result.put("ofertas", ofertas);
        result.put("precioNuevo", precioNuevo);
        result.put("categoria", categoria);
        result.put("imageUrl", imageUrl);
        return result;
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
        dest.writeString(nombre);
        dest.writeString(descripcion);
        dest.writeDouble(precio);
        dest.writeInt(articulosDisponibles);
        dest.writeString(talla);
        dest.writeString(color);
        dest.writeString(marca);
        dest.writeByte((byte) (novedades ? 1 : 0));
        dest.writeByte((byte) (ofertas ? 1 : 0));
        dest.writeDouble(precioNuevo);
        dest.writeString(categoria);
        dest.writeString(imageUrl);
    }

    protected Article(Parcel in) {
        nombre = in.readString();
        descripcion = in.readString();
        precio = in.readDouble();
        articulosDisponibles = in.readInt();
        talla = in.readString();
        color = in.readString();
        marca = in.readString();
        novedades = in.readByte() != 0;
        ofertas = in.readByte() != 0;
        precioNuevo = in.readDouble();
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