package com.example.manlib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.manlib.adapter.HomeAdapter.VedetteAdapter;
import com.example.manlib.adapter.HomeAdapter.VedetteClass;
import com.example.manlib.adapter.RecentAdapter.Model;
import com.example.manlib.adapter.RecentAdapter.MyAdapter;
import com.google.android.material.navigation.NavigationView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class startpage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static final float END_SCALES = 0.7f;
    private final OkHttpClient httpClient = new OkHttpClient();

    RecyclerView liste_vedette;
    RecyclerView recently_added;
    RecyclerView.Adapter adapterQ;
    ImageView menu_icon;
    LinearLayout contentView;
    TextView lib_name;

//    DRAWER MANU
    DrawerLayout drawerLayout;
    NavigationView navigationView;


    private MyAdapter adapter;
    private ArrayList<Model> list = new ArrayList<Model>();

    String UID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpage);


        StorageReference storageRef = FirebaseStorage.getInstance().getReference();


        //liste_vedette = (RecyclerView) findViewById(R.id.liste_vedette);
      //  recently_added = (RecyclerView) findViewById(R.id.recently_added);
        contentView = (LinearLayout) findViewById(R.id.content);
       // lib_name = (TextView) findViewById(R.id.logo_appNmae);


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        menu_icon = (ImageView) findViewById(R.id.menu_icon);
        navigationDrawer();



        Intent intentS = getIntent();
        UID = intentS.getStringExtra("UID");



      //  liste_vedette();
     //   recently_added();
//        TODO: CATEGORIES (CLASS AND ADAPTER

    }

    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        menu_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerVisible(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {

        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        //drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALES);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });

    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        if(!drawerLayout.isDrawerVisible(GravityCompat.START)){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Offline...")
                    .setMessage("Do you want to disconnect...? ")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(startpage.this, MainActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_up, R.anim.stay);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            builder.show();
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.nav_search:
                Intent intent33 = new Intent(startpage.this, recherche.class);
                intent33.putExtra("UID", UID);
                startActivity(intent33);
                overridePendingTransition(R.anim.fade_in, R.anim.stay);
                break;
            case R.id.nav_add_cat:
                Intent intent = new Intent(startpage.this, nav_menu_category.class);
                intent.putExtra("UID", UID);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.stay);
                break;
            case R.id.nav_about:
                Intent intent0 = new Intent(startpage.this, nav_menu_about.class);
                intent0.putExtra("UID", UID);
                startActivity(intent0);
                overridePendingTransition(R.anim.fade_in, R.anim.stay);
                break;
            case R.id.nav_all:
                Intent intent00 = new Intent(startpage.this, nav_menu_vueDensemble.class);
                intent00.putExtra("UID", UID);
                startActivity(intent00);
                overridePendingTransition(R.anim.fade_in, R.anim.stay);
                break;
            case R.id.nav_info:
                Intent intent1 = new Intent(startpage.this, nav_menu_mesInfo.class);
                intent1.putExtra("UID", UID);
                startActivity(intent1);
                overridePendingTransition(R.anim.fade_in, R.anim.stay);
                break;
            case R.id.nav_deconnexion:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Deconnexion...")
                        .setMessage("Voulez-vous vous deconnecté? ")
                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(startpage.this, MainActivity.class);
                                intent.putExtra("UID", UID);
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_up, R.anim.stay);
                                finish();
                            }
                        })
                        .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.show();

                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }


    private void getList() {
//        final ProgressDialog pd = new ProgressDialog(this);
//        pd.setMessage("Connection...");
//        pd.show();


        Request request = new Request.Builder()
                .url("http://172.20.10.6:8080/E-commerce/webresources/productcontroller/list").get()
//                .addHeader("custom-key", "mkyong")  // add request headers
//                .addHeader("User-Agent", "OkHttp Bot")
                .build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new AlertDialog.Builder(startpage.this)
                                .setTitle("Error Occured")
//                                .setMessage("Please ensure you have a good connection peey")
                                .setMessage(e.getMessage())

                                // Specifying a listener allows you to take an action before dismissing the dialog.
                                // The dialog is automatically dismissed when a dialog button is clicked.
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Continue with delete operation
                                    }
                                })

                                // A null listener allows the button to dismiss the dialog and take no further action.
                                .setNegativeButton(android.R.string.no, null)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();

//                        pd.dismiss();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) {
//                        throw new IOException("Unexpected code " + response);
                        startpage.this.runOnUiThread(new Runnable() {
//                            String r = response.body().string();
//                            JSONObject json = new JSONObject(r);

                            @Override
                            public void run() {
                                new AlertDialog.Builder(startpage.this)
                                        .setTitle("Verify")
                                        .setMessage("email or password incorrect")

                                        // Specifying a listener allows you to take an action before dismissing the dialog.
                                        // The dialog is automatically dismissed when a dialog button is clicked.
                                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                // Continue with delete operation
                                            }
                                        })

                                        // A null listener allows the button to dismiss the dialog and take no further action.
                                        .setNegativeButton(android.R.string.no, null)
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .show();
//                                pd.dismiss();

                            }
                        });
                    }
                    else {
                        Gson gson = null;
                        startpage.this.runOnUiThread(new Runnable() {
                            final List<ProductAdapter> list1 = Arrays.asList();

//                            String r = response.body().string();
//                            JSONObject json = new JSONObject(r);
////                            list1 = r;
//
//                            String jsonOutput = response.toString();
//                            Type listType = new TypeToken<List<ProductAdapter>>(){}.getType();
//                            list = gson.fromJson(jsonOutput, listType);

                            @Override
                            public void run() {


//

                                // Get response body

                            }
                        });
                    }
                    // Get response headers


                } catch (Exception e) {
                    e.printStackTrace();
//                    pd.dismiss();

                }
            }
        });

    }

    private void liste_vedette() {

        liste_vedette.setHasFixedSize(true);
        liste_vedette.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<VedetteClass> locationVedette = new ArrayList<>();

        locationVedette.add(new VedetteClass(R.drawable.atom, "", "", (float) 3.5));
        locationVedette.add(new VedetteClass(R.drawable.atom, "", "", (float) 3.5));

        adapterQ = new VedetteAdapter(locationVedette);
        liste_vedette.setAdapter(adapterQ);


//        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffef400, 0xffaff6});


    }

    private void recently_added() {

//       ArrayList<Model> list = new ArrayList<>();

        adapter = new MyAdapter(this, list);
        recently_added.setAdapter(adapter);


        recently_added.setHasFixedSize(true);
        recently_added.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        Request request = new Request.Builder()
                .url("http://172.20.10.6:8080/E-commerce/webresources/productcontroller/list").get()
//                .addHeader("custom-key", "mkyong")  // add request headers
//                .addHeader("User-Agent", "OkHttp Bot")
                .build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new AlertDialog.Builder(startpage.this)
                                .setTitle("Error Occured")
//                                .setMessage("Please ensure you have a good connection peey")
                                .setMessage(e.getMessage())

                                // Specifying a listener allows you to take an action before dismissing the dialog.
                                // The dialog is automatically dismissed when a dialog button is clicked.
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Continue with delete operation
                                    }
                                })

                                // A null listener allows the button to dismiss the dialog and take no further action.
                                .setNegativeButton(android.R.string.no, null)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();

//                        pd.dismiss();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {

                    if (!response.isSuccessful()) {
//                        throw new IOException("Unexpected code " + response);
                        startpage.this.runOnUiThread(new Runnable() {
//                            String r = response.body().string();
//                            JSONObject json = new JSONObject(r);

                            @Override
                            public void run() {
                                new AlertDialog.Builder(startpage.this)
                                        .setTitle("Verify")
                                        .setMessage("email or password incorrect")

                                        // Specifying a listener allows you to take an action before dismissing the dialog.
                                        // The dialog is automatically dismissed when a dialog button is clicked.
                                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                // Continue with delete operation
                                            }
                                        })

                                        // A null listener allows the button to dismiss the dialog and take no further action.
                                        .setNegativeButton(android.R.string.no, null)
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .show();
//                                pd.dismiss();

                            }
                        });
                    }
                    else {
                        Gson gson = new Gson();

                        String jsonOutput = null;
                        try {
                            jsonOutput = response.body().string();
                            Type listType = new TypeToken<ArrayList<ProductAdapter>>(){}.getType();
                            list = gson.fromJson(jsonOutput, listType);
                            System.out.println(list);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        startpage.this.runOnUiThread(new Runnable() {


                            @Override
                            public void run() {
                                System.out.println("Look at this >>>>> "+ list.get(0));



                                //adapter.notifyDataSetChanged();


                                // Get response body

                            }

                        });
                    }
                    // Get response headers


                } catch (Exception e) {
                    e.printStackTrace();
//                    pd.dismiss();

                }
            }

        });


//
//        mDatabase.child(UID).child("Books").child("Category").child("Informatique").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    Model model = dataSnapshot.getValue(Model.class);
//                    list.add(model);
//                }adapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                adapter.notifyAll();
//            }
//        });

//        mDatabase.child(UID).child("Books").child("Category").child("Cuisine").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    Model model = dataSnapshot.getValue(Model.class);
//                    list.add(model);
//                }adapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                adapter.notifyAll();
//            }
//        });
//
//        mDatabase.child(UID).child("Books").child("Category").child("Romance").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    Model model = dataSnapshot.getValue(Model.class);
//                    list.add(model);
//                }adapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                adapter.notifyAll();
//            }
//        });
//
//        mDatabase.child(UID).child("Books").child("Category").child("Sports").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    Model model = dataSnapshot.getValue(Model.class);
//                    list.add(model);
//                }
//
//
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
//        mDatabase.child(UID).child("Books").child("Category").child("Physics").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    Model model = dataSnapshot.getValue(Model.class);
//                    list.add(model);
//                }
//
//
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


//        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffef400, 0xffaff6});


    }


    public void allcat(View view) {
        Intent intent = new Intent(startpage.this, nav_menu_category.class);
        intent.putExtra("UID", UID);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.stay);
    }

    public void informatique(View view) {
        Intent intent = new Intent(startpage.this, Scat.class);
        intent.putExtra("UID", UID);
        String Scat = "Informatique";
        intent.putExtra("Scat", Scat);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.stay);
    }

    public void physics(View view) {
        Intent intent = new Intent(startpage.this, Scat.class);
        intent.putExtra("UID", UID);
        String Scat = "Physics";
        intent.putExtra("Scat", Scat);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.stay);
    }

    public void cuisine(View view) {
        Intent intent = new Intent(startpage.this, Scat.class);
        intent.putExtra("UID", UID);
        String Scat = "Cuisine";
        intent.putExtra("Scat", Scat);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.stay);
    }

    public void search(View view) {
        Intent intent33 = new Intent(startpage.this, recherche.class);
        intent33.putExtra("UID", UID);
        startActivity(intent33);
        overridePendingTransition(R.anim.fade_in, R.anim.stay);
    }
}



//        list = new ArrayList<>();
//        adapter = new MyAdapter(this, list);
//        recently_added.setAdapter(adapter);
//
//
//        recently_added.setHasFixedSize(true);
//        recently_added.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//
//
//        mDatabase.child(UID).child("Books").child("Category").child("Informatique").addValueEventListener(new ValueEventListener() {
//@Override
//public void onDataChange(@NonNull DataSnapshot snapshot) {
//        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//        Model model = dataSnapshot.getValue(Model.class);
//        list.add(model);
//        }adapter.notifyDataSetChanged();
//
//        }
//
//@Override
//public void onCancelled(@NonNull DatabaseError error) {
//        adapter.notifyAll();
//        }
//        });
//
//        mDatabase.child(UID).child("Books").child("Category").child("Cuisine").addValueEventListener(new ValueEventListener() {
//@Override
//public void onDataChange(@NonNull DataSnapshot snapshot) {
//        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//        Model model = dataSnapshot.getValue(Model.class);
//        list.add(model);
//        }adapter.notifyDataSetChanged();
//
//        }
//
//@Override
//public void onCancelled(@NonNull DatabaseError error) {
//        adapter.notifyAll();
//        }
//        });
//
//        mDatabase.child(UID).child("Books").child("Category").child("Romance").addValueEventListener(new ValueEventListener() {
//@Override
//public void onDataChange(@NonNull DataSnapshot snapshot) {
//        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//        Model model = dataSnapshot.getValue(Model.class);
//        list.add(model);
//        }adapter.notifyDataSetChanged();
//
//        }
//
//@Override
//public void onCancelled(@NonNull DatabaseError error) {
//        adapter.notifyAll();
//        }
//        });
//
//        mDatabase.child(UID).child("Books").child("Category").child("Sports").addValueEventListener(new ValueEventListener() {
//@Override
//public void onDataChange(@NonNull DataSnapshot snapshot) {
//        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//        Model model = dataSnapshot.getValue(Model.class);
//        list.add(model);
//        }
//
//
//        adapter.notifyDataSetChanged();
//
//        }
//
//@Override
//public void onCancelled(@NonNull DatabaseError error) {
//        adapter.notifyAll();
//        }
//        });
//
//        mDatabase.child(UID).child("Books").child("Category").child("Physics").addValueEventListener(new ValueEventListener() {
//@Override
//public void onDataChange(@NonNull DataSnapshot snapshot) {
//        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//        Model model = dataSnapshot.getValue(Model.class);
//        list.add(model);
//        }
//
//
//        adapter.notifyDataSetChanged();
//
//        }
//
//@Override
//public void onCancelled(@NonNull DatabaseError error) {
//        adapter.notifyAll();
//        }
//        });
//
//
//
////        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffef400, 0xffaff6});
