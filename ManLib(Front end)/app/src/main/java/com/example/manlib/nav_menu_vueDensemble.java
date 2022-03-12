package com.example.manlib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.navigation.NavigationView;

public class nav_menu_vueDensemble extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static final float END_SCALE = 0.7f;

    ImageView menu_icon;
    LinearLayout contentView;


    DrawerLayout drawerLayout;
    NavigationView navigationView;

    String UID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_menu_vue_densemble);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_vueD);
        navigationView = (NavigationView) findViewById(R.id.navigation_view_vueD);

        contentView = (LinearLayout) findViewById(R.id.content_veuD);

        menu_icon = (ImageView) findViewById(R.id.menu_icon_vueD);
        navigationDrawer();

        Intent intentS = getIntent();
        UID = intentS.getStringExtra("UID");
    }

    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_all);
        menu_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerVisible(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        animatedNavigationDrawer();
    }

    private void animatedNavigationDrawer() {
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
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

        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            overridePendingTransition(R.anim.fade_in, R.anim.stay);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.nav_all:
                break;

            case R.id.nav_search:
                Intent intent33 = new Intent(nav_menu_vueDensemble.this, recherche.class);
                intent33.putExtra("UID", UID);
                startActivity(intent33);
                overridePendingTransition(R.anim.fade_in, R.anim.stay);
                break;

            case R.id.nav_about:
                Intent intent00 = new Intent(nav_menu_vueDensemble.this, nav_menu_about.class);
                intent00.putExtra("UID", UID);
                startActivity(intent00);
                overridePendingTransition(R.anim.fade_in, R.anim.stay);
                break;
            case R.id.nav_home:
                Intent intent = new Intent(nav_menu_vueDensemble.this, startpage.class);
                intent.putExtra("UID", UID);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.stay);
                finish();
                break;
            case R.id.nav_add_cat:
                Intent intent0 = new Intent(nav_menu_vueDensemble.this, nav_menu_category.class);
                intent0.putExtra("UID", UID);
                startActivity(intent0);
                overridePendingTransition(R.anim.fade_in, R.anim.stay);
                break;
            case R.id.nav_info:
                Intent intent1 = new Intent(nav_menu_vueDensemble.this, nav_menu_mesInfo.class);
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
                                Intent intent = new Intent(nav_menu_vueDensemble.this, MainActivity.class);
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

    public void ajouter(View view) {
        Intent intent = new Intent(nav_menu_vueDensemble.this, ajoutlivre.class);
        intent.putExtra("UID", UID);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.stay);
    }

    public void stock(View view) {
        Intent intent = new Intent(nav_menu_vueDensemble.this, stock.class);
        intent.putExtra("UID", UID);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.stay);
    }

    public void vente(View view) {
        Intent intent = new Intent(nav_menu_vueDensemble.this, vente.class);
        intent.putExtra("UID", UID);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.stay);
    }

    public void historique(View view) {
        Intent intent = new Intent(nav_menu_vueDensemble.this, Historique.class);
        intent.putExtra("UID", UID);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.stay);
    }
}