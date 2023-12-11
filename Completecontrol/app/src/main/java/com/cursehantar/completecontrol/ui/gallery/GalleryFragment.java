package com.cursehantar.completecontrol.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cursehantar.completecontrol.cardAdapter.Dispositivo;
import com.cursehantar.completecontrol.cardAdapter.DispositivoAdapter;
import com.cursehantar.completecontrol.cardAdapter.MisDatos;
import com.cursehantar.completecontrol.cardViewDispositivo.CustomAdapter;
import com.cursehantar.completecontrol.cardViewDispositivo.DataModel;
import com.cursehantar.completecontrol.cardViewDispositivo.MyData;
import com.cursehantar.completecontrol.R;
import com.cursehantar.completecontrol.databinding.ActivityMainBinding;
import com.cursehantar.completecontrol.databinding.FragmentGalleryBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.StorageReference;


import java.util.ArrayList;

public class GalleryFragment extends Fragment{

    private FragmentGalleryBinding binding;

    private static RecyclerView recyclerView;

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<DataModel> data;
    // Referencia a la base de datos de Firebase
    private DatabaseReference databaseReference;

    private FirebaseFirestore mfirestore;
    private FirebaseAuth mAuth;
    StorageReference storageReference;
    String storage_path = "dispositivos/*";
    private static final int COD_SEL_STORAGE = 300;
    private static final int COD_SEL_IMAGE = 300;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        //btnAddDevice = view.findViewById(R.id.btnAddDevice);
        //btnAddDevice.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        addDataToFirebase("Nuevo Dispositivo", "Número Predeterminado", "Marca Predeterminada", 1, R.drawable.celular);
        //    }
        //});

        recyclerView = view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mostrarDispositivos();

        // Obtener una referencia a la base de datos de Firebase
        //databaseReference = FirebaseDatabase.getInstance().getReference("datos");
        // Leer datos de Firebase
        //readDataFromFirebase();
        //mfirestore = FirebaseFirestore.getInstance();

        return view;
    }

    private void readDataFromFirebase() {
        // Añadir un listener para escuchar cambios en la base de datos
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Limpiar la lista actual
                data.clear();

                // Iterar sobre los elementos en dataSnapshot y agregarlos a la lista
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DataModel item = snapshot.getValue(DataModel.class);
                    data.add(item);
                }

                // Notificar al adaptador que los datos han cambiado
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Manejar errores
                Toast.makeText(getContext(), "Error al leer datos desde Firebase", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Ejemplo de cómo agregar un nuevo elemento a Firebase
    private void addDataToFirebase(String name, String numero, String marca, int drawable) {
        DataModel newItem = new DataModel(name, numero, marca, drawable);
        databaseReference.push().setValue(newItem);
    }

    // Ejemplo de cómo actualizar un elemento en Firebase
    private void updateDataInFirebase(String id, String newName) {
        // Obtener la referencia al elemento específico mediante su ID
        DatabaseReference itemReference = databaseReference.child(id);

        // Actualizar el nombre
        itemReference.child("name").setValue(newName);
    }

    // Ejemplo de cómo eliminar un elemento en Firebase
    private void deleteDataFromFirebase(String id) {
        // Obtener la referencia al elemento específico mediante su ID
        DatabaseReference itemReference = databaseReference.child(id);

        // Eliminar el elemento
        itemReference.removeValue();
    }

    public void mostrarDispositivos(){
        data = new ArrayList<DataModel>();
        for (int i = 0; i < MyData.nameArray.length; i++) {
            data.add(new DataModel(MyData.nameArray[i], MyData.numeroArray[i],MyData.marcaArray[i], MyData.drawableArray[i]));
        }
        adapter = new CustomAdapter(data);
        recyclerView.setAdapter(adapter);
    }
    public void agregarNuevoDato(String nombre, String numero, String marca, int drawableResId) {
        // Crea un nuevo objeto DataModel con los datos proporcionados
        DataModel nuevoDato = new DataModel(nombre, numero, marca, drawableResId);
        // Agrega el nuevo dato a la lista
        data.add(nuevoDato);
        // Notifica al adaptador que se ha agregado un nuevo dato
        adapter.notifyItemInserted(data.size() - 1);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}