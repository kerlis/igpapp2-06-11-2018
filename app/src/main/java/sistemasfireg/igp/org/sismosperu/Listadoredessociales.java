//package sistemasfireg.igp.org.sismosperu;

/**
 * Created by usuario on 16/05/2018.
 */

//public class Listadoredessociales {
//}



package sistemasfireg.igp.org.sismosperu;

//package com.journaldev.recyclerviewgridlayoutmanager;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import java.util.ArrayList;
public class Listadoredessociales extends FragmentActivity implements RecyclerViewAdapter.ItemListener {

    RecyclerView recyclerView;
    RecyclerView recyclerView2;

    ArrayList<DataModel> arrayList;
    ArrayList<DataModel> arrayList2;


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
        setContentView(R.layout.redessociales);


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
                Intent intent = new Intent(Listadoredessociales.this,Ultimosismo3.class);
                startActivity(intent);
            }
        });
        blocke2a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Listadoredessociales.this,Ultimosismoslist.class);
                startActivity(intent);
            }
        });
        blocke3a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Listadoredessociales.this,Listadoenmapa.class);
                startActivity(intent);
            }
        });
        blocke4a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Listadoredessociales.this,Configuraciones.class);
                startActivity(intent);
            }
        });
        blocke5a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Listadoredessociales.this,Glosarioestatico.class);
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
                Intent intent = new Intent(Listadoredessociales.this,Listadoredessociales.class);
                startActivity(intent);
            }
        });




        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        arrayList = new ArrayList<>();
        arrayList.add(new DataModel("FACEBOOK", R.drawable.facebookicon, R.drawable.facebookicon));
        arrayList.add(new DataModel("TWITTER", R.drawable.iconoreloj, R.drawable.twittericon));

        recyclerView2 = (RecyclerView) findViewById(R.id.recyclerView2);
        arrayList2 = new ArrayList<>();
        arrayList2.add(new DataModel("FACEBOOK ", R.drawable.facebookicon, R.drawable.facebookicon));
        arrayList2.add(new DataModel("TWITTER ", R.drawable.twittericon, R.drawable.twittericon));
        arrayList2.add(new DataModel("YOUTUBE ", R.drawable.youtubeicon, R.drawable.youtubeicon));
      //  arrayList2.add(new DataModel("INSTAGRAM ", R.drawable.instagramicon, R.drawable.instagramicon));
    //    arrayList2.add(new DataModel("LINKEDIN ", R.drawable.linkedinicon, R.drawable.linkedinicon));

        //arrayList.add(new DataModel("NUESTROS PRODUCTOS ", R.drawable.three_d, R.drawable.iconoreloj));
        //arrayList.add(new DataModel("NOTICIAS", R.drawable.terraria, R.drawable.iconosismo));

        /*
        arrayList.add(new DataModel("Sismos", R.drawable.battle, "#73000000" ));
        arrayList.add(new DataModel("Volcanes", R.drawable.beer, "#800D0000"));
        arrayList.add(new DataModel("ATMOSFERA", R.drawable.ferrari, "#73000000"));
        arrayList.add(new DataModel("HUAYCOS", R.drawable.jetpack_joyride, "#73000000"));
        arrayList.add(new DataModel("CLIMA ", R.drawable.three_d, "#73000000"));
        arrayList.add(new DataModel("FEMONEMO DEL NIÑO", R.drawable.terraria, "#73000000"));
*/

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, arrayList, this);
        recyclerView.setAdapter(adapter);


        RecyclerViewAdapter adapter2 = new RecyclerViewAdapter(this, arrayList2, this);
        recyclerView2.setAdapter(adapter2);



        /**
         AutoFitGridLayoutManager that auto fits the cells by the column width defined.
         **/

        AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(this, 500);
        recyclerView.setLayoutManager(layoutManager);


        AutoFitGridLayoutManager layoutManager2 = new AutoFitGridLayoutManager(this, 500);
        recyclerView2.setLayoutManager(layoutManager2);


        /**
         Simple GridLayoutManager that spans two columns
         **/
        /*GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);*/
    }

    @Override
    public void onItemClick(DataModel item) {
       String valor = String.valueOf(item.text);
        Toast.makeText(getApplicationContext(), item.text, Toast.LENGTH_SHORT).show();
        if(valor  == "YOUTUBE"){
           // Intent intent = new Intent(Listadoredessociales.this,Ultimosismoslist.class);
            openDetailActivity("https://www.youtube.com/igp_videos","IGP Youtube");
           // intent.putExtra("https://www.youtube.com/igp_videos",details[0]);


           // startActivity(intent);
        }
        else if(valor  == "TWITTER"){
            openDetailActivity("http://twitter.com/#!/Sismos_Peru_IGP", "IGP CENSIS Twitter");

            //Intent intent = new Intent(Listadoredessociales.this,Ultimosismoslist.class);
            //startActivity(intent);
        }
        else if(valor  == "FACEBOOK"){
            openDetailActivity("http://www.facebook.com/sismosperuigp","IGP CENSIS Facebook");

            // Intent intent = new Intent(Listadoredessociales.this,Ultimosismoslist.class);
           // startActivity(intent);
        }


        if(valor  == "YOUTUBE "){
            // Intent intent = new Intent(Listadoredessociales.this,Ultimosismoslist.class);
            openDetailActivity("https://www.youtube.com/igp_videos","IGP Youtube");
            // intent.putExtra("https://www.youtube.com/igp_videos",details[0]);


            // startActivity(intent);
        }
        else if(valor  == "TWITTER "){
            openDetailActivity("https://twitter.com/igp_peru", "IGP Twitter");

            //Intent intent = new Intent(Listadoredessociales.this,Ultimosismoslist.class);
            //startActivity(intent);
        }


        else if(valor  == "FACEBOOK "){
            openDetailActivity("http://www.facebook.com/igp.peru","IGP Facebook");
        }


        /*
        else if(valor  == "INSTAGRAM "){
            openDetailActivity("https://www.instagram.com/igp.peru/", "IGP Instagram");
        }

        else if(valor  == "LINKEDIN "){
            openDetailActivity("https://www.linkedin.com/company/igpperu/", "IGP Linkedin");
        }
*/



        /*
        else if(valor  == "GOOGLE GROUP"){
            openDetailActivity("https://groups.google.com/forum/#!forum/sismos-peru-igp", "IGP Google Group");

        }
        */
        else{

        }

       // Toast.makeText(getApplicationContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();

    }



    public void onItemClick(DataModel2 item) {
        String valor = String.valueOf(item.text);
        Toast.makeText(getApplicationContext(), item.text, Toast.LENGTH_SHORT).show();
        if(valor  == "YOUTUBE"){
            // Intent intent = new Intent(Listadoredessociales.this,Ultimosismoslist.class);
            openDetailActivity("https://www.youtube.com/igp_videos","IGP Youtube");
            // intent.putExtra("https://www.youtube.com/igp_videos",details[0]);


            // startActivity(intent);
        }
        else if(valor  == "TWITTER"){
            openDetailActivity("https://twitter.com/igp_peru", "IGP Twitter");

            //Intent intent = new Intent(Listadoredessociales.this,Ultimosismoslist.class);
            //startActivity(intent);
        }
        else if(valor  == "FACEBOOK"){
            openDetailActivity("http://www.facebook.com/igp.peru","IGP Facebook");

            // Intent intent = new Intent(Listadoredessociales.this,Ultimosismoslist.class);
            // startActivity(intent);
        }

        else if(valor  == "INSTAGRAM"){
            openDetailActivity("https://www.instagram.com/igp.peru/", "IGP Instagram");
        }

        else if(valor  == "LINKEDIN"){
            openDetailActivity("https://www.linkedin.com/company/igpperu/", "IGP Linkedin");
        }


        else{

        }

        // Toast.makeText(getApplicationContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();

    }



    void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
    }

    private void openDetailActivity(String...details) {
        Intent i=new Intent(Listadoredessociales.this,Contenedorredsocial.class);
        i.putExtra("ACTIVIDAD_RECIENTE",details[0]);
        i.putExtra("NOMBRE",details[1]);

        Listadoredessociales.this.startActivity(i);
    }



}

