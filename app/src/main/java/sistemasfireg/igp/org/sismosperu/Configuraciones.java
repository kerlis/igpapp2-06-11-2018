package sistemasfireg.igp.org.sismosperu;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import me.leolin.shortcutbadger.ShortcutBadger;

public class Configuraciones extends Activity {
    EditText dato1,dato2;
    TextView textView;
    TextView valor;
    String Message;
    String Message2;
    String  v;
    String elvalor;
    String elvalor2;
    int mWidthScreen;
    int mHeightScreen;
    int tamano;
    RadioButton notificar;
    RadioButton nonotificar;
    RadioButton vibrar;
    RadioButton sonido;
    TextView dato_nombre;
    TextView dato_version_numero;
    TextView dato_version_nombre;
    Button mk;
    String ko,ajustes,tipo;
    Integer r,s;
    String  elvalor_defecto = "a";
    String  elvalor2_defecto ="bb";
    String Message3_defecto;
    String Message4_defecto;
    TextView enlaceweb;


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
        setContentView(R.layout.activity_configuraciones);

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
        notificar = (RadioButton) findViewById(R.id.notificar);
        nonotificar = (RadioButton) findViewById(R.id.nonotificar);
        vibrar = (RadioButton) findViewById(R.id.vibrar);
        sonido = (RadioButton) findViewById(R.id.sonido);
        RelativeLayout videoView = (RelativeLayout) findViewById(R.id.videoView1);
        Display display = ((WindowManager) this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        mWidthScreen = display.getWidth();
        mHeightScreen = display.getHeight();
        enlaceweb = (TextView) findViewById(R.id.enlaceweb);

        enlaceweb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.igp.gob.pe/"));
                startActivity(intent);
            }
        });



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
                Intent intent = new Intent(Configuraciones.this,Ultimosismo3.class);
                startActivity(intent);
            }
        });
        blocke2a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Configuraciones.this,Ultimosismoslist.class);
                startActivity(intent);
            }
        });
        blocke3a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Configuraciones.this,Listadoenmapa.class);
                startActivity(intent);
            }
        });
        blocke4a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Configuraciones.this,Configuraciones.class);
                startActivity(intent);
            }
        });
        blocke5a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Configuraciones.this,Glosarioestatico.class);
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
                Intent intent = new Intent(Configuraciones.this,Listadoredessociales.class);
                startActivity(intent);
            }
        });




        switch (getResources().getDisplayMetrics().densityDpi) {
            case DisplayMetrics.DENSITY_LOW:
                tamano = (mHeightScreen - 305);
                break;

            case DisplayMetrics.DENSITY_MEDIUM:
                tamano = (mHeightScreen - 380);
                break;

            case DisplayMetrics.DENSITY_TV:
                tamano = (mHeightScreen - 479);
                break;


            case DisplayMetrics.DENSITY_HIGH:
                tamano = (mHeightScreen - 530);
                break;

            case DisplayMetrics.DENSITY_XHIGH:
                tamano = (mHeightScreen - 680);
                break;

            case DisplayMetrics.DENSITY_XXHIGH:
                tamano = (mHeightScreen - 980);
                break;

            case DisplayMetrics.DENSITY_XXXHIGH:
                tamano = (mHeightScreen - 1280);
                break;
        }


        videoView.getLayoutParams().height = tamano;
        videoView.requestLayout();
        final PackageManager pm = getApplicationContext().getPackageManager();
        ApplicationInfo ai;
        try {
            ai = pm.getApplicationInfo( this.getPackageName(), 0);
        } catch (final PackageManager.NameNotFoundException e) {
            ai = null;
        }
        final String applicationName = (String) (ai != null ? pm.getApplicationLabel(ai) : "(unknown)");
        int versionCode = BuildConfig.VERSION_CODE;
        String versionName = BuildConfig.VERSION_NAME;
        String valor_version_code;
        valor_version_code = String.valueOf(versionCode).toString();
        dato_version_numero = (TextView) findViewById(R.id.datoversionnumero);
        dato_version_numero.setText(versionName);
        //dato_version_numero.setText("36");

        try {
            FileInputStream fileInputStream_read =  openFileInput("datos_configuracion");
            InputStreamReader inputStreamReader_read =  new InputStreamReader(fileInputStream_read);
            BufferedReader bufferedReader_read = new BufferedReader(inputStreamReader_read);
            StringBuffer stringBuffer_read =  new StringBuffer();
            try {
                while ((Message = bufferedReader_read.readLine())!=null){
                    stringBuffer_read.append(Message);
                }
                ko = stringBuffer_read.toString();
                String ajustes_read = ko.split(",")[0];
                String tipo_read = ko.split(",")[1];

                int leido1 = ajustes_read.length();
                int leido2 = tipo_read.length();

                if(leido1 == 1){
                    notificar.setChecked(true);
                    nonotificar.setChecked(false);
                    elvalor ="a";
                }
                else{
                    notificar.setChecked(false);
                    nonotificar.setChecked(true);
                    elvalor ="bb";
                }

                if(leido2 == 1){
                    vibrar.setChecked(true);
                    sonido.setChecked(false);
                    elvalor2 ="a";
                }
                else{
                    vibrar.setChecked(false);
                    sonido.setChecked(true);
                    elvalor2 ="bb";
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        mk= (Button) findViewById(R.id.button);
        mk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(Configuraciones.this, mk);
                popup.getMenuInflater().inflate(R.menu.menu_main, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        int id = item.getItemId();

                        if (id == R.id.ultimo_sismo) {
                            Intent intent = new Intent(Configuraciones.this,Ultimosismo3.class);
                            startActivity(intent);
                            return true;
                        }


                        if (id == R.id.listado_ultimos_sismos) {
                            Intent intent = new Intent(Configuraciones.this,Ultimosismoslist.class);
                            startActivity(intent);
                            return true;
                        }


                        if (id == R.id.mapa_sismos) {
                            Intent intent = new Intent(Configuraciones.this,Listadoenmapa.class);
                            startActivity(intent);
                            return true;
                        }




                        if (id == R.id.glosario) {
                            Intent intent = new Intent(Configuraciones.this,Glosarioestatico.class);
                            startActivity(intent);
                            return true;
                        }

                        if (id == R.id.notificarsino) {
                            Intent intent = new Intent(Configuraciones.this,Configuraciones.class);
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
                        return Configuraciones.super.onOptionsItemSelected(item);
                    }
                });
                popup.show();
            }
        });

        RadioGroup rg = (RadioGroup) findViewById(R.id.grupoopcion1);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                switch(checkedId)
                {
                    case R.id.notificar:
                        elvalor = "a";
                        break;
                    case R.id.nonotificar:
                        elvalor = "bb";
                        break;

                }
            }
        });

        RadioGroup rg2 = (RadioGroup) findViewById(R.id.grupoopcion2);
        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                switch(checkedId)
                {
                    case R.id.vibrar:
                        elvalor2 = "a";
                        break;
                    case R.id.sonido:
                        elvalor2 = "bb";
                        break;

                }
            }
        });

}




    void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
    }

    public void valores_por_defecto(View V){

        //******************grabar valores por defecto

        try {
            FileInputStream fileInputStream =  openFileInput("datos_configuracion");
            InputStreamReader inputStreamReader =  new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer =  new StringBuffer();
            try {
                while ((Message = bufferedReader.readLine())!=null)
                {
                    stringBuffer.append(Message);
                }
                ko = stringBuffer.toString();
                StringTokenizer st = new StringTokenizer(stringBuffer.toString(), ",");
                ajustes = st.nextToken();
                tipo = st.nextToken();
                r = ajustes.length();
                s = tipo.length();

                if (r == 1){

                     Message3_defecto = elvalor_defecto+",";
                     Message4_defecto = elvalor2_defecto+",";

                    String file_name = "datos_configuracion";
                    try {
                        FileOutputStream fileOutputStream = openFileOutput(file_name, MODE_PRIVATE);
                        fileOutputStream.write(Message3_defecto.getBytes());
                        fileOutputStream.write(Message4_defecto.getBytes());
                        fileOutputStream.close();
                        Toast.makeText(getApplicationContext(),"Configurado", Toast.LENGTH_LONG).show();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    String ff = "";

                    Message3_defecto = elvalor_defecto+",";
                    Message4_defecto = elvalor2_defecto+",";

                    String file_name = "datos_configuracion";
                    try {
                        FileOutputStream fileOutputStream = openFileOutput(file_name, MODE_PRIVATE);
                        fileOutputStream.write(Message3_defecto.getBytes());
                        fileOutputStream.write(Message4_defecto.getBytes());
                        fileOutputStream.close();
                        Toast.makeText(getApplicationContext(),"Configurado", Toast.LENGTH_LONG).show();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //************************grabar valores por defecto

    }

    public void write_mesage(View view)
    {
        String Message3 = elvalor+",";
        String Message4 = elvalor2+",";

        String file_name = "datos_configuracion";
        try {
            FileOutputStream fileOutputStream = openFileOutput(file_name, MODE_PRIVATE);
            fileOutputStream.write(Message3.getBytes());
            fileOutputStream.write(Message4.getBytes());
            fileOutputStream.close();
            Toast.makeText(getApplicationContext(),"Configurado", Toast.LENGTH_LONG).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
