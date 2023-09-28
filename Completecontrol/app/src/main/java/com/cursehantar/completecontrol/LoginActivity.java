package com.cursehantar.completecontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private CheckBox seleccionarChBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        seleccionarChBox = (CheckBox) findViewById(R.id.chbox);

    }

    public void irAct2(View vista){
        Intent miIntento = new Intent(this, RegistroActivity.class);
        startActivity(miIntento);
    }


    public void btnCheck(View v){
        if(seleccionarChBox.isChecked() == true){
            String mensaje = "Seleccionado";
            Toast toast = Toast.makeText(this, mensaje, Toast.LENGTH_LONG);
            //toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.START, 70,0);
            toast.show();
        }

        else{
            String mensaje = "No seleccionado";
            Toast toast = Toast.makeText(this, mensaje, Toast.LENGTH_LONG);
            toast.show();
        }
    }
}

