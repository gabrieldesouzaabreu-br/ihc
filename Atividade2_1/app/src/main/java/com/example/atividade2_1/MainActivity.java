package com.example.atividade2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor light;
    EditText l;
    boolean podeTrocar;

    @Override
    protected void onResume() {
        super.onResume();
        podeTrocar=true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l = findViewById(R.id.light);
        podeTrocar=true;

        sensorManager = (SensorManager)
                getSystemService(Context.SENSOR_SERVICE);
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(light != null)
        {
            sensorManager.registerListener(MainActivity.this, light,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        if(sensor.getType() == Sensor.TYPE_LIGHT)
        {
            Float lAux=0f;
            Float lAux2=0f;
            Intent i = new Intent(this, Tela2.class);
            if(l.getText().length()>=1) {
                lAux = Float.parseFloat(l.getText().toString());
                lAux2 = event.values[0];
            }
            if(podeTrocar && (Math.abs(lAux-lAux2)>30)) {
                podeTrocar=false;
                l.setText("");
                startActivity(i);
            }
            l.setText(String.valueOf(event.values[0]));
        }
    }

}
