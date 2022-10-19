package com.example.ph20617_mob201_assignment;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;


import com.example.ph20617_mob201_assignment.Adapter.AdapterTabLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Course extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        Toolbar toolbar = findViewById(R.id.toolbar);
        ViewPager2 viewPager2 = findViewById(R.id.viewPager2);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (actionBar != null) {
            actionBar.setTitle("Course");
        }

        //
        AdapterTabLayout adapterTabLayout = new AdapterTabLayout(Course.this);
        viewPager2.setAdapter(adapterTabLayout);

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            switch (position) {
                case 0: {
                    tab.setText("Student");
                    tab.setIcon(R.drawable.ic_baseline_person_24);
                    break;
                }
                case 1: {
                    tab.setText("Class");
                    tab.setIcon(R.drawable.ic_baseline_class_24);
                    break;
                }
                case 2: {
                    tab.setText("Course");
                    tab.setIcon(R.drawable.ic_baseline_fact_check_24);
                    break;
                }
            }
        }).attach();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}