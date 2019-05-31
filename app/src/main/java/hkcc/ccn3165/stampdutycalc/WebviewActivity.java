package hkcc.ccn3165.stampdutycalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.development.R;

public class WebviewActivity extends AppCompatActivity {
    // Declare a WebView object
    private WebView webView;

    // Define the URL
    private String url = "https://www.gov.hk/tc/residents/taxes/stamp/stamp_duty_rates.htm";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        // Enable JavaScript Feature and Load the URL
        webView = (WebView) findViewById(R.id.wvContent);
        // force open webview in app
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }
}
