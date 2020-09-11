package com.example.labihc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Atividade_1_2_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade_1_2_1);
    }

    public void send(View v){
        EditText msg= findViewById(R.id.mensagem);
        String texto = msg.getText().toString();
        Intent i = new Intent(this, Atividade_1_2_2.class);
        i.putExtra("texto", texto);
        startActivity(i);
    }
}
