package olimpiadas.sena.com.olimpiadasmath.activities.library;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import olimpiadas.sena.com.olimpiadasmath.R;

public class EquationsActivity extends AppCompatActivity {

    WebView webViewEquations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equations);
        getSupportActionBar().hide();

        webViewEquations = (WebView) findViewById(R.id.webview_equations);

        webViewEquations.setWebViewClient(new WebViewClient());
        webViewEquations.loadUrl("http://www.ematematicas.net/ecuacion.php");
    }
}