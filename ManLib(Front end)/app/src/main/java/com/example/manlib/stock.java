package com.example.manlib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manlib.adapter.RecentAdapter.Model;
import com.example.manlib.adapter.RecentAdapter.MyAdapter;


import java.util.ArrayList;

public class stock extends AppCompatActivity {


    RecyclerView stock;
    ImageView menu_back_stock;

    String UID;
    String Scat;

    private MyAdapter adapter;
    private ArrayList<Model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);


        stock = (RecyclerView) findViewById(R.id.stock);
        menu_back_stock = (ImageView) findViewById(R.id.menu_back_stock);


        Intent intentS = getIntent();
        UID = intentS.getStringExtra("UID");
        Vstock(UID);
    }

    public void back(View view) {
        onBackPressed();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.stay);
        finish();
    }

    private void Vstock(String UID) {


        list = new ArrayList<>();
        adapter = new MyAdapter(this, list);
        stock.setAdapter(adapter);


        stock.setHasFixedSize(true);
        stock.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));





//        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffef400, 0xffaff6});


    }
}