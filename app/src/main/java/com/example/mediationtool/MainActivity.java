package com.example.mediationtool;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;

import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView mywebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mywebView=(WebView) findViewById(R.id.webview);
        //mywebView.setWebViewClient(new mywebClient());
        mywebView.setWebViewClient(new SSLTolerentWebViewClient());
        mywebView.loadUrl("https://raikou.laas.fr");
        WebSettings webSettings=mywebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    public class mywebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view,url,favicon);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

    }

    //SSL Error Tolerant Web View Client
    private class SSLTolerentWebViewClient extends mywebClient {
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error){
            handler.proceed(); //Ignore SSL certificate errors
        }
    }
    @Override
    public void onBackPressed() {
        if (mywebView.canGoBack()) {
            mywebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}