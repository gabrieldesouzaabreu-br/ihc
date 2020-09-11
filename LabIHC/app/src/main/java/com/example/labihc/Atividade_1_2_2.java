package com.example.labihc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Atividade_1_2_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade_1_2_2);
        Intent intent = getIntent();
        String texto = intent.getExtras().getString("texto","");
        if(texto!=null){
            TextView tv = findViewById(R.id.text);
            tv.setText(texto);
        }
    }
}
