package com.example.atividade1_3;

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
    private Sensor accelerometer;
    EditText x;
    EditText y;
    EditText z;
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
        x = findViewById(R.id.x);
        y = findViewById(R.id.y);
        z = findViewById(R.id.z);
        podeTrocar=true;

        sensorManager = (SensorManager)
                getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(accelerometer != null)
        {
            sensorManager.registerListener(MainActivity.this, accelerometer,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        if(sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            Float xAux=0f;
            Float xAux2=0f;
            Float yAux=0f;
            Float yAux2=0f;
            Float zAux=0f;
            Float zAux2=0f;
            Intent i = new Intent(this, Tela2.class);
            if(x.getText().length()>=1){
                xAux = Float.parseFloat(x.getText().toString());
                xAux2 = event.values[0];
            }
            if(y.getText().length()>=1){
                yAux = Float.parseFloat(y.getText().toString());
                yAux2 = event.values[1];
            }
            if(z.getText().length()>=1){
                zAux = Float.parseFloat(z.getText().toString());
                zAux2 = event.values[2];
            }
            if(podeTrocar && (Math.abs(xAux-xAux2)>5 || Math.abs(yAux-yAux2)>5 || Math.abs(zAux-zAux2)>5)) {
                podeTrocar=false;
                x.setText("");
                y.setText("");
                z.setText("");
                startActivity(i);
            }
            x.setText(String.valueOf(event.values[0]));
            y.setText(String.valueOf(event.values[1]));
            z.setText(String.valueOf(event.values[2]));
        }
    }


}
