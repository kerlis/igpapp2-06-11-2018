package sistemasfireg.igp.org.sismosperu;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Contenedorredsocial extends FragmentActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    RelativeLayout informacion;
    RelativeLayout ajustes;
    RelativeLayout compartir;
    RelativeLayout convenciones;
    TextView titulovolcanlabel;
    Button mk;
    WebView webView;
    String valor;
    String valor2;
    ImageView sliderz;
    private CharSequence mTitle;
    private DrawerLayout mDrawerLayout;
    private CharSequence mDrawerTitle;
    private String[] mNavigationDrawerItemTitles;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    private RelativeLayout mDrawerBlock;
    Toolbar toolbar;
    RelativeLayout blocke1a;
    RelativeLayout blocke2a;
    RelativeLayout blocke3a;
    RelativeLayout blocke4a;
    RelativeLayout blocke5a;
    RelativeLayout blocke6a;
    RelativeLayout blocke9;
    TextView nombreredsocial;
    private ProgressBar spinner;
    String ShowOrHideWebViewInitialUse = "show";
    @SuppressLint({"WrongViewCast", "SetJavaScriptEnabled"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  requestWindowFeature(Window.FEATURE_NO_TITLE);
        //  getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.window_title);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.conetenedorred);


        sliderz = (ImageView) findViewById(R.id.sliderz);
        mTitle = mDrawerTitle = getTitle();
        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerBlock = (RelativeLayout) findViewById(R.id.mDrawerBlock);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();
        sliderz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        blocke1a = (RelativeLayout) findViewById(R.id.blocke1);
        blocke2a = (RelativeLayout) findViewById(R.id.blocke2);
        blocke3a = (RelativeLayout) findViewById(R.id.blocke3);
        blocke4a = (RelativeLayout) findViewById(R.id.blocke4);
        blocke5a = (RelativeLayout) findViewById(R.id.blocke5);
        blocke6a = (RelativeLayout) findViewById(R.id.blocke6);
        blocke9 = (RelativeLayout) findViewById(R.id.blocke9);

        blocke1a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Contenedorredsocial.this,Ultimosismo3.class);
                startActivity(intent);
            }
        });
        blocke2a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Contenedorredsocial.this,Ultimosismoslist.class);
                startActivity(intent);
            }
        });
        blocke3a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Contenedorredsocial.this,Listadoenmapa.class);
                startActivity(intent);
            }
        });
        blocke4a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Contenedorredsocial.this,Configuraciones.class);
                startActivity(intent);
            }
        });
        blocke5a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Contenedorredsocial.this,Glosarioestatico.class);
                startActivity(intent);
            }
        });
        blocke6a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
        blocke9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Contenedorredsocial.this,Listadoredessociales.class);
                startActivity(intent);
            }
        });



        nombreredsocial = (TextView) findViewById(R.id.texto);


        Intent i = this.getIntent();
        valor = i.getExtras().getString("ACTIVIDAD_RECIENTE");
        valor2 = i.getExtras().getString("NOMBRE");

        nombreredsocial.setText(valor2);

        webView = (WebView)findViewById(R.id.contenedor);


        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        webView.setWebViewClient(new CustomWebViewClient());

        //webView.setWebViewClient(new WebViewClient());


        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setOverScrollMode(WebView.OVER_SCROLL_NEVER);

      //  webSettings.setJavaScriptEnabled(true);
      //  webView.getSettings().setBuiltInZoomControls(true);
      //  webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);


/*
        WebSettings webSettings = webView.getSettings();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(valor);
*/






        // Let's display the progress in the activity title bar, like the
        // browser app does.
//        getWindow().requestFeature(Window.FEATURE_PROGRESS);


      // getWindow().requestFeature(Window.FEATURE_PROGRESS);







/*
        webView.getSettings().setJavaScriptEnabled(true);

        final Activity activity = this;
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                // Activities and WebViews measure progress with different scales.
                // The progress meter will automatically disappear when we reach 100%
                activity.setProgress(progress * 1000);
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, "Oh no! " + description, Toast.LENGTH_SHORT).show();
            }
        });
*/

        webView.loadUrl(valor);


















      //  webView.loadUrl("https://developer.android.com/");

    }

    private class CustomWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView webview, String url, Bitmap favicon) {

            // only make it invisible the FIRST time the app is run
            if (ShowOrHideWebViewInitialUse.equals("show")) {
                webview.setVisibility(webview.INVISIBLE);
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            ShowOrHideWebViewInitialUse = "hide";
            spinner.setVisibility(View.GONE);

            view.setVisibility(webView.VISIBLE);
            super.onPageFinished(view, url);

        }
    }






    void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
    }


 }

