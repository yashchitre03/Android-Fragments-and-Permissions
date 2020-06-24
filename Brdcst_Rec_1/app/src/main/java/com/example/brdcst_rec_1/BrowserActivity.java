package com.example.brdcst_rec_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class BrowserActivity extends AppCompatActivity {

    // Array of web-links of respective phones
    String[] link = {"https://www.apple.com/shop/buy-iphone/iphone-11-pro",
            "https://www.lg.com/us/mobile-phones/g8-thinq",
            "https://www.samsung.com/us/mobile/galaxy-note10/",
            "https://www.oneplus.com/7pro#/",
            "https://store.google.com/product/pixel_3"};

    private WebView webview;

    // Creates Activityt 2 (Browser activity) and creates web-view in the same.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        Intent intent = getIntent();
        int position = intent.getIntExtra("number", -1);

        webview = findViewById(R.id.webView);

        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        webview.loadUrl(link[position]);
    }
}
