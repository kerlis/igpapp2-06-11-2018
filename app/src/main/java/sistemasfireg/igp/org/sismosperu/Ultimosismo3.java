package sistemasfireg.igp.org.sismosperu;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.StringTokenizer;
import me.leolin.shortcutbadger.ShortcutBadger;

public class Ultimosismo3 extends FragmentActivity implements OnMapReadyCallback {
    SupportMapFragment sMapFragment;
    Button mk;
    TextView text_hora;
    TextView text_intensidad;
    TextView text_ubicacion;
    TextView text_profundidad;
    TextView txtmagnitud;
    TextView txtubicacion;
    TextView texto;
    TextView iconocompartir;
    RelativeLayout textoencabezado;
    public Context c;
    public String fechadato;
    public String horadato;
    public String ubicacion;
    public String epicentro;
    public String latitud;
    public String longitud;
    public Double lati;
    public Double longit;
    private static String urlString;
    int mWidthScreen;
    int mHeightScreen;
    String magni,ml,nh;
    int tamano;
    Button satelite,terreno;
    private static final String TAG = Ultimosismo3.class.getSimpleName();
    private DatabaseReference mFirebaseDatabase;
    private DatabaseReference mDatabase;
    String dato_intensidad;
    String dato_hora;
    String dato_fecha;
    String dato_profundidad;
    String dato_epicentro;
    String dato_ubicacion;
    String magnitud2;
    GoogleMap googleMap;
    Button datox;
    String orden;
    String Message;
    String temp2;
    String s;
    String b;
    String k;
    String tipo;
    static final int READ_BLOCK_SIZE = 100;
    private static final String jsonurl= "http://arteypixel.com/envio_notificaciones/consultaposicion.php";

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




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onNewIntent(getIntent());




        Context context = this;
            String Message3 = "0";
            String file_name = "valorcero";
            try {
                FileOutputStream fileOutputStream = openFileOutput(file_name, MODE_PRIVATE);
                fileOutputStream.write(Message3.getBytes());
                fileOutputStream.close();
                //  Toast.makeText(getApplicationContext(), "Configurado", Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        ShortcutBadger.applyCount(context, 0); //for 1.1.4+














        String file2 = "datos_ordences";
        try {
            FileInputStream fileIn = openFileInput(file2);
            InputStreamReader InputRead= new InputStreamReader(fileIn);
            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            int charRead;
            charRead=InputRead.read(inputBuffer);
            String readstring=String.copyValueOf(inputBuffer,0,charRead);
            k +=readstring;
            InputRead.close();
           //  Toast.makeText(getBaseContext(),"data : " + k,Toast.LENGTH_SHORT).show();
            StringTokenizer st = new StringTokenizer(k.toString(), ",");
            String ajustes = st.nextToken();
            tipo = st.nextToken();

            Log.d(TAG, "valor: json: " + k);


            //Toast.makeText(getBaseContext(),"data 2 : " + tipo,Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }



      //  Toast.makeText(Ultimosismo3.this, k, Toast.LENGTH_LONG).show();

        //int badgeCount = 1;
      //  ShortcutBadger.applyCount(context, badgeCount); //for 1.1.4+
       // ShortcutBadger.with(getApplicationContext()).count(badgeCount); //for 1.1.3


        //ver que tipo de conexxion se esta utilizando
        ConnectivityManager cmanager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo info = cmanager.getActiveNetworkInfo();
        if(info!= null && info.isConnected()){
            if(info.getType() == ConnectivityManager.TYPE_WIFI) {
                Toast.makeText(Ultimosismo3.this, "Conexión Establecida", Toast.LENGTH_LONG).show();
            }
            else if(info.getType() == ConnectivityManager.TYPE_MOBILE){
                Toast.makeText(Ultimosismo3.this, "Conexión Establecida", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(Ultimosismo3.this, "Su Equipo ha Bloquedo la Conexión", Toast.LENGTH_LONG).show();
        }

        //verdato3();
        detecta_sismo();

        sMapFragment = SupportMapFragment.newInstance();
        FragmentManager sFm = getSupportFragmentManager();
        sFm.beginTransaction().add(R.id.mapx, sMapFragment).commit();
        sMapFragment.getMapAsync(this);
        setContentView(R.layout.activity_ultimosismo3);

        Typeface fontAwesomeFont = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        TextView ihoras = (TextView) findViewById(R.id.iconohora);
        TextView iubicaciones = (TextView) findViewById(R.id.iconoubicacion);
        ihoras.setTypeface(fontAwesomeFont);
        iubicaciones.setTypeface(fontAwesomeFont);
        satelite = (Button) findViewById(R.id.satelite);
        terreno = (Button) findViewById(R.id.terreno);
        datox = (Button) findViewById(R.id.localizacion);
        datox.setTypeface(fontAwesomeFont);
        text_hora = (TextView) findViewById(R.id.text_hora);
        text_intensidad = (TextView) findViewById(R.id.text_intensidad);
        text_ubicacion = (TextView) findViewById(R.id.text_ubicacion);
        text_profundidad = (TextView) findViewById(R.id.text_profundidad);
        txtmagnitud= (TextView) findViewById(R.id.grados);
        txtubicacion= (TextView) findViewById(R.id.txtubicacion);
        texto = (TextView) findViewById(R.id.texto);

        iconocompartir = (TextView) findViewById(R.id.iconocompartir);
        iconocompartir.setTypeface(fontAwesomeFont);
        iconocompartir.setBackgroundResource(R.drawable.circuloblanco);
        textoencabezado = (RelativeLayout) findViewById(R.id.textoencabezado);

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
                Intent intent = new Intent(Ultimosismo3.this,Ultimosismo3.class);
                startActivity(intent);
            }
        });
        blocke2a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Ultimosismo3.this,Ultimosismoslist.class);
                startActivity(intent);
            }
        });
        blocke3a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Ultimosismo3.this,Listadoenmapa.class);
                startActivity(intent);
            }
        });
        blocke4a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Ultimosismo3.this,Configuraciones.class);
                startActivity(intent);
            }
        });
        blocke5a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Ultimosismo3.this,Glosarioestatico.class);
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
                Intent intent = new Intent(Ultimosismo3.this,Listadoredessociales.class);
                startActivity(intent);
            }
        });



        FrameLayout frame1 = (FrameLayout) findViewById(R.id.mapx);
        Display display = ((WindowManager) this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        mWidthScreen = display.getWidth();
        mHeightScreen = display.getHeight();

        switch (getResources().getDisplayMetrics().densityDpi) {
            case DisplayMetrics.DENSITY_LOW:
                tamano = (mHeightScreen - 259);
                break;

            case DisplayMetrics.DENSITY_MEDIUM:
                tamano = (mHeightScreen - 345);
                break;

            case DisplayMetrics.DENSITY_TV:
                tamano = (mHeightScreen - 459);
                break;


            case DisplayMetrics.DENSITY_HIGH:
                tamano = (mHeightScreen - 518);
                break;

            case DisplayMetrics.DENSITY_XHIGH:
                tamano = (mHeightScreen - 690);
                break;


            case DisplayMetrics.DENSITY_XXHIGH:
                tamano = (mHeightScreen - 1035);
                break;

            case DisplayMetrics.DENSITY_XXXHIGH:
                tamano = (mHeightScreen - 1380);
                break;

        }

        frame1.getLayoutParams().height = tamano;
        frame1.requestLayout();
        mk= (Button) findViewById(R.id.button);
        mk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(Ultimosismo3.this, mk);
                popup.getMenuInflater().inflate(R.menu.menu_main, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    public boolean onMenuItemClick(MenuItem item) {

                        int id = item.getItemId();

                        if (id == R.id.ultimo_sismo) {
                            Intent intent = new Intent(Ultimosismo3.this,Listadoredessociales.class);
                            startActivity(intent);
                            return true;
                        }


                        if (id == R.id.listado_ultimos_sismos) {
                            Intent intent = new Intent(Ultimosismo3.this,Listadoredessociales.class);
                            startActivity(intent);
                            return true;
                        }

                        if (id == R.id.mapa_sismos) {
                            Intent intent = new Intent(Ultimosismo3.this,Listadoenmapa.class);
                            startActivity(intent);
                            return true;
                        }

                        if (id == R.id.glosario) {
                            Intent intent = new Intent(Ultimosismo3.this,Glosarioestatico.class);
                            startActivity(intent);
                            return true;
                        }

                        if (id == R.id.notificarsino) {
                            Intent intent = new Intent(Ultimosismo3.this,Configuraciones.class);
                            startActivity(intent);
                            return true;
                        }

                        if (id == R.id.salir) {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                        return Ultimosismo3.super.onOptionsItemSelected(item);
                    }
                });
                popup.show();
            }
        });
        mostrar_ult_sismo();
    }



    /*
        @Override
        public void onNewIntent(Intent intent){
            Bundle extras = intent.getExtras();
            if(extras != null){
                if(extras.containsKey("NotificationMessage"))
                {
                    String latitudk = extras.getString("NotificationMessage");
                  //  Toast.makeText(Ultimosismo3.this,"k : "+latitudk, Toast.LENGTH_LONG).show();
                }
                else{
                 //   Toast.makeText(Ultimosismo3.this,"no hay datos ", Toast.LENGTH_LG).show();
                }
            }
        }

    /*
        public void verdato3() {
            String file = "datos_configuracion";

            try {
                FileInputStream fin = openFileInput(file);
                int c;
                String temp="";
                while( (c = fin.read()) != -1){
                    temp = temp + Character.toString((char)c);
                }
                // tv.setText(temp);
              //  Toast.makeText(getBaseContext(),temp,Toast.LENGTH_SHORT).show();
            }
            catch(Exception e){
            }
        }
    */

    void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
    }

    public void detecta_sismo() {


        /* instanci de la base de datos
        String file2 = "datos_ordences";
        try {
            FileInputStream fileIn = openFileInput(file2);
            InputStreamReader InputRead= new InputStreamReader(fileIn);
            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            int charRead;
            charRead=InputRead.read(inputBuffer);
            String readstring=String.copyValueOf(inputBuffer,0,charRead);
            k +=readstring;
            InputRead.close();
            // Toast.makeText(getBaseContext(),"data : " + k,Toast.LENGTH_SHORT).show();
            StringTokenizer st = new StringTokenizer(k.toString(), ",");
            String ajustes = st.nextToken();
            tipo = st.nextToken();
            // Toast.makeText(getBaseContext(),"data 2 : " + tipo,Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }



        if(tipo == null){
            reload();
        }
        else{
        }
        */



        /*
        DatabaseReference mDatabase;
        if (tipo == null){
            mDatabase = FirebaseDatabase.getInstance("https://igpsismos2.firebaseio.com/").getReference("messages");
        }
        else{
            mDatabase = FirebaseDatabase.getInstance(tipo).getReference("messages");
        }
        */



        DatabaseReference mDatabase;
        //if (tipo == null){
        mDatabase = FirebaseDatabase.getInstance("https://igpsismos2.firebaseio.com/").getReference("messages");
        //}
        //else{
        //mDatabase = FirebaseDatabase.getInstance(tipo).getReference("messages");
        //}



        //  Toast.makeText(getBaseContext(), tipo,Toast.LENGTH_SHORT).show();
        //  Toast.makeText(getBaseContext(), "no refresh",Toast.LENGTH_SHORT).show();
        //   Toast.makeText(getBaseContext(), "red normal",Toast.LENGTH_SHORT).show();
        //  Toast.makeText(getBaseContext(), tipo,Toast.LENGTH_SHORT).show();

        mDatabase.keepSynced(true);
        mDatabase.orderByKey().limitToLast(1).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                DatSismo sreporte = dataSnapshot.getValue(DatSismo.class);
                guardar_pref(sreporte);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                DatSismo sreporte = dataSnapshot.getValue(DatSismo.class);
                guardar_pref(sreporte);
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                DatSismo sreporte = dataSnapshot.getValue(DatSismo.class);
                guardar_pref(sreporte);
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                DatSismo sreporte = dataSnapshot.getValue(DatSismo.class);
                guardar_pref(sreporte);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void guardar_pref(DatSismo sreporte) {
        SharedPreferences prefs = getSharedPreferences("ultsismo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("incategoria", sreporte.getCategoria());
        editor.putString("inreferencia", sreporte.getReferencia());
        editor.putString("inmagnitud", sreporte.getMagnitud());
        editor.putString("inprof", sreporte.getProfundidad());
        editor.putString("infecha", sreporte.getFechautc());
        editor.putString("txintenso", sreporte.getIntenso());
        editor.putString("inhora", sreporte.getHorautc());
        editor.putString("inlat", sreporte.getLat());
        editor.putString("inlong", sreporte.getLon());
        editor.putString("inepic", sreporte.getEpicentro());
        editor.putString("insim", sreporte.getSimulacro());
        editor.apply();
        mostrar_ult_sismo();
    }

    public void mostrar_ult_sismo() {
        SharedPreferences prefs = getSharedPreferences("ultsismo", Context.MODE_PRIVATE);
        String intensidad = prefs.getString("txintenso", "");
        String hora = prefs.getString("inhora", "");
        String fecha = prefs.getString("infecha", "");
        String simulacro  = prefs.getString("insim", "");
        String profundidad = prefs.getString("inprof","");
        fechadato= prefs.getString("infecha","0");
        horadato = prefs.getString("inhora","0");
        latitud = prefs.getString("inlat","0");
        longitud = prefs.getString("inlong","0");
        epicentro = prefs.getString("inepic","");
        magni = prefs.getString("inmagnitud","");
        ubicacion = prefs.getString("inreferencia","Aqui");
        iconocompartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Reporte de Último Sismo Perú" +  "\n";
                String shareSub = "Reporte de Último Sismo Perú" +  "\n" + "Fecha Local: " + fechadato + "  " + horadato +  "\n Magnitud: " + magni + "\n" +  "Latitud: " + latitud + " " + "Longitud: " + longitud + "\n" + "Referencia: " + ubicacion + "\n"  +   "\n"  ;
                //String shareSub2 = "http://ultimosismo.igp.gob.pe/";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareBody);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareSub);
                // sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareSub2);
                startActivity(Intent.createChooser(sharingIntent, "compartir último sismo"));
            }
        });

        //evaluar dato intensidad
        if (intensidad.length() == 0){
            dato_intensidad = "- -";
        }
        else{
            dato_intensidad = intensidad;
        }
        text_intensidad.setText(dato_intensidad);

        //evaluar dato epicentro
        if (epicentro.length() == 0){
            dato_epicentro = "- -";
        }
        else{
            dato_epicentro = epicentro;
        }

        //evaluar dato hora
        if (hora.length() == 0){
            dato_hora = "- -";
        }
        else{
            dato_hora = hora;
        }

        //evaluar dato fecha
        if (fecha.length() == 0){
            dato_fecha = "- -";
        }
        else{
            dato_fecha = fecha;
        }
        text_hora.setText(dato_fecha+"   "+dato_hora);


        //evaluar dato profundidad
        if (profundidad.length() == 0){
            dato_profundidad= "- -";
        }
        else{
            dato_profundidad = profundidad;
        }
        text_profundidad.setText(dato_profundidad+""+" km");


        //evaluar dato magnitud
        double w;

        try {
            w = new Double(magni);
        } catch (NumberFormatException e) {
            w = 0;
        }

        if (w == 0){
            magnitud2 = "0";
        }
        else
        {
            magnitud2 = magni;
        }

        Double val = Double.parseDouble(magnitud2);
        txtmagnitud.setText(magnitud2);

        if (val >= 0 && val < 4.5){
            txtmagnitud.setBackgroundResource(R.drawable.circuloverde);
            textoencabezado.setBackgroundColor(getResources().getColor(R.color.verdeigp));
        }

        else if (val >= 4.5 && val <= 6.0){
            txtmagnitud.setBackgroundResource(R.drawable.circuloamarillo);
            textoencabezado.setBackgroundColor(getResources().getColor(R.color.orangeyellow));
        }

        else if (val > 6.0 && val <= 15){
            txtmagnitud.setBackgroundResource(R.drawable.circulorojo);
            textoencabezado.setBackgroundColor(getResources().getColor(R.color.rojoigp));
        }

        else  {
            txtmagnitud.setBackgroundResource(R.drawable.circulorojo);
            textoencabezado.setBackgroundColor(getResources().getColor(R.color.rojoigp));
        }


        //evaluar dato ubicacion
        if (ubicacion.length() == 0){
            dato_ubicacion = "null";
        }
        else{
            dato_ubicacion = ubicacion;
        }

        String z = dato_ubicacion.replace("-","\n");
        txtubicacion.setText(z);


        //evaluar dato simulacro
        int g;

        try {
            g = new Integer(simulacro);
        } catch (NumberFormatException e) {
            g = 0;
        }



        if (g == 0){
            texto.setText("Último Sismo");
        }
        else if (g == 1){
            texto.setText("Simulacro");
        }
        else if (g == 2){
            texto.setText("Simulación");
        }
        else{
            texto.setText("Último Sismo");
        }

/*
        if (g > 0){
            texto.setText("Simulacro");
        }
        else if (g < 1){
            texto.setText("Último sismo");
        }
        else{
            texto.setText("Último sismo");
        }

      */


        lati= Double.parseDouble(latitud);
        longit = Double.parseDouble(longitud);
        sMapFragment.getMapAsync(this);
        DecimalFormat form = new DecimalFormat("0.00");
        text_ubicacion.setText(form.format(lati)+"  "+" "+form.format(longit));
    }

    public void onMapReady(final GoogleMap googleMap) {
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.iconored);
        LatLng latLng = new LatLng(lati,longit);
        if (latLng != null){
            googleMap.clear();
        }
        googleMap.addMarker(new MarkerOptions().position(latLng).title("Epicentro").icon(icon).anchor(0.5f, 0.5f)).showInfoWindow();
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 6));
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(6), 1500, null);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setIndoorLevelPickerEnabled(true);
        googleMap.getUiSettings().setTiltGesturesEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);

        satelite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        });

        terreno.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            }
        });

        datox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                // map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lati, longit), 6));
            }
        });


    }



    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }
    public void onClick (View v){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}