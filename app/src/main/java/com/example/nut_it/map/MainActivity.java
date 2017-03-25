package com.example.nut_it.map;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.nut_it.map.Adapter.GridViewAdapter;
import com.example.nut_it.map.Adapter.NaviAdapter;
import com.example.nut_it.map.Model.CallService;
import com.example.nut_it.map.Model.ListCategory;
import com.example.nut_it.map.Model.ListPlace;
import com.example.nut_it.map.Model.Navi;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    int i;
    public int categoryId = 0;
    private ListView navBar;
    private ArrayList<Navi> arrNavi;
    private MapFragment mapFragment;
    private DrawerLayout drawerlayout;
    FloatingActionButton fab;

    CreateGMap createGMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapFragment = new MapFragment();
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment);
//        mapFragment.getMapAsync(this);


        getPlace(5);



/* Getting reference to the DrawerLayout */
        drawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navBar = (ListView) findViewById(R.id.left_drawer);
        fab= (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerlayout.openDrawer(GravityCompat.START);
            }
        });
        arrNavi = new ArrayList<>();
        arrNavi.add(new Navi(R.drawable.scene));
        arrNavi.add(new Navi(R.drawable.pagoda));
        arrNavi.add(new Navi(R.drawable.restaurant));
        arrNavi.add(new Navi(R.drawable.atm));
        arrNavi.add(new Navi(R.drawable.fuel));
        NaviAdapter naviAdapter = new NaviAdapter(MainActivity.this, R.layout.nav_item, arrNavi);
        navBar.setAdapter(naviAdapter);
        navBar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getPlace(position);
            }
        });
    }

    private void getCategory() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://bwhere.vn/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        CallService callService = retrofit.create(CallService.class);
        Call<ListCategory> call = callService.getListCategory();

        call.enqueue(new Callback<ListCategory>() {
            @Override
            public void onResponse(Call<ListCategory> call, Response<ListCategory> response) {
                ListCategory respon = response.body();
                Log.d("Result", respon.getData().get(0).getNameVi() + "");
            }

            @Override
            public void onFailure(Call<ListCategory> call, Throwable t) {
                Log.d("Result", t.getMessage());
            }
        });
    }

    private void getPlace(final int catId) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://bwhere.vn/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        CallService callService = retrofit.create(CallService.class);
        Call<ListPlace> call = callService.getListPlace();

        call.enqueue(new Callback<ListPlace>() {

            @Override
            public void onResponse(Call<ListPlace> call, Response<ListPlace> response) {
                String title, des, desFull, address;
                ArrayList<String> avatar = new ArrayList<String>();

                double latitude, longtitude;
                ListPlace respon = response.body();
                if(catId==0){
                    categoryId=2;
                   drawerlayout.closeDrawers();
                }else if(catId==1){
                    categoryId=1;
                    drawerlayout.closeDrawers();
                }else if(catId==2){
                    categoryId=3;
                    drawerlayout.closeDrawers();
                }else if(catId==3){
                    categoryId=4;
                    drawerlayout.closeDrawers();
                }else if(catId==4){
                    categoryId=5;
                    drawerlayout.closeDrawers();
                }
                for (int i = 0; i < respon.getData().size(); i++) {
                    if (categoryId == 0){
                        latitude = Double.parseDouble(respon.getData().get(i).getLatitude());
                        longtitude = Double.parseDouble(respon.getData().get(i).getLongitude());
                        int catId = respon.getData().get(i).getCategoryId();
                        title = respon.getData().get(i).getNameVi();
                        Log.d("Res", catId + " " + title);
                        des = respon.getData().get(i).getShortDescriptionVi();
                        address = respon.getData().get(i).getAddressVi();
                        createGMap = new CreateGMap(mapFragment, latitude, longtitude, catId, title, des, address);
                    }
                    else if (categoryId == respon.getData().get(i).getCategoryId() ){
                        latitude = Double.parseDouble(respon.getData().get(i).getLatitude());
                        longtitude = Double.parseDouble(respon.getData().get(i).getLongitude());
                        title = respon.getData().get(i).getNameVi();
                        des = respon.getData().get(i).getShortDescriptionVi();
                        address = respon.getData().get(i).getAddressVi();
                        createGMap = new CreateGMap(mapFragment, latitude, longtitude, categoryId, title, des, address);
                    }
                }
            }

            @Override
            public void onFailure(Call<ListPlace> call, Throwable t) {
                Log.d("Result", t.getMessage());
            }
        });
    }
}


