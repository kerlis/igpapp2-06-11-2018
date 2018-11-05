package sistemasfireg.igp.org.sismosperu;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringTokenizer;

import me.leolin.shortcutbadger.ShortcutBadger;
import sistemasfireg.igp.org.sismosperu.m_model.messages;
public class Listadoenmapa extends FragmentActivity implements OnMapReadyCallback,LocationListener,ActivityCompat.OnRequestPermissionsResultCallback
{
    SupportMapFragment sMapFragment;
    Button mk;
    String categoria,epicentro,fechautc,horautc,intenso,lat,lon,magnitud,profundidad,referencia,simulacro,tiporeporte,magnitud2,magnitud3;
    private LocationManager lm;
    private Location location;
    String valorprofundidad;
    String valorprofundidadcambio;
    TextView texto;
    private double longitudex = -75.816650;
    private double latitudex = -11.544259;
    private FirebaseDatabase database;
    private static final int REQUEST_PERMISSION = 1;
    private DatabaseReference mFirebaseDatabase;
    private GoogleMap map;
    Button satelite,terreno,localizacion;
    Button datox;
    String refe,magni;
    private static String[] PERMISSIONS = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
    String ultimalatitud;
    String ultimalongitud;
    Double ultimalatituddouble;
    Double ultimalongituddouble;
    String datokkk;
    String lati2;
    String ultlati2;
    int total;
    int i = 0;
    String cd;
    String latitudeva;
    String latitudcambio;
    String ultimalatitudcambio;
    TextView profundidadeskm;
    String k;
    String tipo;
    static final int READ_BLOCK_SIZE = 100;


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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listadoenmapa);

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

        ConnectivityManager cmanager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo info = cmanager.getActiveNetworkInfo();
        if(info!= null && info.isConnected()){
            if(info.getType() == ConnectivityManager.TYPE_WIFI) {
                Toast.makeText(Listadoenmapa.this, "Conexión Establecida", Toast.LENGTH_LONG).show();
                //Toast.makeText(Listadoenmapa.this, "TIPO DE CONEXIÓN : WIFI", Toast.LENGTH_LONG).show();
            }
            else if(info.getType() == ConnectivityManager.TYPE_MOBILE){
                Toast.makeText(Listadoenmapa.this, "Conexión Establecida", Toast.LENGTH_LONG).show();
                // Toast.makeText(Listadoenmapa.this, "TIPO DE CONEXIÓN :MOBILE", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(Listadoenmapa.this, "Su Equipo ha Bloquedo la Conexión", Toast.LENGTH_LONG).show();
        }

        database = FirebaseDatabase.getInstance();
        sMapFragment = SupportMapFragment.newInstance();
        FragmentManager sFm = getSupportFragmentManager();
        sFm.beginTransaction().add(R.id.map, sMapFragment).commit();
        sMapFragment.getMapAsync(this);
        satelite = (Button) findViewById(R.id.satelite);
        terreno = (Button) findViewById(R.id.terreno);
        Typeface fontAwesomeFont = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        datox = (Button) findViewById(R.id.localizacion);
        datox.setTypeface(fontAwesomeFont);
        profundidadeskm = (TextView) findViewById(R.id.profundidadeskm);
        profundidadeskm.setText(Html.fromHtml("<u>Profundidades km</u>"));
        detecta_sismo();
        initMaps();




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
                Intent intent = new Intent(Listadoenmapa.this,Ultimosismo3.class);
                startActivity(intent);
            }
        });
        blocke2a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Listadoenmapa.this,Ultimosismoslist.class);
                startActivity(intent);
            }
        });
        blocke3a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Listadoenmapa.this,Listadoenmapa.class);
                startActivity(intent);
            }
        });
        blocke4a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Listadoenmapa.this,Configuraciones.class);
                startActivity(intent);
            }
        });
        blocke5a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Listadoenmapa.this,Glosarioestatico.class);
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
                Intent intent = new Intent(Listadoenmapa.this,Listadoredessociales.class);
                startActivity(intent);
            }
        });





        mk= (Button) findViewById(R.id.button);
        mk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(Listadoenmapa.this, mk);
                popup.getMenuInflater().inflate(R.menu.menu_main, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        int id = item.getItemId();
                        if (id == R.id.ultimo_sismo) {
                            Intent intent = new Intent(Listadoenmapa.this,Ultimosismo3.class);
                            startActivity(intent);
                            return true;
                        }
                        if (id == R.id.listado_ultimos_sismos) {
                            Intent intent = new Intent(Listadoenmapa.this,Ultimosismoslist.class);
                            startActivity(intent);
                            return true;
                        }
                        if (id == R.id.mapa_sismos) {
                            Intent intent = new Intent(Listadoenmapa.this,Listadoenmapa.class);
                            startActivity(intent);
                            return true;
                        }

                        if (id == R.id.glosario) {
                            Intent intent = new Intent(Listadoenmapa.this,Glosarioestatico.class);
                            startActivity(intent);
                            return true;
                        }

                        if (id == R.id.notificarsino) {
                            Intent intent = new Intent(Listadoenmapa.this,Configuraciones.class);
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
                        return Listadoenmapa.super.onOptionsItemSelected(item);
                    }
                });
                popup.show();
            }
        });
        texto = (TextView) findViewById(R.id.texto);
        mostrar_ult_sismo();
    }



    void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
    }


    public void detecta_sismo() {
        /*
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
            //  Toast.makeText(getBaseContext(),"data 2 : " + tipo,Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }


        DatabaseReference mFirebaseDatabase;
        if (tipo == null){
            mFirebaseDatabase = FirebaseDatabase.getInstance("https://igpsismos2.firebaseio.com/").getReference("messages");
        }
        else{
            mFirebaseDatabase = FirebaseDatabase.getInstance(tipo).getReference("messages");
        }
        */

        DatabaseReference mFirebaseDatabase;
        mFirebaseDatabase = FirebaseDatabase.getInstance("https://igpsismos2.firebaseio.com/").getReference("messages");



        //  Toast.makeText(getBaseContext(), "red normal",Toast.LENGTH_SHORT).show();
        // Toast.makeText(getBaseContext(), "rbase",Toast.LENGTH_SHORT).show();

        /*
        if (mFirebaseDatabase == null) {
            FirebaseDatabase.getInstance();
            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            mFirebaseDatabase = database.getReference("messages");
        }
        else{
            FirebaseDatabase.getInstance();
            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            mFirebaseDatabase = database.getReference("messages");
        }
        */
        mFirebaseDatabase.keepSynced(true);
        mFirebaseDatabase.orderByKey().limitToLast(1).addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                DatSismo sreporte = dataSnapshot.getValue(DatSismo.class);
                datokkk = dataSnapshot.getKey();
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
        // String valorx  = sreporte.getMagnitud();
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
        ultimalatitud = prefs.getString("inlat","0");
        ultimalongitud = prefs.getString("inlong","0");
        epicentro = prefs.getString("inepic","");
        magni = prefs.getString("inmagnitud","");
        ultlati2 = prefs.getString("inlat","0");
        ultimalatituddouble= Double.parseDouble(ultimalatitud);
        ultimalongituddouble = Double.parseDouble(ultimalongitud);
        sMapFragment.getMapAsync(this);
    }

    public void initMaps(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions();
        } else {
            lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 60000, this);
        }
    }

    @Override
    public void onMapReady(final GoogleMap map) {
        this.map = map;

       /* if (lm != null) {
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            }
        }
        */

        LatLng latLng = new LatLng(ultimalatituddouble,ultimalongituddouble);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
        }
        map.setTrafficEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitudex, longitudex), 5));
        map.animateCamera(CameraUpdateFactory.zoomTo(5), 2000, null);
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.getUiSettings().setIndoorLevelPickerEnabled(true);
        map.getUiSettings().setTiltGesturesEnabled(true);
        map.getUiSettings().setCompassEnabled(true);

        satelite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        });

        terreno.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            }
        });

        datox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitudex, longitudex), 5));
            }
        });
        loadMarker();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onLocationChanged(Location arg0) {

    }

    @Override
    public void onProviderDisabled(String arg0) {

    }

    @Override
    public void onProviderEnabled(String arg0) {

    }

    @Override
    public void onStatusChanged(String arg0, int arg1, Bundle arg2) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,"Autorizado",Toast.LENGTH_SHORT).show();
                    initMaps();

                } else {
                    Toast.makeText(this,"Permissão negada",Toast.LENGTH_SHORT).show();
                }
                return;
            }

        }
    }

    private void requestPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                || ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            Toast.makeText(this,"Permissão negada",Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(this,PERMISSIONS , REQUEST_PERMISSION);
        }
    }

    public void loadMarker(){

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
            //  Toast.makeText(getBaseContext(),"data 2 : " + tipo,Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }


        DatabaseReference locais;
        if (tipo == null){
            locais = FirebaseDatabase.getInstance("https://igpsismos2.firebaseio.com/").getReference("messages");
          //  Toast.makeText(getBaseContext(), "red normal",Toast.LENGTH_SHORT).show();
        }
        else{
            locais = FirebaseDatabase.getInstance(tipo).getReference("messages");
          //  Toast.makeText(getBaseContext(), "rbase",Toast.LENGTH_SHORT).show();
        }

       // final DatabaseReference locais = database.getReference("messages");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String outputDate = simpleDateFormat.format(calendar.getTime());
        String mytime = (DateFormat.format("dd/mm/YYYY", new java.util.Date()).toString());
        // locais.orderByChild("fechautc").startAt(outputDate).endAt(mytime).addValueEventListener(new ValueEventListener()
        locais.orderByKey().limitToLast(20).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                final Iterable<DataSnapshot> dataSnapshots = dataSnapshot.getChildren();
                map.clear();
                total = (int) dataSnapshot.getChildrenCount();
                // let's say userB is actually 2nd in the list
                i = 0;

                for (final DataSnapshot dataSnapshot1 : dataSnapshots) {
                    final messages local = dataSnapshot1.getValue(messages.class);
                    cd = String.valueOf(i);
                    final Double lati,longi;
                    refe = local.getReferencia();
                    magnitud3 = local.getMagnitud();
                    valorprofundidad = local.getProfundidad();
                    latitudeva = local.getLat();
                    String lati3 = local.getLat();
                    lati = Double.parseDouble(local.getLat());
                    lati2 = local.getLat();
                    longi = Double.parseDouble(local.getLon());
                    //evaluar dato magnitud
                    magni = magnitud3;
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
                    //evaluar profundidad
                    double p;
                    try {
                        p = new Double(valorprofundidad);
                    } catch (NumberFormatException e) {
                        p = 0;
                    }
                    if (p == 0){
                        valorprofundidadcambio = "0";
                    }
                    else
                    {
                        valorprofundidadcambio = valorprofundidad;
                    }

                    //evaluar profundidad
                    double l;
                    try {
                        l = new Double(lati3);
                    } catch (NumberFormatException e) {
                        l = 0;
                    }
                    // String latitudcambio;

                    if (l == 0){
                        latitudcambio = "0";
                    }
                    else
                    {
                        latitudcambio = lati2;
                    }


                    //evaluar ultima latitud
                    double m;
                    try {
                        m = new Double(ultimalatitud);
                    } catch (NumberFormatException e) {
                        m = 0;
                    }
                    // String latitudcambio;

                    if (m == 0){
                        ultimalatitudcambio = "0";
                    }
                    else
                    {
                        ultimalatitudcambio = ultlati2;
                    }


                    final Double magnitude = Double.parseDouble(magnitud2);
                    final Double profundidadd =  Double.parseDouble(valorprofundidadcambio);

                    final Double latitudd =  Double.parseDouble(latitudcambio);

                    final Double ultimalatitudd =  Double.parseDouble(ultimalatitudcambio);

                    String lac = local.getLat();

                    final BitmapDescriptor icon;

                    ultimalatituddouble = Double.parseDouble(ultimalatitud);

                    if (latitudd == Double.parseDouble(String.valueOf(ultimalatitudd))){
                        icon = BitmapDescriptorFactory.fromResource(R.drawable.puntoamarillo);
                    }

                    else if (latitudd != Double.parseDouble(String.valueOf(ultimalatitudd)) && profundidadd > 0 && profundidadd <= 60){
                        icon = BitmapDescriptorFactory.fromResource(R.drawable.puntorojo);
                    }
                    else if (latitudd != Double.parseDouble(String.valueOf(ultimalatitudd)) && profundidadd > 60 && profundidadd <= 350) {
                        icon = BitmapDescriptorFactory.fromResource(R.drawable.puntoverde);
                    }
                    else if (latitudd != Double.parseDouble(String.valueOf(ultimalatitudd)) && profundidadd > 500) {
                        icon = BitmapDescriptorFactory.fromResource(R.drawable.iconoazul);
                    }
                    else {
                        icon = BitmapDescriptorFactory.fromResource(R.drawable.puntorojo);
                    }

                    Log.d("myTag", latitudd + "-xx-" + String.valueOf(ultimalatitudd));
                  //  final String uid = locais.child("messages").push().getKey();

                    map.addMarker(new MarkerOptions().position(new LatLng(lati, longi)).title("Epicentro").snippet(local.getHorautc()).icon(icon).anchor(0.5f, 0.5f)).showInfoWindow();
                    map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                        // DatabaseReference mref;

                        public void onInfoWindowClick(Marker marker) {
                            String de3 = marker.getSnippet();




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
                                //  Toast.makeText(getBaseContext(),"data 2 : " + tipo,Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                            DatabaseReference locais;
                            if (tipo == null){
                                locais = FirebaseDatabase.getInstance("https://igpsismos2.firebaseio.com/").getReference("messages");
                             //   Toast.makeText(getBaseContext(), "red normal",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                locais = FirebaseDatabase.getInstance(tipo).getReference("messages");
                            //    Toast.makeText(getBaseContext(), "rbase",Toast.LENGTH_SHORT).show();
                            }


                         //   mFirebaseDatabase = database.getReference("messages");



                            locais.orderByChild("horautc").equalTo(de3).addChildEventListener(new ChildEventListener() {

                                @Override
                                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                    DatSismo sreporte = dataSnapshot.getValue(DatSismo.class);
                                    guardar_pref(sreporte);
                                }
                                @Override
                                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                                }
                                @Override
                                public void onChildRemoved(DataSnapshot dataSnapshot) {
                                }
                                @Override
                                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                                }
                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                }


                                public void guardar_pref(DatSismo sreporte) {
                                    SharedPreferences prefs = getSharedPreferences("ultsismo", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = prefs.edit();
                                    editor.putString("incategoria", sreporte.getCategoria());
                                    editor.putString("inreferencia", sreporte.getReferencia());
                                    editor.putString("inmagnitud", sreporte.getMagnitud());
                                    // String valorx  = sreporte.getMagnitud();
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
                                    // String categoria,epicentro,fechautc,horautc,intenso,lat,lon,magnitud,profundidad,referencia,simulacro,tiporeporte;
                                    SharedPreferences prefs = getSharedPreferences("ultsismo", Context.MODE_PRIVATE);
                                    categoria = prefs.getString("incategoria", "");
                                    epicentro = prefs.getString("inepic", "");
                                    fechautc = prefs.getString("infecha", "");
                                    horautc = prefs.getString("inhora", "");
                                    intenso = prefs.getString("txintenso", "");
                                    lat = prefs.getString("inlat", "");
                                    lon = prefs.getString("inlong", "");
                                    magnitud = prefs.getString("inmagnitud", "");
                                    profundidad = prefs.getString("inprof", "");
                                    referencia = prefs.getString("inreferencia", "");
                                    simulacro = prefs.getString("insim", "");

                                    String dato_referencia;
                                    String dato_fechautc;
                                    String dato_horautc;
                                    String dato_profundidad;
                                    String dato_intenso;
                                    String dato_lat;
                                    String dato_lon;
                                    String dato_magnitud;

                                    if (magnitud.length() == 0){
                                        dato_magnitud ="- -";
                                    }
                                    else{
                                        dato_magnitud = magnitud;
                                    }

                                    if (referencia.length() == 0){
                                        dato_referencia ="- -";
                                    }
                                    else{
                                        dato_referencia = referencia;
                                    }

                                    if (fechautc.length() == 0){
                                        dato_fechautc ="- -";
                                    }
                                    else{
                                        dato_fechautc = fechautc;
                                    }

                                    if (horautc.length() == 0){
                                        dato_horautc ="- -";
                                    }
                                    else{
                                        dato_horautc = horautc;
                                    }

                                    if (profundidad.length() == 0){
                                        dato_profundidad ="- -";
                                    }
                                    else{
                                        dato_profundidad = profundidad;
                                    }

                                    if (intenso.length() == 0){
                                        dato_intenso ="- -";
                                    }
                                    else{
                                        dato_intenso = intenso;
                                    }

                                    if (lat.length() == 0){
                                        dato_lat ="- -";
                                    }
                                    else{
                                        dato_lat = lat;
                                    }

                                    if (lon.length() == 0){
                                        dato_lon ="- -";
                                    }
                                    else{
                                        dato_lon = lon;
                                    }

                                    AlertDialog.Builder builder = new AlertDialog.Builder(Listadoenmapa.this);
                                    builder.setTitle("Epicentro");
                                    builder.setMessage("Referencia : "+ dato_referencia+ "\n" +
                                            "Fecha local : " + dato_fechautc +"\n" +
                                            "Hora local : " + dato_horautc + "\n" +
                                            "Profundidad : " + dato_profundidad+" km"+ "\n" +
                                            "Intensidad : " + dato_intenso+ "\n" +
                                            "Magnitud : " + dato_magnitud+ "\n" +
                                            "Ubicación : " + dato_lat+ ", " + dato_lon);
                                    builder.setCancelable(true);
                                    builder.create().show();
                                }
                            });
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }
}