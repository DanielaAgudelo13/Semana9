package main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPConnection extends Thread {

	private DatagramSocket socket;
	private OnMessageListener observer;

	public void setObserver(OnMessageListener observer) {
		this.observer = observer;
	}

	@Override
	public void run() {
		try {
			// 1 Escuchar
			socket = new DatagramSocket(6000);

			// esperar mensajes: datagramas

			while (true) {
				// 2 Parametros Constructor
				byte[] buffer = new byte[200];
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
				System.out.println("Esperando datagrama");
				socket.receive(packet);
				System.out.println(packet);
				String mensaje = new String(packet.getData()).trim();
				observer.recibirOrden(mensaje);// esto manda el mensaje al main

				System.out.println("orden recibida" + mensaje); //

			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String mensaje) {
		// 4 parametros de envio

		new Thread(() -> {
			try {
				// parametros datagrama de envio
				InetAddress ip = InetAddress.getByName("192.168.1.2");
				DatagramPacket packet = new DatagramPacket(mensaje.getBytes(), mensaje.getBytes().length, ip, 5000); // dec
																								// puerto
				socket.send(packet);
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();

	}
}
