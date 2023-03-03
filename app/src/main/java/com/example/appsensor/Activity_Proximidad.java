package com.example.appsensor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_Proximidad extends AppCompatActivity {

    // Definimos los objetos con sus variables.
    Sensor Micell;
    SensorManager AdministradorDeCell;
    SensorEventListener Evento_Decell;
    TextView Ver_DatoP, DatoSensor;
    Button VerNum;
    // Para cambiar el color del ConstraintLayout
    // lo referenciamos con una variable
    ConstraintLayout Nuevo_Tema;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximidad);
        // Referenciamos cada variable con su objeto y el ID de atributo.
        Nuevo_Tema = findViewById(R.id.Fondo_Cambiar);
        Ver_DatoP = findViewById(R.id.Cambiar_Color);
        DatoSensor = findViewById(R.id.Dato_Sensor);
        VerNum = findViewById(R.id.Mostrar_Dato);
        // Inicializando el sensor
        AdministradorDeCell = (SensorManager) getSystemService(SENSOR_SERVICE);
        // Tipo de sensor a usar
        Micell = AdministradorDeCell.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        // Inicializa el SensorEventListener.
        Evento_Decell = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                // Mostramos el resultado de proximidad en el botón.
                VerNum.setText("Valor del sensor: " + sensorEvent.values[0]);
                // Establecemos condiciones.
                // Si se cumple.
                if (sensorEvent.values [0] < Micell.getMaximumRange()){
                    // Cambiará de color las letras del botón a azul.
                    VerNum.setTextColor(Color.BLUE);
                    // Camabiará el fondo del botón amarillo.
                    VerNum.setBackgroundColor(Color.parseColor("#A8FE11"));
                    // Cambiará el color del título a azul.
                    Ver_DatoP.setTextColor(Color.BLUE);
                    // Mostrará el siguiente mensaje en el subtitulo.
                    DatoSensor.setText("Se ha acercado el sensor");
                    // Cambiará de color el fondo del subtitulo.
                    DatoSensor.setBackgroundColor(Color.parseColor("#00FF00"));
                    // Cambiará de color las letras del subtitulo.
                    DatoSensor.setTextColor(Color.parseColor("#0000FF"));
                    // Cambiara de color el ConstraintLayout.
                    Nuevo_Tema.setBackgroundColor(Color.parseColor("#ff6600"));
                }
                // Si no se cumle.
                else {
                    // Cambiará de color las letras del botón.
                    VerNum.setTextColor(Color.parseColor("#123605"));
                    // Camabiará el fondo del botón
                    VerNum.setBackgroundColor(Color.parseColor("#D5E6CF"));
                    // Cambiará el color del título a blanco.
                    Ver_DatoP.setTextColor(Color.WHITE);
                    // Mostrará el siguiente mensaje en el subtitulo.
                    DatoSensor.setText("Se ha alejado el sensor");
                    // Cambiará de color el fondo del subtitulo.
                    DatoSensor.setBackgroundColor(Color.parseColor("#414A6D"));
                    // Cambiará de color las letras del subtitulo.
                    DatoSensor.setTextColor(Color.WHITE);
                    // Cambiara de color el ConstraintLayout.
                    Nuevo_Tema.setBackgroundColor(Color.parseColor("#872323"));

                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };
        // Método de inicialización del sensor
        Proceso_MiSensor();
        }
        // Se declará
        public void Proceso_MiSensor(){
        AdministradorDeCell.registerListener(Evento_Decell, Micell,(2000*1000));
        }
        // Método de detener el sensor.
        public void Detencion_MiSensor(){
        AdministradorDeCell.unregisterListener(Evento_Decell);
        }
    // Utilizamos el onResume
    @Override
    protected void onResume() {
        // Dentro se llama el método del inicialización del sensor.
        Proceso_MiSensor();
        super.onResume();
    }
    @Override
    protected void onPause() {
        // Dentro se llama el método de detención del sensor.
        Detencion_MiSensor();
        super.onPause();
    }
}