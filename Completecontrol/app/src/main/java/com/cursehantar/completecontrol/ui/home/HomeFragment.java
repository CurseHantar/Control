package com.cursehantar.completecontrol.ui.home;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.cursehantar.completecontrol.GyroscopeActivity;
import com.cursehantar.completecontrol.ProximityActivity;
import com.cursehantar.completecontrol.R;
import com.cursehantar.completecontrol.RotationVectorActivity;
import com.cursehantar.completecontrol.databinding.FragmentHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private Bundle bundle;
    private TextView text_home;
    private Button imagenProximity;
    private Button imagenGyroscope;
    private Button imagenRotationVector;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        //Para ir al activity de los sensores
        imagenProximity = rootView.findViewById(R.id.imagenProximity);
        imagenGyroscope = rootView.findViewById(R.id.imagenGiroscopy);
        imagenRotationVector = rootView.findViewById(R.id.imagenRotacion);

        // Configurar eventos de clic
        imagenProximity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProximity();
            }
        });

        imagenGyroscope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGyroscope();
            }
        });

        imagenRotationVector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRotationVector();
            }
        });


        // MENSAJE DE INICIO DE SESIÓN
        // Inicializar el TextView
        text_home = rootView.findViewById(R.id.text_home);

        // Obtener el Bundle de la actividad
        bundle = getActivity().getIntent().getExtras();

        if (bundle != null) {
            String saludo = bundle.getString("nombre");

            if (saludo != null) {
                text_home.append("Bienvenido " + saludo + " :D");
            }
        }

        return rootView;

    }
    public void openGyroscope() {
        Intent intent = new Intent(getActivity(), GyroscopeActivity.class);
        startActivity(intent);
    }

    public void openProximity() {
        Intent intent = new Intent(getActivity(), ProximityActivity.class);
        startActivity(intent);
    }

    public void openRotationVector() {
        Intent intent = new Intent(getActivity(), RotationVectorActivity.class);
        startActivity(intent);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}