package com.cursehantar.completecontrol.bluetooth;

import android.bluetooth.BluetoothSocket;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

public class AcceptThread extends Thread{

    private TextView textView;

    public void run() {
        BluetoothSocket socket = null;

       // while (true) {
       //     try {
       //         socket = serverSocket.accept();
       //     } catch (IOException e) {
       //         Log.e("BluetoothServer", "Error al aceptar la conexión", e);
       //         break;
       //     }
//
       //     if (socket != null) {
       //         // La conexión se ha establecido
       //         // Puedes manejar la comunicación aquí usando InputStream y OutputStream
       //         try {
       //             InputStream inputStream = socket.getInputStream();
//
       //             // Lee los datos del InputStream
       //             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
       //             String mensaje = bufferedReader.readLine();
//
       //             // Muestra el mensaje en un TextView o donde desees
       //             runOnUiThread(new Runnable() {
       //                 @Override
       //                 public void run() {
       //                     // Por ejemplo, si tienes un TextView llamado txtMensaje
       //                     textView.setText("Mensaje recibido: " + mensaje);
       //                 }
       //             });
//
       //         } catch (IOException e) {
       //             Log.e("BluetoothServer", "Error al obtener el InputStream", e);
       //         }
       //         break;
       //     }
       // }
    }//

    private void runOnUiThread(Runnable runnable) {

    }

    // ...
}
