package com.cursehantar.completecontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {

    CheckBox seleccionarChBox;
    Button miBoton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        seleccionarChBox = findViewById(R.id.chbox);
    }

    public void comprobarChBox(View view){

        if (seleccionarChBox.isChecked()) {
            String mensaje = "Se ha aceptado los terminos";
            Toast toast = Toast.makeText(this, mensaje, Toast.LENGTH_LONG);
            toast.show();
            boolean checked = ((CheckBox) view).isChecked();
            miBoton = findViewById(R.id.btnVolver);
            miBoton.setEnabled(checked);

        }else{
            String mensaje = "Debes de aceptar los terminos";
            Toast toast = Toast.makeText(this, mensaje, Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void irLogin(View vista){
        Intent miIntento2 = new Intent(this, LoginActivity.class);
        startActivity(miIntento2);
        finish();
    }
}