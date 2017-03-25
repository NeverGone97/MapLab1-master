package com.example.nut_it.map;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.nut_it.map.Adapter.InfoWindowAdapter;
import com.example.nut_it.map.Model.Image;
import com.example.nut_it.map.Model.Place;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CreateGMap extends FragmentActivity implements OnMapReadyCallback {

    private MapFragment mapFragment;
    private String title, des, adress;
    private int catId;
    private double latitude, longtitude;
    private int zoom = 10;

    public CreateGMap(MapFragment mapFragment, double latitude, double longtitude, int catId,  String title, String des, String adress) {
        this.mapFragment = mapFragment;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.catId = catId;
        this.title = title;
        this.des = des;
        this.adress = adress;
        this.mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        locateMap(googleMap, latitude, longtitude, catId, title, des, zoom);
//            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                    == PackageManager.PERMISSION_GRANTED) {
//                googleMap.setMyLocationEnabled(true);
//            } else {
//                // Show rationale and request permission.
//            }
//            googleMap.setMyLocationEnabled(true);
    }

    public void locateMap(GoogleMap googleMap, double latitude, double longtitude, int catId, String title, String des, int zoom) {
        LatLng latLng = new LatLng(latitude, longtitude);
        googleMap.addMarker(createMarkeroptions(catId, latLng, title));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));


    }

    private MarkerOptions createMarkeroptions(int idCat, LatLng latLng, String title) {
        int icon = 0;
        switch (idCat) {
            case 1:
                icon = R.drawable.category1;
                break;
            case 2:
                icon = R.drawable.category2;
                break;
            case 3:
                icon = R.drawable.category9;
                break;
            case 4:
                icon = R.drawable.category4;
                break;
            case 5:
                icon = R.drawable.category6;
                break;
            case 6:
                icon = R.drawable.category5;
                break;
            case 7:
                icon = R.drawable.category12;
                break;
            case 8:
                icon = R.drawable.category13;
                break;
            case 9:
                icon = R.drawable.category11;
                break;
            case 10:
                icon = R.drawable.category14;
                break;
            case 11:
                icon = R.drawable.category17;
                break;
            case 12:
                icon = R.drawable.category16;
                break;
            case 13:
                icon = R.drawable.category15;
                break;
            case 14:
                icon = R.drawable.category14;
                break;
            case 15:
                icon = R.drawable.category15;
                break;
            case 16:
                icon = R.drawable.category16;
                break;
            case 17:
                icon = R.drawable.category17;
                break;
            default: icon = R.drawable.category10;
        }
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.icon(BitmapDescriptorFactory.fromResource(icon));
        markerOptions.position(latLng);
        markerOptions.title(title);


        return markerOptions;
    }
}