package com.example.ph20617_mob201_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class News2 extends AppCompatActivity {
    WebView web;
    String urlGGMaps = "https://www.google.com/maps/@9.779349,105.6189045,11z?hl=vi-VN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news2);
        web=findViewById(R.id.web);
        web.getSettings().setJavaScriptEnabled(true);
        web.setWebViewClient(new WebViewClient());
//        web.loadUrl(getIntent().getStringExtra("a"));
        web.loadUrl(urlGGMaps);

    }
}