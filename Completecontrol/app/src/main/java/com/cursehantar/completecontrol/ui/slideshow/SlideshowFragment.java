package com.cursehantar.completecontrol.ui.slideshow;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.Manifest;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;


import com.cursehantar.completecontrol.R;
import com.cursehantar.completecontrol.databinding.FragmentSlideshowBinding;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment {

    MapView mapView;
    private FragmentSlideshowBinding binding;
    Button locationButton;
    Button add_marker_button;
    Button clearButton;
    List<GeoPoint> geoPoints = new ArrayList<>();
    Polyline line;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_slideshow, container, false);


        //mapView = new MapView(requireActivity());
        //mapView.setTileSource(TileSourceFactory.MAPNIK);
//
        //FrameLayout mapContainer = view.findViewById(R.id.map_container);
        //mapContainer.addView(mapView);
//
        //GeoPoint startPoint = new GeoPoint(-33.4489,-70.6693);
//
        //Marker startMarker = new Marker(mapView);
        //startMarker.setPosition(startPoint);
        //startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
//
        //mapView.getOverlays().add(startMarker);
//
        //mapView.setBuiltInZoomControls(true);
        //mapView.setMultiTouchControls(true);
//
        //mapView.getController().setCenter(startPoint);
//
        //mapView.getController().setZoom(50);
//
//
        //mapView.getOverlays().add(new Overlay() {
        //    @Override
        //    public boolean onSingleTapConfirmed(final MotionEvent e, final MapView mapView) {
        //        return true;
        //    }
        //});
//
//
        //mapView.setTileSource(TileSourceFactory.MAPNIK);
        //List<GeoPoint> geoPoints = new ArrayList<>();
        //Polyline line = new Polyline();
        //line.setPoints(geoPoints);
        //line.setColor(Color.GREEN);
        //line.setWidth(5);
//
        //mapView.getOverlayManager().add(line);
//
//
        //Drawable customMarker = getResources().getDrawable(R.drawable.dialogbkg);
//
        //ArrayList<OverlayItem> items = new ArrayList<>();
        //items.add(new OverlayItem("Mi marcador", "Descripción", startPoint));
//
        //mapView.getOverlays().add(new Overlay() {
//
        //    @Override
        //    public boolean onSingleTapConfirmed(final MotionEvent e, final MapView mapView) {
//
        //        return true;
        //    }
        //});

        //Ubicacion establecida en Santiago

        mapView = view.findViewById(R.id.mapView);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);

        GeoPoint santiago = new GeoPoint(-33.4691, -70.6420);
        mapView.getController().setCenter(santiago);
        mapView.getController().setZoom(12); // Establece el nivel de zoom


        //Para mostrar Ubicacion tiempo real

        MyLocationNewOverlay locationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(getContext()), mapView);
        locationOverlay.enableMyLocation();
        mapView.getOverlays().add(locationOverlay);


        locationButton = view.findViewById(R.id.locationButton);

        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                } else {
                    GeoPoint startPoint = locationOverlay.getMyLocation();
                    mapView.getController().setCenter(startPoint);
                }
            }
        });


        //Marcar en el mapa

        List<GeoPoint> geoPoints = new ArrayList<>();
        Polyline line = new Polyline();
        line.setPoints(geoPoints);
        line.setColor(Color.BLUE);
        line.setWidth(5);

        mapView.getOverlayManager().add(line);

        Button addMarkerButton = view.findViewById(R.id.add_marker_button);
        addMarkerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Agregar un nuevo marcador en la ubicación actual
                GeoPoint currentLocation = locationOverlay.getMyLocation();
                Marker marker = new Marker(mapView);
                marker.setPosition(currentLocation);
                mapView.getOverlays().add(marker);

                // Agregar el punto a la lista de puntos para el recorrido
                geoPoints.add(currentLocation);

                // Actualizar la Polyline
                line.setPoints(geoPoints);

                mapView.invalidate(); // Actualiza el mapa

            }
        });

        //Desmarcar en el mapa (cuando lo clickee)

        // Antes de añadir el marcador, asegúrate de tener una referencia a él
        //Marker marker = new Marker(mapView);
        //marker.setPosition(santiago);
//
        //// Añade el marcador a la lista de overlays
        //mapView.getOverlays().add(marker);
//
        //// Luego, configura el OnMarkerClickListener
        //marker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
        //    private long lastClickTime = 0;
        //    @Override
        //    public boolean onMarkerClick(Marker marker, MapView mapView) {
        //        long clickTime = System.currentTimeMillis();
//
        //        // Si el tiempo entre clics es menor a 500ms (medio segundo), considera que es un doble clic
        //        if (clickTime - lastClickTime < 500) {
        //            // Se hizo doble clic, elimina el marcador
        //            mapView.getOverlays().remove(marker);
//
        //            // Remueve el punto de la lista de puntos para el recorrido
        //            geoPoints.remove(marker.getPosition());
//
        //            // Actualiza la Polyline
        //            line.setPoints(geoPoints);
//
        //            mapView.invalidate(); // Actualiza el mapa
//
        //            return true; // Evento manejado
        //        }
//
        //        lastClickTime = clickTime; // Actualiza el tiempo del último clic
//
        //        return true; // Evento manejado
        //    }
        //});


        //Desmarcar mapa

        clearButton = view.findViewById(R.id.clear_button);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Eliminar todos los overlays
                //mapView.getOverlays().clear();

                // Limpiar la lista de puntos y la polyline
                geoPoints.clear();
                line.setPoints(geoPoints);

                mapView.invalidate(); // Actualiza el mapa
                TextView distanciaTextView = view.findViewById(R.id.distancia_textview);
                distanciaTextView.setText("Distancia: ");
            }
        });


        //Marcar clickeando el mapa

        mapView.getOverlays().add(new Overlay() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e, MapView mapView) {
                // Obtener las coordenadas del punto donde se hizo clic
                GeoPoint tappedPoint = (GeoPoint) mapView.getProjection().fromPixels((int)e.getX(), (int)e.getY());
                // Crear y agregar el marcador en el lugar donde se hizo clic
                Marker marker = new Marker(mapView);
                marker.setPosition(tappedPoint);
                mapView.getOverlays().add(marker);

                // Agregar el punto a la lista de puntos para el recorrido
                geoPoints.add(tappedPoint);

                // Actualizar la Polyline
                line.setPoints(geoPoints);
                mapView.invalidate(); // Actualizar el mapa

                return true;
            }
        });


        //Boton para calcular distancia

        Button calcularDistanciaButton = view.findViewById(R.id.calcular_distancia_button);
        calcularDistanciaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Distancia", "Se hizo clic en el botón de cálculo de distancia");

                if (geoPoints.size() >= 2) {
                    GeoPoint punto1 = geoPoints.get(0);
                    GeoPoint punto2 = geoPoints.get(1);

                    double distanciaEnKilometros = calcularDistanciaEnKilometros(punto1, punto2);
                    TextView distanciaTextView = view.findViewById(R.id.distancia_textview);
                    distanciaTextView.setText("Distancia: " + distanciaEnKilometros + " Kilometros");
                } else {
                    // No hay suficientes marcadores para calcular la distancia
                    // Puedes mostrar un mensaje al usuario o tomar otra acción adecuada.
                }
            }
        });



        return view;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mapView.getOverlayManager().get(0).setEnabled(true);
                locationButton.performClick();
            }
        }
    }

    public double calcularDistanciaEnMetros(GeoPoint punto1, GeoPoint punto2) {
        double radioTierra = 6371000; // Radio de la Tierra en metros

        double latitud1 = Math.toRadians(punto1.getLatitude());
        double longitud1 = Math.toRadians(punto1.getLongitude());
        double latitud2 = Math.toRadians(punto2.getLatitude());
        double longitud2 = Math.toRadians(punto2.getLongitude());

        double dLat = latitud2 - latitud1;
        double dLon = longitud2 - longitud1;

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(latitud1) * Math.cos(latitud2) *
                        Math.sin(dLon/2) * Math.sin(dLon/2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        //return radioTierra * c;
        double distancia = radioTierra * c;
        Log.d("Distancia", "Distancia calculada: " + distancia + " metros");
        return distancia;
    }

    public double calcularDistanciaEnKilometros(GeoPoint punto1, GeoPoint punto2) {
        double radioTierraKilometros = 6371; // Radio de la Tierra en kilómetros

        double latitud1 = Math.toRadians(punto1.getLatitude());
        double longitud1 = Math.toRadians(punto1.getLongitude());
        double latitud2 = Math.toRadians(punto2.getLatitude());
        double longitud2 = Math.toRadians(punto2.getLongitude());

        double dLat = latitud2 - latitud1;
        double dLon = longitud2 - longitud1;

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(latitud1) * Math.cos(latitud2) *
                        Math.sin(dLon/2) * Math.sin(dLon/2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return radioTierraKilometros * c;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        mapView.onDetach();
    }
}