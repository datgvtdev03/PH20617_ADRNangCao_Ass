package com.example.ph20617_mob201_assignment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.example.ph20617_mob201_assignment.Adapter.Adapter_GridView;
import com.example.ph20617_mob201_assignment.DTO.News;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    Toolbar toolbar;
    ActionBar actionBar;
    String[] name = {
            "Course",
            "News",
            "Social"
    };

    int[] img = {
            R.drawable.course,
            R.drawable.news,
            R.drawable.social
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = findViewById(R.id.gridView);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_home_24);
        }
        if (actionBar != null) {
            actionBar.setTitle("Hỗ trợ học tập");
        }

        Adapter_GridView adapter_gridView = new Adapter_GridView(getApplicationContext(), img, name);
        gridView.setAdapter(adapter_gridView);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            switch (position) {
                case 0: {
                    Intent in = new Intent(MainActivity.this, Course.class);
                    Toast.makeText(this, "Looi", Toast.LENGTH_SHORT).show();
                    startActivity(in);
                    break;
                }
                case 2: {
                    Intent in = new Intent(MainActivity.this, News1.class);
                    startActivity(in);
                    break;
                }
                case 3: {
                    Intent in = new Intent(MainActivity.this, Social.class);
                    startActivity(in);
                    break;
                }
                default: {
                    Toast.makeText(MainActivity.this, " ", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        });
    }
}