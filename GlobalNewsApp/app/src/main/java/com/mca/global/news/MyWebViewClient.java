package com.mca.global.news;

import android.app.ProgressDialog;
import android.content.Context;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewClient extends WebViewClient {

    Context context;
    MyWebViewClient(Context context){
        this.context=context;
    }
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        view.loadUrl(url);
        return true;
    }
}
