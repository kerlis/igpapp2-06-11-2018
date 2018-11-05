package sistemasfireg.igp.org.sismosperu;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import me.leolin.shortcutbadger.ShortcutBadger;

public class Glosarioestatico extends Activity {
    Button mk;
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
        setContentView(R.layout.glosario2);
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
        Typeface fontAwesomeFont = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        TextView ihoras = (TextView) findViewById(R.id.iconohora);
        TextView iubicacion = (TextView) findViewById(R.id.iconoubicacion);
        ihoras.setTypeface(fontAwesomeFont);
        iubicacion.setTypeface(fontAwesomeFont);
        TextView titulo4;
        TextView titulo5;
        TextView titulo6;
        TextView titulo7;
        TextView titulo8;
        TextView titulo9;
        titulo4 = (TextView) findViewById(R.id.titulo4);
        titulo5 = (TextView) findViewById(R.id.titulo5);
        titulo6 = (TextView) findViewById(R.id.titulo6);
        titulo7 = (TextView) findViewById(R.id.titulo7);
        titulo8 = (TextView) findViewById(R.id.titulo8);
        titulo9 = (TextView) findViewById(R.id.titulo9);
        titulo4.setPaintFlags(titulo4.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        titulo5.setPaintFlags(titulo5.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        titulo6.setPaintFlags(titulo6.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        titulo7.setPaintFlags(titulo7.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        titulo8.setPaintFlags(titulo8.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        titulo9.setPaintFlags(titulo9.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);



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
                Intent intent = new Intent(Glosarioestatico.this,Ultimosismo3.class);
                startActivity(intent);
            }
        });
        blocke2a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Glosarioestatico.this,Ultimosismoslist.class);
                startActivity(intent);
            }
        });
        blocke3a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Glosarioestatico.this,Listadoenmapa.class);
                startActivity(intent);
            }
        });
        blocke4a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Glosarioestatico.this,Configuraciones.class);
                startActivity(intent);
            }
        });
        blocke5a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Glosarioestatico.this,Glosarioestatico.class);
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
                Intent intent = new Intent(Glosarioestatico.this,Listadoredessociales.class);
                startActivity(intent);
            }
        });





        mk= (Button) findViewById(R.id.button);
        mk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(Glosarioestatico.this, mk);
                popup.getMenuInflater().inflate(R.menu.menu_main, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        int id = item.getItemId();

                        if (id == R.id.ultimo_sismo) {
                            Intent intent = new Intent(Glosarioestatico.this,Ultimosismo3.class);
                            startActivity(intent);
                            return true;
                        }


                        if (id == R.id.listado_ultimos_sismos) {
                            Intent intent = new Intent(Glosarioestatico.this,Ultimosismoslist.class);
                            startActivity(intent);
                            return true;
                        }


                        if (id == R.id.mapa_sismos) {
                            Intent intent = new Intent(Glosarioestatico.this,Listadoenmapa.class);
                            startActivity(intent);
                            return true;
                        }

                        if (id == R.id.glosario) {
                            Intent intent = new Intent(Glosarioestatico.this,Glosarioestatico.class);
                            startActivity(intent);
                            return true;
                        }

                        if (id == R.id.notificarsino) {
                            Intent intent = new Intent(Glosarioestatico.this,Configuraciones.class);
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
                        return Glosarioestatico.super.onOptionsItemSelected(item);
                    }
                });
                popup.show();
            }
        });
    }

    void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
    }

}
