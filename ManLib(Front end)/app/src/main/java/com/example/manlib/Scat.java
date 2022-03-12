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
import com.google.firebase.storage.StorageReference;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class Scat extends AppCompatActivity {


    StorageReference storageRef;

    RecyclerView selected_cat;
    ImageView menu_back_sc,  logo_sc;
    TextView title_sc;

    String UID;
    String Scat;

    private MyAdapter adapter;
    private ArrayList<JsonObject> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scat);



        selected_cat = (RecyclerView) findViewById(R.id.selected_cat);
        menu_back_sc = (ImageView) findViewById(R.id.menu_back_sc);
        logo_sc = (ImageView) findViewById(R.id.logo_sc);
        title_sc = (TextView) findViewById(R.id.title_sc);

        Intent intentS = getIntent();
        UID = intentS.getStringExtra("UID");
        Scat = intentS.getStringExtra("Scat");
        title_sc.setText(Scat);

//       selected_cat(UID, Scat);
    }

//    private void selected_cat(String uid, String scat) {
//
//        list = new ArrayList<>();
//        adapter = new MyAdapter(this, list);
//        selected_cat.setAdapter(adapter);
//
//        selected_cat.setHasFixedSize(true);
//        selected_cat.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//
//        mDatabase.child(uid).child("Books").child("Category").child(scat).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    Model model = dataSnapshot.getValue(Model.class);
//                    list.add(model);
//                }
//                adapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                adapter.notifyAll();
//            }
//        });
//
//    }

    public void back(View view) {
        onBackPressed();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.stay);
        finish();
    }
}