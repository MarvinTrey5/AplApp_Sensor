package com.example.appsensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Se definen solo 2 botones y sus variables a necesitar.
    Button Primera_Ventana, Segunda_Ventana;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Se referencian.
        Primera_Ventana = findViewById(R.id.Ir_Proximida);
        Segunda_Ventana = findViewById(R.id.Ir_Luminosidad);
        // Se llama al primer botón.
        Primera_Ventana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Que nos llevará a la Activity_Proximidad.
                Intent Go_Prox = new Intent(MainActivity.this, Activity_Proximidad.class);
                startActivity(Go_Prox);
            }
        });
        // Se llama al segundo botón.
        Segunda_Ventana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Que nos llevará a la Activity_Luminosidad.
                Intent Go_Lum = new Intent(MainActivity.this, Activity_Luminosidad.class);
                startActivity(Go_Lum);
            }
        });
    }
}