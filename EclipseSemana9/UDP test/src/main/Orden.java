package main;

import processing.core.PApplet;
import processing.core.PImage;

public class Orden {

	private String imagenPedido;
	private String numeroPedido;
	private String horaPedido;

	public Orden(String imagenPedido, String numeroPedido, String horaPedido) {
		this.imagenPedido = imagenPedido;
		this.numeroPedido = numeroPedido;
		this.horaPedido = horaPedido;
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

	public void paint(int posX,int posY, PApplet app) {
		PImage img = app.loadImage("./lib/"+imagenPedido+"");
		app.fill(0);
		app.text ("Pedido #"+numeroPedido, posX+120, posY+40);
		app.text ("hora"+horaPedido,posX+120,posY+60);
		
		if(imagenPedido.equals("yogur.jpg") || imagenPedido.equals("ensalada.png")) {
			app.image(img, posX+25, posY,50,80);
			
		}else {
			app.image(img, posX, posY,100,80);
		}
	}
}
