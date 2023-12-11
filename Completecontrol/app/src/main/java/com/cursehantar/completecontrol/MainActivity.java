package com.cursehantar.completecontrol;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.cursehantar.completecontrol.cardViewDispositivo.DataModel;
import com.cursehantar.completecontrol.menuItemOptions.HelpActivity;
import com.cursehantar.completecontrol.menuItemOptions.SettingsActivity;
import com.cursehantar.completecontrol.ui.gallery.GalleryFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.cursehantar.completecontrol.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.storage.StorageReference;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.osmdroid.config.Configuration;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    private TextView txtNombrePerfil;
    private DatabaseReference databaseReference;
    String nombre_Dispositivo;
    private static final String SERVER_URI = "mqtt://conected.cloud.shiftr.io:1883";  // Cambiar al servidor shiftr.io
    private static final String USERNAME = "conected";  //
    private static final String PASSWORD = "vrh2bfxadPIGkha7";  //
    private static final String TOPIC = "Conectado";  // Cambiar a tu tema
    private MqttAndroidClient client;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<DataModel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //obtener_nombre_Dispositivo();
        //conexionBroker();


        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                mostrarDialogoConfirmacion();
                Snackbar.make(view, "Añadiendo nuevo dispositivo", Snackbar.LENGTH_LONG);
            }
        });

        TextView txtNombrePerfil = findViewById(R.id.txtNombrePerfil);
        //mostrarNombrePerfil();

        //NavigationView

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();

        //BottomNavigationView

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view_menu);


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //Esto IMPORTANTE permite la navegacion entre NavigationView y BottomNavigationView
        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);


        //Maps openStretMap
        Configuration.getInstance().load(getApplicationContext(),
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));

    }

    public void conexionBroker() {
        //para conextar al broker   //generamos un clienteMQTT
        String clientId = nombre_Dispositivo;//MqttClient.generateClientId();//noombre del celular
        client = new MqttAndroidClient(this.getApplicationContext(), SERVER_URI, clientId);
        //para agregar los parametros
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(USERNAME);
        options.setPassword(PASSWORD.toCharArray());
        try {

            IMqttToken token = client.connect(options);//intenta la conexion
            token.setActionCallback(new IMqttActionListener() {

                @Override//metodo de conectado con exito
                public void onSuccess(IMqttToken asyncActionToken) {
                    // mensaje de conectado
                    Toast.makeText(getBaseContext(), "Conectado ", Toast.LENGTH_SHORT).show();
                }

                @Override//si falla la conexion
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // mensaje de que no se conecto
                    Toast.makeText(getBaseContext(), "NO Conectado ", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void desSubcribcion(View v){
        String tema ="LED";
        try {   client.unsubscribe(tema);
            Toast.makeText(getBaseContext(),"DESsusccipto",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getBaseContext(),"sigues subcrito a LED",Toast.LENGTH_SHORT).show();
        }    }


    public  void  Desconectar (View v) {
        try {
            client.disconnect();

        }catch (Exception e){e.printStackTrace();}
    }


    private void obtener_nombre_Dispositivo() {

        String fabricante = Build.MANUFACTURER;
        String modelo = Build.MODEL;
        nombre_Dispositivo=fabricante+" "+modelo;
        txtNombrePerfil =(TextView) findViewById(R.id.txtNombrePerfil);//para enlazar el tv_G con el codigo
        txtNombrePerfil.setText(nombre_Dispositivo);//para mostrar en el tv_g e modelo del celular
    }


    private void connectToMqtt() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        //options.setPassword("vrh2bfxadPIGkha7".toCharArray());

        try {
            client.connect(options, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // Conexión exitosa
                    subscribeToTopic();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Manejar la falla en la conexión
                    exception.printStackTrace();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void subscribeToTopic() {
        try {
            client.subscribe(TOPIC, 0);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void mostrarNombrePerfil(){
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String correo = bundle.getString("nombre");

            if (correo != null) {
                txtNombrePerfil.append("Bienvenido " + correo + " :D");
            }
        } else {
            txtNombrePerfil.append("Click me!!");
            txtNombrePerfil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, RickRollTroll.class));
                }
            });
        }
    }


    private void mostrarDialogoConfirmacion() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmación");
        builder.setMessage("¿Estás seguro de que deseas añadir un nuevo dispositivo?");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //addDataToFirebase("","","",11, R.drawable.celular);
                GalleryFragment galleryFragment = (GalleryFragment) getSupportFragmentManager().findFragmentByTag("galleryFragment");

                if (galleryFragment != null) {
                    // Llama al método en el fragmento para agregar un nuevo dato
                    galleryFragment.agregarNuevoDato("Nuevo Nombre", "Nuevo Número", "Nueva Marca", R.drawable.celular);
                } else {
                    // Manejar el caso cuando el fragmento no está disponible o no se ha agregado aún
                    Log.e("MainActivity", "GalleryFragment no encontrado");
                }
                //agregarNuevoDato("Nuevo Nombre", "Nuevo Número", "Nueva Marca", R.drawable.celular);

            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //Ta nice
        getMenuInflater().inflate(R.menu.main, menu);
        //onMenuItemSelected(R.id.action_settings);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        } else if (id == R.id.action_help) {
            Intent helpIntent = new Intent(this, HelpActivity.class);
            startActivity(helpIntent);
            return true;
        } else if (id == R.id.action_leaving) {
            Intent leavingIntent = new Intent(this, LoginActivity.class);
            startActivity(leavingIntent);
            finish();
            return true;
        }

            return super.onOptionsItemSelected(item);
    }

    private void addDataToFirebase(String name, String numero, String marca, int drawable) {

        DataModel newItem = new DataModel(name, numero, marca, drawable);
        databaseReference.push().setValue(newItem);
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //si esta conectado no agas nada//else pero si no conectate
        if(client.isConnected()){
        }else {Toast.makeText(getBaseContext(),"connection_perdida: ",Toast.LENGTH_SHORT).show();
            conexionBroker();}
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        disconnectFromMqtt();
    }

    private void disconnectFromMqtt() {
        try {
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


}