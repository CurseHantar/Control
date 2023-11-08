package com.cursehantar.completecontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {

    CheckBox seleccionarChBox;
    EditText txtNombre, txtCorreo, txtContra, txtTelefono;
    Button miBoton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        seleccionarChBox = findViewById(R.id.chbox);
        txtNombre = findViewById(R.id.txtNombre);
        txtContra = findViewById(R.id.txtContra);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtTelefono = findViewById(R.id.txtTelefono);

        txtTelefono.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged (CharSequence s,int start, int count, int after){
            }

            @Override
            public void onTextChanged (CharSequence s,int start, int before, int count){
                // Verifica si el primer carácter no es un "+"
                if (s.length() > 0 && s.charAt(0) != '+') {
                    // Si no es un "+", añade uno al principio del texto
                    txtTelefono.setText("+" + s);
                    txtTelefono.setSelection(txtTelefono.getText().length()); // Mueve el cursor al final
                }
            }

            @Override
            public void afterTextChanged (Editable s){

            }
        });

    }

    public void comprobarChBox(View view) {

        if (seleccionarChBox.isChecked()) {
            String mensaje = "Se ha aceptado los terminos";
            Toast toast = Toast.makeText(this, mensaje, Toast.LENGTH_SHORT);
            toast.show();
            boolean checked = ((CheckBox) view).isChecked();
            miBoton = findViewById(R.id.btnVolver);
            miBoton.setEnabled(checked);

        } else {
            String mensaje = "Debes de aceptar los terminos";
            Toast toast = Toast.makeText(this, mensaje, Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void irLogin(View vista) {
        Intent miIntento2 = new Intent(this, LoginActivity.class);
        startActivity(miIntento2);
        finish();
    }


}