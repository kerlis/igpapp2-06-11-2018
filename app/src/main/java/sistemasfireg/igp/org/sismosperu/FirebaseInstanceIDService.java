package sistemasfireg.igp.org.sismosperu;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import static android.content.ContentValues.TAG;
import static java.lang.System.in;

public class FirebaseInstanceIDService extends FirebaseInstanceIdService{
    String elvalor = "a";
    String elvalor2 = "bb";
    String valorcero = "0";
    String orden;
    String Message;
    String ko,ajustes,tipo;
    Integer r,s;
    String json;
    String datou = "valueu";


    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        registerToken(token);
        ver();
        contador();
        createNotificationChannel();

    }

    private void createNotificationChannel() {

       // String channelId = "some_channel_id";
       // CharSequence channelName = "Some Channel";

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.default_notification_channel_name_sismos);

            String channelId = getString(R.string.default_notification_channel_id_sismos);
            String description = getString(R.string.channel_description_sismos);

            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channelId, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }




    private void registerToken(String token) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("Token",token)
                .build();
                 Request request = new Request.Builder()
                   .url("http://intranet.igp.gob.pe/AnMI0laYWBo4/index.php?Token="+token)
                  //.url("http://arteypixel.com/envio_notificaciones/register.php?Token="+token)

                  //.url("http://intranet.igp.gob.pe/test_erlis/test.php?Token="+token)
                 //.url("http://intranet.igp.gob.pe/MI0laYWBo4/")
                 //http://intranet.igp.gob.pe/MI0laYWBo4/


                .post(body)
                .build();

                //  consulta("http://arteypixel.com/envio_notificaciones/register.php?Token="+token);
//        consulta("http://intranet.igp.gob.pe/test_erlis/test.php?Token="+token);




         consulta("http://intranet.igp.gob.pe/AnMI0laYWBo4/index.php?Token="+token);
        //   vale consulta("http://arteypixel.com/envio_notificaciones/register.php?Token="+token);
        guardartoken(token);

        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }




/* consulta e insercion de base de datos en la memoria interna
        try {
            URL url = new URL("http://intranet.igp.gob.pe/MI0laYWBo4/index.php?Token="+token);
            HttpURLConnection urlConnection = null;
            BufferedReader bufferedReader = null;
            urlConnection = (HttpURLConnection) url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            json = bufferedReader.readLine();
            //  Toast.makeText(getApplicationContext(), json, Toast.LENGTH_SHORT).show();
            Log.d(TAG, "fdfdfd json: " + json);
            ver2(json);
            urlConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

*/



    }



    public void consulta(String urlString)  {
                try {
//                    URL url = new URL("http://arteypixel.com/envio_notificaciones/register.php");

                  URL url = new URL(urlString);
                  HttpURLConnection urlConnection = null;
                  BufferedReader bufferedReader = null;
                  urlConnection = (HttpURLConnection) url.openConnection();
                  bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                  json = bufferedReader.readLine();
                  //  Toast.makeText(getApplicationContext(), json, Toast.LENGTH_SHORT).show();
                  Log.d(TAG, "fdfdfd json: " + json);
                  ver2(json);
                  urlConnection.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
         //   }
      //  });
    }
/**/


/*  consulta e insercion de base de datos en la memoria interna */
    private void ver2(String dato) {
        String Message5 = dato;
        String file_namex = "datos_ordences";
        try {
            FileOutputStream fileOutputStream = openFileOutput(file_namex, MODE_PRIVATE);
            fileOutputStream.write(Message5.getBytes());

            FirebaseMessaging.getInstance().subscribeToTopic(Message5);
            /// FirebaseMessaging.getInstance().subscribeToTopic("SISMOSANDROIDDOSTRES");


            //  fileOutputStream.write(Message7.getBytes());
            fileOutputStream.close();
            //  Toast.makeText(getApplicationContext(), "Configurado", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void guardartoken(String token) {
        String Message5 = token;
        String file_namex = "eltoken";
        try {
            FileOutputStream fileOutputStream = openFileOutput(file_namex, MODE_PRIVATE);
            fileOutputStream.write(Message5.getBytes());


          //  FirebaseMessaging.getInstance().subscribeToTopic(Message5);


            //  fileOutputStream.write(Message7.getBytes());
            fileOutputStream.close();
            //  Toast.makeText(getApplicationContext(), "Configurado", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    private void ver() {
        String Message3 = elvalor + ",";
        String Message4 = elvalor2 + ",";
        String file_name = "datos_configuracion";
        try {
            FileOutputStream fileOutputStream = openFileOutput(file_name, MODE_PRIVATE);
            fileOutputStream.write(Message3.getBytes());
            fileOutputStream.write(Message4.getBytes());
            fileOutputStream.close();
          //  Toast.makeText(getApplicationContext(), "Configurado", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void contador() {
        String Message3 = valorcero;
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
    }


}