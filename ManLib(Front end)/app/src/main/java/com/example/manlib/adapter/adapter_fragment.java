package com.example.manlib.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.manlib.fragment.fragment1;
import com.example.manlib.fragment.fragment2;
import com.example.manlib.fragment.fragment3;


public class adapter_fragment extends FragmentStatePagerAdapter {

    public adapter_fragment(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                fragment1 tab1 = new fragment1();
                return tab1;
            case 1:
                fragment2 tab2 = new fragment2();
                return tab2;
            case 2:
                fragment3 tab3 = new fragment3();
                return tab3;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return 3;
    }

}
