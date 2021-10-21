package main;

import java.util.ArrayList;

import com.google.gson.Gson;

import processing.core.PApplet;

public class Main extends PApplet implements OnMessageListener {

	UDPConnection udp;
	
	
	private ArrayList <Orden> orden;
	
	

	public static void main(String[] args) {
		PApplet.main("main.Main");
	}
	
	public void settings() {
		size (800,600);
	}
	
	public void setup() {
		udp = new UDPConnection();
        udp.setObserver(this);
        udp.start();
		orden = new ArrayList<Orden>();
	}
	
	public void draw() {
		background(255);
	
		for(int i=0 ; i<orden.size() ; i++) {
			Orden datosOrden = orden.get(i);
			datosOrden.paint(60,120*i+60,this); //distancia entre cada orden
			
		}
	}

	@Override
	public void recibirOrden(String orden) { //porque recibe una linea json seria String orden, agrega un elemento arraylist
		
		Gson gson = new Gson();
		Orden datosOrden = gson.fromJson(orden, Orden.class);

		/*orden es la variable global*/this.orden.add (datosOrden); //orden tipo string
	}
	
	public void mousePressed() {
		for (int i = 0; i < orden.size(); i++) {
			
			if (mouseX>60 && mouseX<160 && mouseY>60+(120*i) && mouseY<60+(120*i)+50) {
				
				String type = orden.get(i).getImagenPedido().substring(0, orden.get(i).getImagenPedido().length() - 4);
				udp.sendMessage("Su pedido de "+ type + " ya fue despachado");

				orden.remove(i);
			}
			
		}
	}
	
}
