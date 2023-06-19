package com.example.Deportes_Chontalpa.DB;

import java.util.List;

public class Pedido {
    private String numeroPedido;
    private User usuario;
    private List<Article> articulos;
    private Double total;

    public Pedido() {

    }

    public Pedido(String numeroPedido, User usuario, List<Article> articulos, Double total) {
        this.usuario = usuario;
        this.numeroPedido = numeroPedido;
        this.articulos = articulos;
        this.total = total;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public List<Article> getArticulos() {
        return articulos;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public void setArticulos(List<Article> articulos) {
        this.articulos = articulos;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public Double getTotal() {
        return total;
    }
}

