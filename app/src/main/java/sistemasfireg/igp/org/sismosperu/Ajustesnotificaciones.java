package sistemasfireg.igp.org.sismosperu;

import android.app.AlertDialog;
import android.app.IntentService;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ToggleButton;

//import com.google.android.gms.iid.InstanceID;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.StringTokenizer;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class Ajustesnotificaciones extends AppCompatActivity {
    static final int READ_BLOCK_SIZE = 100;
    String oldtoken;
    String json;
    File file;
    String nuevotoken;
    Button localizacionbutton,ckeckstatusgps;
    FusedLocationProviderClient mFusedLocationClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajustescontainer);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);



  localizacionbutton = (Button) findViewById(R.id.obtenerlocalizacionuno);


         localizacionbutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //  Toast.makeText(Ajustesnotificaciones.this, "dsad", Toast.LENGTH_LONG).show();
                obtenerlocalizacion();

            }
        });


        ckeckstatusgps = (Button) findViewById(R.id.ckeckstatusgps);

        ckeckstatusgps.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //  Toast.makeText(Ajustesnotificaciones.this, "dsad", Toast.LENGTH_LONG).show();
                statusCheck();

            }
        });


/*
        ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleButton2);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                } else {
                    // The toggle is disabled
                }
            }
        });

*/
        /////  generartokennuevo.setOnClickListener();
        //  Button generartokennuevo = (Button) findViewById(R.id.generarnuevaid);


/*






        Button button2 = (Button) findViewById(R.id.vercapturado2);
        button2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //  Toast.makeText(Ajustesnotificaciones.this, "dsad", Toast.LENGTH_LONG).show();
               vervalor();
            }
        });

        */


        Button button = (Button) findViewById(R.id.generarnuevaid);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //  Toast.makeText(Ajustesnotificaciones.this, "dsad", Toast.LENGTH_LONG).show();
                obtenertoken();
            }
        });


        Button button2 = (Button) findViewById(R.id.vernuevotoken);
        button2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //  Toast.makeText(Ajustesnotificaciones.this, "dsad", Toast.LENGTH_LONG).show();
                vervalor();
            }
        });




/*
        Button button3 = (Button) findViewById(R.id.vercapturado3);
        button3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                enviarvalor3();
            }
        });


*/




        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);



    }











    public void statusCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }






    private void obtenerlocalizacion(){

        FusedLocationProviderClient mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        Toast.makeText(getBaseContext(), "valor capturado : "+ location.getLatitude() + location.getLongitude(), Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getBaseContext(), "no hay localizacion: ", Toast.LENGTH_SHORT).show();

                    }
                });

    }




    //private void obtenertoken(String s) {
    //  Toast.makeText(getBaseContext(), "valor capturado : "+ s, Toast.LENGTH_SHORT).show();
    // }




    private void obtenertoken() {


        try
        {
            // Check for current token
            String originalToken = getTokenFromPrefs();
            Log.d(TAG, "Token before deletion: " + originalToken);

            // Resets Instance ID and revokes all tokens.
            FirebaseInstanceId.getInstance().deleteInstanceId();

            // Clear current saved token
            saveTokenToPrefs("");

            // Check for success of empty token
            String tokenCheck = getTokenFromPrefs();
            Log.d(TAG, "Token deleted. Proof: " + tokenCheck);

            // Now manually call onTokenRefresh()
            Log.d(TAG, "Getting new token");
            String nuevotoken =FirebaseInstanceId.getInstance().getToken();


            // if (nuevotoken){
            Toast.makeText(getBaseContext(),"nuevo token" + nuevotoken,Toast.LENGTH_SHORT).show();
            //  }
            //  else{
            //  Toast.makeText(getBaseContext(),"no eliminado ",Toast.LENGTH_SHORT).show();
            //  }


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        String nuevotoken = FirebaseInstanceId.getInstance().getToken();



      /*  try {
            InstanceID.getInstance(getApplicationContext()).deleteInstanceID();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        // Toast.makeText(Ajustesnotificaciones.this, nuevotoken, Toast.LENGTH_LONG).show();

        String file2 = "eltoken";
        try {
            FileInputStream fileIn = openFileInput(file2);
            InputStreamReader InputRead= new InputStreamReader(fileIn);
            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            int charRead;
            charRead=InputRead.read(inputBuffer);
            oldtoken = String.copyValueOf(inputBuffer,0,charRead);
            // k +=readstring;

            // Toast.makeText(getBaseContext(),"token antiguo : " + oldtoken + "\n " +  "nuevo token:" +  nuevotoken ,Toast.LENGTH_SHORT).show();

            InputRead.close();

            enviarvalor3(oldtoken, nuevotoken);
            guardartoken(nuevotoken);




            //  StringTokenizer st = new StringTokenizer(k.toString(), ",");
            // String ajustes = st.nextToken();
            // tipo = st.nextToken();
            // Log.d(TAG, "valor: json: " + k);
            //Toast.makeText(getBaseContext(),"data 2 : " + tipo,Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    public void enviarvalor3(String valor1, String valor2){
        Toast.makeText(getBaseContext(), "oldtoken 1: \n "+ valor1+ "\n\n  nuevotoken: \n"+valor2, Toast.LENGTH_SHORT).show();


        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("Token", "elmensaje")
                .build();
        Request request = new Request.Builder()
                .url("http://arteypixel.com/envio_notificaciones/register.php")
                .post(formBody)
                .build();
        consulta("http://intranet.igp.gob.pe/UtAnMI0laYWBo4/index.php?NewToken="+valor1+"&"+"OldToken="+valor2);
        try {
            client.newCall(request).execute();
            Response response = client.newCall(request).execute();
            //  obtenertoken(String.valueOf(response));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


/*
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_pirates:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.radio_ninjas:
                if (checked)
                    // Ninjas rule
                    break;
        }
    }

*/


    public void consulta(String urlString)  {
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = null;
            BufferedReader bufferedReader = null;
            urlConnection = (HttpURLConnection) url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            json = bufferedReader.readLine();
            //  Toast.makeText(getApplicationContext(), json, Toast.LENGTH_SHORT).show();
            Log.d(TAG, "fdfdfd json: " + json);
            verrecibido(json);
            urlConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardartoken(String token) {
        try{
            File dir = getFilesDir();
            file= new File(dir,"eltoken");
            boolean deleted = file.delete();
            if (deleted){
                Toast.makeText(getBaseContext(),"eliminado",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getBaseContext(),"no eliminado ",Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception e ){
            e.printStackTrace();
        }



        String Message5 = token;
        String file_namex = "eltoken";
        try {
            FileOutputStream fileOutputStream = openFileOutput(file_namex, MODE_PRIVATE);
            fileOutputStream.write(Message5.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }









    public void vervalor() {
        String file2 = "eltoken";
        String k;
        try {
            FileInputStream fileIn = openFileInput(file2);
            InputStreamReader InputRead = new InputStreamReader(fileIn);
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            int charRead;
            charRead = InputRead.read(inputBuffer);
            String readstring = String.copyValueOf(inputBuffer, 0, charRead);
            // k += readstring;
            InputRead.close();
            /// Toast.makeText(getBaseContext(),"data : " + readstring,Toast.LENGTH_SHORT).show();
            // StringTokenizer st = new StringTokenizer(k.toString(), ",");
            //String ajustes = st.nextToken();
            //tipo = st.nextToken();

            //      Log.d(TAG, "valor: json: " + k);

            vertoken(readstring);
            //Toast.makeText(getBaseContext(),"data 2 : " + tipo,Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void vertoken(String valor) {
        Toast.makeText(getBaseContext(),"data : " + valor,Toast.LENGTH_SHORT).show();

    }


    public void verrecibido(String valor) {
        Toast.makeText(getBaseContext(),"valor recibido : " + valor,Toast.LENGTH_SHORT).show();

    }

























    /*

         protected void onHandleIntent(Intent intent)
        {
            try
            {
                // Check for current token
                String originalToken = getTokenFromPrefs();
               // Log.d(TAG, "Token before deletion: " + originalToken);

                // Resets Instance ID and revokes all tokens.
                FirebaseInstanceId.getInstance().deleteInstanceId();

                // Clear current saved token
                saveTokenToPrefs("");

                // Check for success of empty token
                String tokenCheck = getTokenFromPrefs();
               // Log.d(TAG, "Token deleted. Proof: " + tokenCheck);

                // Now manually call onTokenRefresh()
               // Log.d(TAG, "Getting new token");
                FirebaseInstanceId.getInstance().getToken();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
*/
    private void saveTokenToPrefs(String _token)
    {
        // Access Shared Preferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        // Save to SharedPreferences
        editor.putString("registration_id", _token);
        editor.apply();
    }

    private String getTokenFromPrefs()
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getString("registration_id", null);
    }








/*

    public void enviar(String valor1, String valor2){
        Toast.makeText(getBaseContext(), "velores: "+ valor1 + "\n" + valor2, Toast.LENGTH_SHORT).show();

        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("NewToken",valor1)
                .build();
        Request request = new Request.Builder()

       // http://arteypixel.com/envio_notificaciones/register.php
//                        .url("http://arteypixel.com/envio_notificaciones/register.php?NewToken="+valor2+"&"+"OldToken="+valor1)
                .url("http://arteypixel.com/envio_notificaciones/register.php?NewToken="+valor1)

               // .url("http://intranet.igp.gob.pe/UtAnMI0laYWBo4/index.php?NewToken="+valor2+"&"+"OldToken="+valor1)
                // .url("http://intranet.igp.gob.pe/test_erlis/test.php?Token="+nuevotoken)
                //.url("http://intranet.igp.gob.pe/MI0laYWBo4/")
                //http://intranet.igp.gob.pe/MI0laYWBo4/
                .post(body)
                .build();

          consulta("http://arteypixel.com/envio_notificaciones/register.php?NewToken="+valor1);


        // consulta("http://arteypixel.com/envio_notificaciones/register.php?Token="+token);
        // consulta("http://intranet.igp.gob.pe/test_erlis/test.php?Token="+token);
        // consulta("http://intranet.igp.gob.pe/AnMI0laYWBo4/index.php?Token="+token);
        // guardartoken(token);

        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void consulta(String urlString)  {
        Toast.makeText(getBaseContext(), "valor capturado : "+ urlString, Toast.LENGTH_SHORT).show();
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = null;
            BufferedReader bufferedReader = null;
            urlConnection = (HttpURLConnection) url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            json = bufferedReader.readLine();
            //  Toast.makeText(getApplicationContext(), json, Toast.LENGTH_SHORT).show();
            Log.d(TAG, "vslor json: " + json);

            ver2(json);
            urlConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //   }
        //  });
    }


    public void ver2(String valor) {



        String Message5 = valor;
        String file_namex = "capturado";
        try {
            FileOutputStream fileOutputStream = openFileOutput(file_namex, MODE_PRIVATE);
            fileOutputStream.write(Message5.getBytes());
            FirebaseMessaging.getInstance().subscribeToTopic(Message5);


            //  fileOutputStream.write(Message7.getBytes());
            fileOutputStream.close();
            //  Toast.makeText(getApplicationContext(), "Configurado", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


       // Toast.makeText(getBaseContext(), "valor capturado : "+ valor, Toast.LENGTH_SHORT).show();
    }




    public void vervalor() {
        Toast.makeText(getBaseContext(),"data : " + "el dato",Toast.LENGTH_SHORT).show();

        String file2 = "capturado";
        try {
            FileInputStream fileIn = openFileInput(file2);
            InputStreamReader InputRead = new InputStreamReader(fileIn);
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            int charRead;
            charRead = InputRead.read(inputBuffer);
            String readstring = String.copyValueOf(inputBuffer, 0, charRead);
            //k2 += readstring;
           // InputRead.close();
           Toast.makeText(getBaseContext(),"data : " + readstring,Toast.LENGTH_SHORT).show();
           // StringTokenizer st = new StringTokenizer(k.toString(), ",");
            //String ajustes = st.nextToken();
            //tipo = st.nextToken();

      //      Log.d(TAG, "valor: json: " + k);


            //Toast.makeText(getBaseContext(),"data 2 : " + tipo,Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


*/


}

