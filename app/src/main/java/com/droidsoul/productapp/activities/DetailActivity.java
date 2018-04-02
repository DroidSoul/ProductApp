package com.droidsoul.productapp.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.droidsoul.productapp.R;
import com.droidsoul.productapp.fragments.ProductFragment;
import com.droidsoul.productapp.models.Product;

import org.parceler.Parcels;

public class DetailActivity extends AppCompatActivity {

    ViewPager vpPager;
    Product previous, current, next;
    MyPagerAdapter2 myPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setTitle("Product Detail");
        previous = Parcels.unwrap(getIntent().getParcelableExtra("previous"));
        current = Parcels.unwrap(getIntent().getParcelableExtra("current"));
        next = Parcels.unwrap(getIntent().getParcelableExtra("next"));
        vpPager = findViewById(R.id.viewpager);
        myPagerAdapter = new MyPagerAdapter2(getSupportFragmentManager());
        vpPager.setAdapter(myPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(vpPager);
        vpPager.setCurrentItem(1);
    }



    public class MyPagerAdapter2 extends FragmentPagerAdapter {
        private int NUM_ITEMS = 3;

        public MyPagerAdapter2(FragmentManager fragmentManager) {
            super(fragmentManager);
        }


        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return ProductFragment.newInstance(previous, "previous");
                case 1:
                    return ProductFragment.newInstance(current, "current");
                case 2:
                    return ProductFragment.newInstance(next, "next");
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }
    }

    }
