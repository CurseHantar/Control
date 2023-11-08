package com.cursehantar.completecontrol.bluetooth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import com.cursehantar.completecontrol.R;

public class BluetoothClient extends AppCompatActivity {

    //private static final String APP_NAME = "MiAppBluetooth";
    //private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); // UUID genérico para SPP (Serial Port Profile)

    private BluetoothDevice serverDevice;
    private BluetoothSocket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_client);

        // Obtén la referencia del botón
        Button btnSendData = findViewById(R.id.btn_send_data);

        // Obtiene el dispositivo Bluetooth al que quieres conectarte
        //serverDevice = ..;// Debe ser inicializado con el dispositivo Bluetooth adecuado

        // Crea una conexión BluetoothSocket
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            socket = serverDevice.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")); // UUID del servidor
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Define el comportamiento del botón al hacer clic
        btnSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (socket != null && socket.isConnected()) {
                    try {
                        // Obtiene el OutputStream para enviar datos
                        OutputStream outputStream = socket.getOutputStream();

                        // Escribe los datos en el OutputStream
                        String mensaje = "Hola desde el cliente";
                        outputStream.write(mensaje.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(BluetoothClient.this, "La conexión no está establecida", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            // Asegúrate de cerrar el socket cuando ya no lo necesitas
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}