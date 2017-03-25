package com.example.nut_it.map.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nut_it.map.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;


public class InfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    private TextView tvTitle, tvDes, tvAddress;
    private ImageView imgAvatar;
    private LayoutInflater inflater = null;
    private String title, des, address;
    private ArrayList<String> avatar;

    public InfoWindowAdapter(LayoutInflater inflater, String title, String des, String address, ArrayList<String> avatar) {
        this.inflater = inflater;
        this.title = title;
        this.des = des;
        this.address = address;
        this.avatar = avatar;
    }


    @Override
    public View getInfoWindow(Marker arg0) {
        View v = inflater.inflate(R.layout.windown_layout, null);
        tvTitle = (TextView) v.findViewById(R.id.tvTitle);
        tvDes = (TextView) v.findViewById(R.id.tvDes);
        tvAddress = (TextView) v.findViewById(R.id.tvAddress);
        imgAvatar = (ImageView) v.findViewById(R.id.imgAvatar);
        tvTitle.setText(title);
        tvDes.setText(des);
        tvAddress.setText(address);

        //imgAvatar.setImageResource(avatar.get(0));
        return v;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
