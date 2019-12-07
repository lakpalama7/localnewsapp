package com.mca.global.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class NewsDetailsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private WebView webView;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        webView=findViewById(R.id.webview);

        Intent intent=getIntent();
        String url=null;
        if(intent!=null){
            getSupportActionBar().setTitle(intent.getStringExtra("title"));
            url=intent.getStringExtra("url");
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*dialog=new ProgressDialog(NewsDetailsActivity.this);
        dialog.setMessage("Loading page...");
        dialog.show();*/
        progressBar=findViewById(R.id.progressbar);
        loadOnWebView(url);
    }

    private void loadOnWebView(String url) {

        webView=findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebViewClient(NewsDetailsActivity.this));
        webView.loadUrl(url);
        //progressBar.setVisibility(View.GONE);
       // dialog.dismiss();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
