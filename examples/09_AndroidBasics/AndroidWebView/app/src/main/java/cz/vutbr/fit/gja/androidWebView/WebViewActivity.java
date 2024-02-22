package cz.vutbr.fit.gja.androidWebView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.webkit.WebView;
 
public class WebViewActivity extends Activity {
 
  private WebView webView;
 
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.google);
 
    webView = (WebView) findViewById(R.id.webView1);
    webView.getSettings().setJavaScriptEnabled(true);
    webView.loadUrl("https://www.google.com");
 
  }
 
}