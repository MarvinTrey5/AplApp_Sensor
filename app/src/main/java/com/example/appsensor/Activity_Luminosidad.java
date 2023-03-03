package com.example.appsensor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_Luminosidad extends AppCompatActivity {
    // Definimos los objetos con sus variables.
    Sensor MiDato;
    SensorManager AdministradorDato;
    SensorEventListener Evento_Dato;
    TextView Ver_Lum;
    float Valor;
    Button VerLum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luminosidad);
        // Se referencian
        Ver_Lum = (TextView) findViewById(R.id.Ver_Opcion);
        VerLum = findViewById(R.id.Ver_Resultado);
        // Funcion al botón.
        VerLum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Llamaremos un método para enviar el resultado
                // por whatsapp.
                EnviarResultado();
            }
        });
        // Se referencian los sensores.
        AdministradorDato = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        MiDato = AdministradorDato.getDefaultSensor(Sensor.TYPE_LIGHT);
        Evento_Dato = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                // Se declarán los cierta condición.
                Valor = sensorEvent.values[0];
                if(sensorEvent.values[0] < MiDato.getMaximumRange()){
                    Ver_Lum.setText("Valor " + Valor + " LUX ");
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        Proceso_Luminosidad();
    }
    // Se crea el métdo de funcionalidad para enviar el mensaje por whatsapp.
    public void EnviarResultado(){
        Intent enviarvalor = new Intent();
        enviarvalor.setAction(Intent.ACTION_SEND);
        enviarvalor.putExtra(Intent.EXTRA_TEXT, "El valor del sensor es: " + Valor);
        enviarvalor.setType("text/plain");
        enviarvalor.setPackage("com.whatsapp");
        startActivity(enviarvalor);
    }
    // Se crea el método
    public void  Proceso_Luminosidad(){
        AdministradorDato.registerListener(Evento_Dato,MiDato,SensorManager.SENSOR_DELAY_FASTEST);
    }
    // Se crea el método de detención
    public void DetenetLum(){
        AdministradorDato.unregisterListener(Evento_Dato);
    }
    // Utilizamos el onResume
    @Override
    protected void onResume() {
        // Llamamos el método del proceso del sensor de luminación.
        super.onResume();
        Proceso_Luminosidad();
    }
    @Override
    protected void onPause() {
        // Llamamos el método de detención del sensor de luminación.
        super.onPause();
        DetenetLum();
    }
}