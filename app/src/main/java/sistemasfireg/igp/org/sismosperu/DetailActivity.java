package sistemasfireg.igp.org.sismosperu;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
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
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import me.leolin.shortcutbadger.ShortcutBadger;

public class DetailActivity extends FragmentActivity implements OnMapReadyCallback {
    SupportMapFragment sMapFragment;
    int horas_resta,horas,minutos,segundos;
    String referencia,latitud,longitud,epicentro,fecha,hora,intensidad,caden,uno,dos,tres,profundidad,magnitud,magni,magnitud2;
    Double lati,longi;
    TextView latitud_txt,longitud_txt,profundidad_txt, magnitud_txt,imax_txt,fechahora_txt,ubicacion_txt,asignacion;
    RelativeLayout encabezado_titulos;
    Button mk;
    String dato_fecha,dato_hora;
    Button satelite,terreno,localizacion;
    String simulacro;
    Button datox;
    GoogleMap map;
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
        setContentView(R.layout.activity_detail);

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
                Toast.makeText(DetailActivity.this, "Conexión Establecida", Toast.LENGTH_LONG).show();
            }
            else if(info.getType() == ConnectivityManager.TYPE_MOBILE){
                Toast.makeText(DetailActivity.this, "Conexión Establecida", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(DetailActivity.this, "Su Equipo ha Bloquedo la Conexión", Toast.LENGTH_LONG).show();
        }

       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        sMapFragment = SupportMapFragment.newInstance();
        FragmentManager sFm = getSupportFragmentManager();
        sFm.beginTransaction().add(R.id.map, sMapFragment).commit();
        sMapFragment.getMapAsync(this);

        latitud_txt = (TextView) findViewById(R.id.latitud);
        longitud_txt = (TextView) findViewById(R.id.longitud);
        profundidad_txt = (TextView) findViewById(R.id.profundidad);
        magnitud_txt = (TextView) findViewById(R.id.magnitud);
        imax_txt = (TextView) findViewById(R.id.imax);
        fechahora_txt = (TextView) findViewById(R.id.fechahora);
        ubicacion_txt = (TextView) findViewById(R.id.ubicacion);
        asignacion = (TextView) findViewById(R.id.asignacion);
        localizacion = (Button) findViewById(R.id.localizacion);
        Intent i=this.getIntent();
        latitud=i.getExtras().getString("LATITUD");
        longitud=i.getExtras().getString("LONGITUD");
        profundidad =i.getExtras().getString("PROFUNDIDAD");
        magnitud=i.getExtras().getString("MAGNITUD");
        referencia=i.getExtras().getString("REFERENCIA");
        epicentro=i.getExtras().getString("EPICENTRO");
        fecha = i.getExtras().getString("FECHA");
        hora = i.getExtras().getString("HORA");
        intensidad = i.getExtras().getString("INTENSIDAD");
        String horax = i.getExtras().getString("HORA");
        simulacro = i.getExtras().getString("SIMULACRO");

        satelite = (Button) findViewById(R.id.satelite);
        terreno = (Button) findViewById(R.id.terreno);

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
                Intent intent = new Intent(DetailActivity.this,Ultimosismo3.class);
                startActivity(intent);
            }
        });
        blocke2a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(DetailActivity.this,Ultimosismoslist.class);
                startActivity(intent);
            }
        });
        blocke3a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(DetailActivity.this,Listadoenmapa.class);
                startActivity(intent);
            }
        });
        blocke4a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(DetailActivity.this,Configuraciones.class);
                startActivity(intent);
            }
        });
        blocke5a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(DetailActivity.this,Glosarioestatico.class);
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
                Intent intent = new Intent(DetailActivity.this,Listadoredessociales.class);
                startActivity(intent);
            }
        });




        Typeface fontAwesomeFont = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

        datox = (Button) findViewById(R.id.localizacion);
        datox.setTypeface(fontAwesomeFont);
        //evluar dato referencia
        if (referencia.length() == 00){
            ubicacion_txt.setText("--");
        }
        else {
            Double val = Double.parseDouble(simulacro);
            if(val == 1){
                ubicacion_txt.setText("'SIMULACRO'" + "\n" + referencia);
            }
            else{
                ubicacion_txt.setText(referencia);
            }
        }

        //evaluar dato latitud
        if (latitud.length() == 00){
            latitud_txt.setText("--");
        }
        else {
            latitud_txt.setText(latitud);
        }


        //evaluar dato longitud
        if (longitud.length() == 00){
            longitud_txt.setText("--");
        }
        else {
            longitud_txt.setText(longitud);
        }


        //latitud_txt.setText(latitud);
        //longitud_txt.setText(longitud);

        lati= Double.parseDouble(latitud);
        longi = Double.parseDouble(longitud);



        //evaluar dato longitud
        if (profundidad.length() == 00){
            profundidad_txt.setText("- - "+"km");
        }
        else {
            profundidad_txt.setText(profundidad+"km");
        }


        //evaluar dato magnitud
        if (magnitud.length() == 00){
             magnitud_txt.setText("- -");
        }
        else {
            magnitud_txt.setText(magnitud);
        }


        //evaluar dato magnitud
        magni = magnitud;
        double w;
        try {
            w = new Double(magni);
        } catch (NumberFormatException e) {
            w = 0;
        }

        if (w == 0){
            magnitud2 = "0";
            asignacion.setText("- -");
        }
        else
        {
            magnitud2 = magnitud;
           asignacion.setText(magnitud);
        }



        //evaluar dato magnitud
        if (intensidad.length() == 00){
            imax_txt.setText("- -");
        }
        else {
            imax_txt.setText(intensidad);
        }


        //evaluar dato fecha
        if (fecha.length() == 00){
              dato_fecha = "- -";
        }
        else {
              dato_fecha = fecha;

            imax_txt.setText(intensidad);
        }


        //evaluar dato hora
        if (hora.length() == 00){
              dato_hora = "- -";
        }
        else {
              dato_hora = hora;
        }

        fechahora_txt.setText(dato_fecha+"  "+dato_hora);
       // ubicacion_txt.setText(referencia);


        encabezado_titulos = (RelativeLayout) findViewById(R.id.encabezado_titulos);
      //  asignacion.setText(magnitud);



        Double val = Double.parseDouble(magnitud2);


        mk= (Button) findViewById(R.id.button);
        mk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(DetailActivity.this, mk);
                popup.getMenuInflater().inflate(R.menu.menu_main, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        int id = item.getItemId();

                        if (id == R.id.ultimo_sismo) {
                            Intent intent = new Intent(DetailActivity.this,Ultimosismo3.class);
                            startActivity(intent);
                            return true;
                        }


                        if (id == R.id.listado_ultimos_sismos) {
                            Intent intent = new Intent(DetailActivity.this,Ultimosismoslist.class);
                            startActivity(intent);
                            return true;
                        }


                        if (id == R.id.mapa_sismos) {
                            Intent intent = new Intent(DetailActivity.this,Listadoenmapa.class);
                            startActivity(intent);
                            return true;
                        }


                        if (id == R.id.glosario) {
                            Intent intent = new Intent(DetailActivity.this,Glosarioestatico.class);
                            startActivity(intent);
                            return true;
                        }

                        if (id == R.id.notificarsino) {
                            Intent intent = new Intent(DetailActivity.this,Configuraciones.class);
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
                        return DetailActivity.super.onOptionsItemSelected(item);
                    }
                });
                popup.show();
            }
        });




        if (val >= 0 && val < 4.5){
            asignacion.setBackgroundResource(R.drawable.circuloverde);
            encabezado_titulos.setBackgroundColor(ContextCompat.getColor(this, R.color.verdeigp));
        }

        else if (val >= 4.5 && val <= 6.0){
            asignacion.setBackgroundResource(R.drawable.circuloamarillo);
            encabezado_titulos.setBackgroundColor(ContextCompat.getColor(this, R.color.orangeyellow));
        }

        else if (val > 6.0 && val <= 15){
            asignacion.setBackgroundResource(R.drawable.circulorojo);
            encabezado_titulos.setBackgroundColor(ContextCompat.getColor(this, R.color.rojoigp));
        }

        else if (val > 15){
            asignacion.setBackgroundResource(R.drawable.circulorojo);
            encabezado_titulos.setBackgroundColor(ContextCompat.getColor(this, R.color.rojo2));
        }
    }

    public void onMapReady(final GoogleMap map) {
        BitmapDescriptor icon;
        icon = BitmapDescriptorFactory.fromResource(R.drawable.iconored);
        LatLng latLng = new LatLng(lati,longi);
        map.addMarker(new MarkerOptions().position(latLng).title("Epicentro").icon(icon).anchor(0.5f, 0.5f)).showInfoWindow();
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 6));
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        map.animateCamera(CameraUpdateFactory.zoomTo(6), 2000, null);
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
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lati, longi), 6));
            }
        });
    }



    void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
    }


}
