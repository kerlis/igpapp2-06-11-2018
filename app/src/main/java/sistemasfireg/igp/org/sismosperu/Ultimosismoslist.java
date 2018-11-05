package sistemasfireg.igp.org.sismosperu;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import me.leolin.shortcutbadger.ShortcutBadger;
import sistemasfireg.igp.org.sismosperu.m_model.messages;
import sistemasfireg.igp.org.sismosperu.m_ui.CustomAdapter;
public class Ultimosismoslist extends Activity {
    Button mk;
    int mWidthScreen;
    int mHeightScreen;
    int tamano;
    DatabaseReference mFirebaseDatabase5;
    FirebaseDatabase database;
    ArrayList<messages> objetosismos=new ArrayList<messages>();
    CustomAdapter adapter;
    ListView lv;
    static final int READ_BLOCK_SIZE = 100;
    String k;
    String tipo;


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
        setContentView(R.layout.activity_ultimossismoslist);

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
                Toast.makeText(Ultimosismoslist.this, "Conexión Establecida", Toast.LENGTH_LONG).show();
            }
            else if(info.getType() == ConnectivityManager.TYPE_MOBILE){
                Toast.makeText(Ultimosismoslist.this, "Conexión Establecida", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(Ultimosismoslist.this, "Su Equipo ha Bloquedo la Conexión", Toast.LENGTH_LONG).show();
        }


        database = FirebaseDatabase.getInstance();
        //  FirebaseDatabase.getInstance();

        mFirebaseDatabase5 = database.getReference("messages");
        mFirebaseDatabase5.keepSynced(true);


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
                Intent intent = new Intent(Ultimosismoslist.this,Ultimosismo3.class);
                startActivity(intent);
            }
        });
        blocke2a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Ultimosismoslist.this,Ultimosismoslist.class);
                startActivity(intent);
            }
        });
        blocke3a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Ultimosismoslist.this,Listadoenmapa.class);
                startActivity(intent);
            }
        });
        blocke4a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Ultimosismoslist.this,Configuraciones.class);
                startActivity(intent);
            }
        });
        blocke5a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Ultimosismoslist.this,Glosarioestatico.class);
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
                Intent intent = new Intent(Ultimosismoslist.this,Listadoredessociales.class);
                startActivity(intent);
            }
        });



        lv = (ListView) findViewById(R.id.lv);
        adapter=new CustomAdapter(Ultimosismoslist.this,retrieve());
        lv.setAdapter(adapter);






        mk= (Button) findViewById(R.id.button);

        TextView Button = (TextView) findViewById(R.id.txtLostpassword);

        Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://ultimosismo.igp.gob.pe/bdsismos/ultimosSismosSentidos.php#tabs-2"));
                startActivity(intent);
            }
        });

        Display display = ((WindowManager) this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        mWidthScreen = display.getWidth();
        mHeightScreen = display.getHeight();

        switch (getResources().getDisplayMetrics().densityDpi) {
            case DisplayMetrics.DENSITY_LOW:
                tamano = (mHeightScreen - 145);
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                tamano = (mHeightScreen - 193);
                break;
            case DisplayMetrics.DENSITY_TV:
                tamano = (mHeightScreen - 257);
                break;
            case DisplayMetrics.DENSITY_HIGH:
                tamano = (mHeightScreen - 290);
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                tamano = (mHeightScreen - 386);
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                tamano = (mHeightScreen - 579);
                break;
            case DisplayMetrics.DENSITY_XXXHIGH:
                tamano = (mHeightScreen - 772);
                break;
        }

        lv.getLayoutParams().height = tamano;
        lv.requestLayout();


        mk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(Ultimosismoslist.this, mk);
                popup.getMenuInflater().inflate(R.menu.menu_main, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        if (id == R.id.ultimo_sismo) {
                            Intent intent = new Intent(Ultimosismoslist.this,Ultimosismo3.class);
                            startActivity(intent);
                            return true;
                        }
                        if (id == R.id.listado_ultimos_sismos) {
                            Intent intent = new Intent(Ultimosismoslist.this,Ultimosismoslist.class);
                            startActivity(intent);
                            return true;
                        }
                        if (id == R.id.mapa_sismos) {
                            Intent intent = new Intent(Ultimosismoslist.this,Listadoenmapa.class);
                            startActivity(intent);
                            return true;
                        }
                        if (id == R.id.glosario) {
                            Intent intent = new Intent(Ultimosismoslist.this,Glosarioestatico.class);
                            startActivity(intent);
                            return true;
                        }
                        if (id == R.id.notificarsino) {
                            Intent intent = new Intent(Ultimosismoslist.this,Configuraciones.class);
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
                        return Ultimosismoslist.super.onOptionsItemSelected(item);
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

    public ArrayList<messages> retrieve() {
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


        DatabaseReference mDatabase;
        if (tipo == null){
            mDatabase = FirebaseDatabase.getInstance("https://igpsismos2.firebaseio.com/").getReference("messages");
        }
        else{
            mDatabase = FirebaseDatabase.getInstance(tipo).getReference("messages");
        }
        */
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance("https://igpsismos2.firebaseio.com/").getReference("messages");

        //   Toast.makeText(getBaseContext(), "rbase",Toast.LENGTH_SHORT).show();

        //   Toast.makeText(getBaseContext(), "red normal",Toast.LENGTH_SHORT).show();

        // FirebaseDatabase.getInstance();
     //   mFirebaseDatabase5 = database.getReference("messages");

        // FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        mDatabase.orderByKey().limitToLast(20).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                messages objetosismo = dataSnapshot.getValue(messages.class);
                objetosismos.add(objetosismo);


                // Log.i("myTag", String.valueOf(objetosismos.add(objetosismo)));

                //              Toast.makeText(Ultimosismoslist.this,"dddd" + objetosismos.add(objetosismo), Toast.LENGTH_LONG).show();



                int iSwapCount = objetosismos.size() - 1;
                int iPosition = objetosismos.size()- 1;
                for (int j = 0; j < iSwapCount; j++)
                {
                    Collections.swap(objetosismos, iPosition, iPosition - 1);
                    iPosition = iPosition - 1;
                }


                //  Toast.makeText(Ultimosismoslist.this,"dddd" + h, Toast.LENGTH_LONG).show();
                lv.setAdapter(adapter);


            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                messages objetosismo = dataSnapshot.getValue(messages.class);
                objetosismos.add(objetosismo);

                int iSwapCount = objetosismos.size() - 1;
                int iPosition = objetosismos.size()- 1;
                for (int j = 0; j < iSwapCount; j++)
                {
                    Collections.swap(objetosismos, iPosition, iPosition - 1);
                    iPosition = iPosition - 1;

                 }
                lv.setAdapter(adapter);

   /*
                for(int i = 19, j = objetosismos.size() - 1; i == j; i++) {
                    objetosismos.add(i, objetosismos.remove(j));
                     Collections.reverse(objetosismos);
                }*/
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
        });
        // mFirebaseDatabase5.keepSynced(true);
        return objetosismos;
    }
}