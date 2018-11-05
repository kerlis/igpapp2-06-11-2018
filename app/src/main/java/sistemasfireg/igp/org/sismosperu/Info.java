package sistemasfireg.igp.org.sismosperu;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import me.leolin.shortcutbadger.ShortcutBadger;

public class Info extends Activity {

    TextView dato_nombre;
    TextView dato_version_numero;
    TextView dato_version_nombre;
    Button mk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

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
                Toast.makeText(Info.this, "TIPO DE CONEXIÓN : WIFI", Toast.LENGTH_LONG).show();
            }
            else if(info.getType() == ConnectivityManager.TYPE_MOBILE){
                Toast.makeText(Info.this, "TIPO DE CONEXIÓN :MOBILE", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(Info.this, "SIN CONEXIÓN", Toast.LENGTH_LONG).show();
        }

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

        Drawable login_activity_top_background = getResources().getDrawable(R.drawable.ddd);
        login_activity_top_background.setAlpha(600);
        RelativeLayout login_activity_top = (RelativeLayout) findViewById(R.id.activity_info);
        login_activity_top.setBackgroundDrawable(login_activity_top_background);


        dato_nombre = (TextView) findViewById(R.id.nombreaplicativo);
        dato_version_numero = (TextView) findViewById(R.id.datoversionnumero);
        dato_version_nombre = (TextView) findViewById(R.id.datoversionnombre);

        dato_nombre.setText(applicationName);
        dato_version_numero.setText("35");
        dato_version_nombre.setText("35");

        mk= (Button) findViewById(R.id.button);
        mk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(Info.this, mk);
                popup.getMenuInflater().inflate(R.menu.menu_main, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        int id = item.getItemId();

                        if (id == R.id.ultimo_sismo) {
                            Intent intent = new Intent(Info.this,Ultimosismo3.class);
                            startActivity(intent);
                            return true;
                        }

                        if (id == R.id.listado_ultimos_sismos) {
                            Intent intent = new Intent(Info.this,Ultimosismoslist.class);
                            startActivity(intent);
                            return true;
                        }

                        if (id == R.id.mapa_sismos) {
                            Intent intent = new Intent(Info.this,Listadoenmapa.class);
                            startActivity(intent);
                            return true;
                        }

                        if (id == R.id.glosario) {
                            Intent intent = new Intent(Info.this,Glosarioestatico.class);
                            startActivity(intent);
                            return true;
                        }

                        if (id == R.id.notificarsino) {
                            Intent intent = new Intent(Info.this,Configuraciones.class);
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
                        return Info.super.onOptionsItemSelected(item);
                    }
                });
                popup.show();
            }
        });
    }
}
