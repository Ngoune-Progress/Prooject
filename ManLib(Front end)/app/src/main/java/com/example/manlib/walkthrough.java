package com.example.manlib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.manlib.adapter.adapter_fragment;
import me.relex.circleindicator.CircleIndicator;

public class walkthrough extends AppCompatActivity {

    public ViewPager viewpager;
    adapter_fragment adapter_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough);

        viewpager = findViewById(R.id.viewpager);
        CircleIndicator indicator = findViewById(R.id.indicator);
        adapter_fragment = new adapter_fragment(getSupportFragmentManager());
        viewpager.setAdapter(adapter_fragment);
        indicator.setViewPager(viewpager);
        adapter_fragment.registerDataSetObserver(indicator.getDataSetObserver());
    }

    public void start(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_up, R.anim.stay);
        finish();
    }
}