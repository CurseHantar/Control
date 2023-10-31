package com.cursehantar.completecontrol;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class LoginActivity extends AppCompatActivity {

    EditText usuarioTxt;
    EditText contraTxt;
    Button btnSelecionar;
    Button btnSaltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuarioTxt = (EditText) findViewById(R.id.usuarioTxt);
        contraTxt = (EditText) findViewById(R.id.contraTxt);
        btnSelecionar = (Button) findViewById(R.id.btnSeleccionar);

        btnSelecionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = usuarioTxt.getText().toString();
                String contra = contraTxt.getText().toString();;
                String mensaje = "";
                boolean enviar = true;

                if(contra.length()==0){
                    enviar = false;
                    mensaje="Escriba la contraseña, por favor.\n";
                }
                if(nombre.length()==0){
                    enviar = false;
                    mensaje+="Escriba el usuario, por favor.";
                }
                if(enviar){
                    String titulo = "Aviso de complete control";
                    mensaje = "Usuario acaba de iniciar sesión:\n";
                    mensaje+= "@" + nombre;
                    int notificaID = 1;
                    Notificar(titulo, mensaje, notificaID);
                    irMain(v);

                }
                if(!enviar) Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void irAct2(View vista){
        Intent miIntento = new Intent(this, RegistroActivity.class);
        startActivity(miIntento);
    }

    public void irMain(View view){
        Intent miIntento = new Intent(this, MainActivity.class);
        miIntento.putExtra("nombre", usuarioTxt.getText().toString());
        startActivity(miIntento);

    }

    public void Notificar(String titulo, String mensaje, int notID){
        NotificationCompat.Builder creador;
        String canalID = "MiCanal01";
        Context contexto = getApplicationContext();
        NotificationManager notificador = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        creador = new NotificationCompat.Builder(contexto, canalID);
        // Si nuestro dispositivo tiene Android 8 (API 26, Oreo) o superior

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String canalNombre = "Mensajes";
            String canalDescribe = "Canal de mensajes";
            int importancia = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel miCanal = new NotificationChannel(canalID, canalNombre, importancia);
            miCanal.setDescription(canalDescribe);
            miCanal.enableLights(true);
            miCanal.setLightColor(Color.BLUE); // Esto no lo soportan todos los dispositivos
            miCanal.enableVibration(true);
            notificador.createNotificationChannel(miCanal);
            creador = new NotificationCompat.Builder(contexto, canalID);
        }
        Bitmap iconoNotifica = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.pngegg);
        int iconoSmall = R.drawable.pngegg;
        creador.setSmallIcon(iconoSmall);
        creador.setLargeIcon(iconoNotifica);
        creador.setContentTitle(titulo);
        creador.setContentText(mensaje);
        creador.setStyle(new NotificationCompat.BigTextStyle().bigText(mensaje));
        creador.setChannelId(canalID);
        notificador.notify(notID, creador.build());
    }


}

