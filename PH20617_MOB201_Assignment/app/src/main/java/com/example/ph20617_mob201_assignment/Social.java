package com.example.ph20617_mob201_assignment;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class Social extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        webView = findViewById(R.id.webview_fb);

        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//auto javascript
        webView.getSettings().setBuiltInZoomControls(true);//ho tro room
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());//dung chrome
        webView.setWebViewClient(new MyWebView());
        webView.loadUrl("https://www.facebook.com/");

    }
    public class MyWebView extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

    }
}