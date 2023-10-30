package com.cursehantar.completecontrol.ui.slideshow;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.cursehantar.completecontrol.R;
import com.cursehantar.completecontrol.databinding.FragmentSlideshowBinding;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.Polyline;

import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment {

    MapView mapView;

    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_slideshow, container, false);

        mapView = new MapView(requireActivity());
        mapView.setTileSource(TileSourceFactory.MAPNIK);

        FrameLayout mapContainer = view.findViewById(R.id.map_container);
        mapContainer.addView(mapView);

        GeoPoint startPoint = new GeoPoint(-33.4489,-70.6693);

        Marker startMarker = new Marker(mapView);
        startMarker.setPosition(startPoint);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        mapView.getOverlays().add(startMarker);

        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);

        mapView.getController().setCenter(startPoint);

        mapView.getController().setZoom(15);


        mapView.getOverlays().add(new Overlay() {
            @Override
            public boolean onSingleTapConfirmed(final MotionEvent e, final MapView mapView) {
                return true;
            }
        });


        mapView.setTileSource(TileSourceFactory.MAPNIK);
        List<GeoPoint> geoPoints = new ArrayList<>();
        Polyline line = new Polyline();
        line.setPoints(geoPoints);
        line.setColor(Color.BLUE);
        line.setWidth(5);

        mapView.getOverlayManager().add(line);


        Drawable customMarker = getResources().getDrawable(R.drawable.dialogbkg);

        ArrayList<OverlayItem> items = new ArrayList<>();
        items.add(new OverlayItem("Mi marcador", "Descripción", startPoint));


        mapView.getOverlays().add(new Overlay() {

            @Override
            public boolean onSingleTapConfirmed(final MotionEvent e, final MapView mapView) {

                return true;
            }
        });

        return view;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        mapView.onDetach();
    }
}