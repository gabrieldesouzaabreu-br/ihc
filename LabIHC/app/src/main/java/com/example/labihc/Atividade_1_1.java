package com.example.labihc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Atividade_1_1 extends AppCompatActivity {
    EditText num1;
    EditText num2;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade_1_1);
        num1=findViewById(R.id.number1);
        num2=findViewById(R.id.number2);
        result=findViewById(R.id.result);
    }

    public void sum(View v){
        Float val1, val2;
        try{
            val1 = Float.parseFloat(num1.getText().toString());
            val2 = Float.parseFloat(num2.getText().toString());
            if(val1!=null && val2!=null){
                String soma =String.valueOf(val1+val2);
                result.setText("Resultado: "+soma);
            }else result.setText("Não foi possível calcular a soma");
        }catch (Exception e){
            result.setText("Não foi possível calcular a soma");
        }
    }

}
