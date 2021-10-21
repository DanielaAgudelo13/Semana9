package com.example.udpsemana9;

import android.location.OnNmeaMessageListener;
import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPConnection extends Thread{

    private DatagramSocket socket;
    private OnMessageListener observer; //main activity se referencia aqui

    public void setObserver(OnMessageListener observer) { //metodo de subscribsion
        this.observer = observer;
    }

    @Override
    public void run() {
        try {
            //1 Escuchar
            socket = new DatagramSocket(5000);

            //esperar mensajes: datagramas

            while (true) {
                //2 Parametros Constructor
                byte[] buffer = new byte [100];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                System.out.println("Esperando datagrama");
                socket.receive(packet); //lo vuelvo un string



                String mensaje = new String(packet.getData()).trim();//recibe el mensaje
                observer.recibirConfirmacion(mensaje); //main.recibir confirmacion gracias a como esta refenenciado
                System.out.println("Esperando datagrama" + mensaje);

            }
        } catch (SocketException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String mensaje) {
        //4 parametros de envio

        new Thread (
                ()->{
                    try {
                        //parametros datagrama de envio
                        InetAddress ip = InetAddress.getByName("192.168.1.4");
                        DatagramPacket packet = new DatagramPacket(mensaje.getBytes(), mensaje.getBytes().length, ip, 6000); //dec con el que me comunico el 6000 es el puerto
                        socket.send(packet);
                        Log.e("hello","se envio algo");
                    } catch (UnknownHostException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
        ).start();

    }
}
