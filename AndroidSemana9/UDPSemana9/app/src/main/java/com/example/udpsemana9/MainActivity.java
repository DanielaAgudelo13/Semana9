package com.example.udpsemana9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements OnMessageListener{

    UDPConnection udp;
    private Orden orden;
    private ImageView sandwich;
    private ImageView yogur;
    private ImageView jugo;
    private ImageView ensalada;
    private int orderNumber, hour, min;
    private String orderTimeStamp;
    private Calendar cal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sandwich = findViewById(R.id.sandwich);
        yogur = findViewById(R.id.yogur);
        jugo = findViewById(R.id.jugo);
        ensalada = findViewById(R.id.ensalada);
        orden = new Orden("","","");

        udp = new UDPConnection();
        udp.setObserver(this); //this en este caso es main activity
        udp.start();
        Gson gson = new Gson();

        cal = Calendar.getInstance();
        hour = cal.get(Calendar.HOUR);
        min = cal.get(Calendar.MINUTE);
        orderNumber = 0;
        orderTimeStamp = String.valueOf(hour)+":"+String.valueOf(min);


        sandwich.setOnClickListener(
                (v)->{
                    Orden orden = new Orden("sandwich.jpg", String.valueOf(orderNumber), orderTimeStamp);
                    String json = gson.toJson(orden);
                    //  Log.e("hello", ""+json);

                    udp.sendMessage(json);
                    orderNumber+=1;
                }
        );
        yogur.setOnClickListener(
                (v)->{
                    Orden orden = new Orden("yogur.jpg", String.valueOf(orderNumber), orderTimeStamp);
                    String json = gson.toJson(orden);
                    // Log.e("hello", ""+json);

                    udp.sendMessage(json);
                    orderNumber+=1;
                }
        );
        jugo.setOnClickListener(
                (v)->{
                    Orden orden = new Orden("jugos.jpg", String.valueOf(orderNumber), orderTimeStamp);
                    String json = gson.toJson(orden);
                    //Log.e("hello", ""+json);

                    udp.sendMessage(json);
                    orderNumber+=1;
                }
        );
        ensalada.setOnClickListener(
                (v)->{
                    Orden orden = new Orden("ensalada.png", String.valueOf(orderNumber), orderTimeStamp);
                    String json = gson.toJson(orden);
                    //Log.e("hello", ""+json);

                    udp.sendMessage(json);
                    orderNumber+=1;
                }
        );
    }

    @Override
    public void recibirConfirmacion(String mssg) { //se conecta con el UDP, metodo se hace desde un hilo y si quiero imagenes necesito
        runOnUiThread(
                ()->{ //aqui van las confirmaciones de ordenes
                    Toast.makeText(getApplicationContext(), mssg, Toast.LENGTH_LONG).show();
                }
        );
    }
}