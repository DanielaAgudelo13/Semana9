package com.example.udpsemana9;

public class Orden {

    private String imagenPedido;
    private String numeroPedido;
    private String horaPedido;

    public Orden (String imagenPedido, String numeroPedido, String horaPedido) {
        this.imagenPedido = imagenPedido;
        this.numeroPedido = numeroPedido;
        this.horaPedido = horaPedido;
    }

    public Orden() {

    }

    public String getImagenPedido() {
        return imagenPedido;
    }

    public void setImagenPedido(String imagenPedido) {
        this.imagenPedido = imagenPedido;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public String getHoraPedido() {
        return horaPedido;
    }

    public void setHoraPedido(String horaPedido) {
        this.horaPedido = horaPedido;
    }


}
