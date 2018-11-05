/*package sistemasfireg.igp.org.sismosperu;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import me.leolin.shortcutbadger.ShortcutBadger;


public class FirebaseMessagingNotification {
}
*/




package sistemasfireg.igp.org.sismosperu;
        import android.app.NotificationManager;
        import android.app.PendingIntent;
        import android.content.Context;
        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.net.Uri;
        import android.os.Build;
        import android.support.v4.app.NotificationCompat;

        import com.google.firebase.messaging.FirebaseMessaging;
        import com.google.firebase.messaging.RemoteMessage;
        import java.io.BufferedReader;
        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.StringTokenizer;

        import me.leolin.shortcutbadger.ShortcutBadger;

public class FirebaseMessagingNotification extends com.google.firebase.messaging.FirebaseMessagingService{
    String Message;

    String valorcontador;


    String ko,ajustes,tipo,valorinicial;
    Integer r,s;
    // String dato_referencia;
    //String dato_magnitud;
    // String dato_epicentro;

    String titletema;
    String messagetema;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        //ShortcutBadger.applyCount(FirebaseMessagingService.this, 1);

        //subscrito a tema sismos
        // FirebaseMessaging.getInstance().subscribeToTopic("SISMOS");



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

                    // if () {



                    if((remoteMessage.getNotification() != null) && (remoteMessage.getNotification().getTitle() != null) && (remoteMessage.getNotification().getTitle()  != null)){
                        titletema = remoteMessage.getNotification().getTitle(); //get title
                        messagetema = remoteMessage.getNotification().getBody(); //get message
                        sendNotification(titletema, messagetema);
                    }


                    // else if(remoteMessage.getData().get("message") != null) {
                    //  showNotification(remoteMessage.getData().get("message"));
                    //  }


                    else{

                    }

                    // }

                    if (titletema != ""){

                        // if (remoteMessage.getData().get("message") != ""){
                    //    sumar();
                        FileInputStream fileInputStream_cero =  openFileInput("valorcero");
                        InputStreamReader inputStreamReader_cero =  new InputStreamReader(fileInputStream_cero);
                        BufferedReader bufferedReader_cero = new BufferedReader(inputStreamReader_cero);
                        StringBuffer stringBuffer_cero =  new StringBuffer();
                        while ((valorcontador = bufferedReader_cero.readLine())!=null)
                        {
                            stringBuffer_cero.append(valorcontador);
                        }
                        valorinicial = stringBuffer_cero.toString();
                        Integer cero = Integer.parseInt(valorinicial);
                        // int badgeCount = 1;
                        ShortcutBadger.applyCount(FirebaseMessagingNotification.this, cero);
                    }
                    else{

                    }


                }
                else {
                    String bb = "";
                }





            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onDeletedMessages() {

    }

    //NOTIFICACION CON TEMAS
    private void sendNotification(String title, String messageBody) {
        Intent intent = new Intent(this, Ultimosismo3.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Bitmap icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.logo);

        Uri defaultSoundUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.beep2);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.raw.ic_launcherdos)
                .setLargeIcon(icon)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
    //FIN NOTIFICACION CON TEMAS




/*
        //   Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                  .setContentText("Mag " + "--- \n" + "----------" + "----" + "\n" + "----------" + "\n" + "Click para detalles - IGP" )


  .setStyle(new NotificationCompat.InboxStyle()
                .addLine("Mag " + "---")
                .addLine("----------")
                .addLine("----" + " "+ "-----------")
                .setSummaryText("Click para detalles - IGP"));


    NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setVibrate(new long[] { 1000, 1000})
            .setContentTitle("Sismo")
            .setLargeIcon(icon)
            .setContentIntent(pendingIntent)
            .setStyle(new NotificationCompat.InboxStyle()
                    .addLine("Mag " + dato_magnitud)
                    .addLine(dato_referencia)
                    .addLine(dato_fecha + " "+ dato_hora_local)
                    .setSummaryText("Click para detalles - IGP"));
    NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                manager.notify(0,builder.build());



      .setStyle(new NotificationCompat.InboxStyle()
                .addLine("Mag " + dato_magnitud)
                .addLine(dato_referencia)
                .addLine(dato_fecha + " "+ dato_hora_local)
                .setSummaryText("Click para detalles - IGP"));
    NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setVibrate(new long[] { 1000, 1000})
            .setContentTitle("Sismo")
            .setLargeIcon(icon)
            .setContentIntent(pendingIntent)
            .setStyle(new NotificationCompat.InboxStyle()
                    .addLine("Mag " + dato_magnitud)
                    .addLine(dato_referencia)
                    .addLine(dato_fecha + " "+ dato_hora_local)
                    .setSummaryText("Click para detalles - IGP"));
    NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                manager.notify(0,builder.build());

            */





    private void sumar() throws IOException {
        FileInputStream fileInputStream_cero =  openFileInput("valorcero");
        InputStreamReader inputStreamReader_cero =  new InputStreamReader(fileInputStream_cero);
        BufferedReader bufferedReader_cero = new BufferedReader(inputStreamReader_cero);
        StringBuffer stringBuffer_cero =  new StringBuffer();
        while ((valorcontador = bufferedReader_cero.readLine())!=null)
        {
            stringBuffer_cero.append(valorcontador);
        }
        valorinicial = stringBuffer_cero.toString();
        Integer cero = Integer.parseInt(valorinicial);

        Integer suma = cero + 1;
        String valor = String.valueOf(suma);

        // String Message3 = valorcero;
        String file_name = "valorcero";

        try {
            FileOutputStream fileOutputStream = openFileOutput(file_name, MODE_PRIVATE);
            fileOutputStream.write(valor.getBytes());
            fileOutputStream.close();
            //  Toast.makeText(getApplicationContext(), "Configurado", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void showNotification(String message) {
        Intent i = new Intent(this,Ultimosismo3.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        String magnitud = message.split(",")[0];
        String epicentro = message.split(",")[1];
        String referencia = message.split(",")[2];
        String fecha = message.split(",")[3];
        String hora_local = message.split(",")[4];
        String latitud = message.split(",")[5];
        String longitud = message.split(",")[6];

        String dato_magnitud;
        String dato_epicentro;
        String dato_referencia;
        String dato_fecha;
        String dato_hora_local;
        String dato_latitud;
        String dato_longitud;

        if (latitud.length() < 2){
            dato_latitud = "nulo";
        }
        else{
            dato_latitud = magnitud;
        }


        if (longitud.length() < 2){
            dato_longitud = "nulo";
        }
        else{
            dato_longitud = magnitud;
        }





        if (magnitud.length() == 0){
            dato_magnitud = "nulo";
        }
        else{
            dato_magnitud = magnitud;
        }

        if (epicentro.length() == 0){
            dato_epicentro = "nulo";

        }
        else{

            dato_epicentro = epicentro;

        }

        if (referencia.length() == 0){
            dato_referencia = "nulo";

        }
        else{

            dato_referencia = referencia;

        }

        if (fecha.length() == 0){
            dato_fecha = "nulo";

        }

        else{
            dato_fecha = fecha;

        }

        if (hora_local.length() == 0){
            dato_hora_local = "nulo";

        }
        else{
            dato_hora_local = hora_local;

        }

        Intent notificationIntent = new Intent(Intent.ACTION_VIEW);
        notificationIntent.setData(Uri.parse(dato_referencia));
        PendingIntent pi = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);
        Bitmap icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.logo);
        Uri defaultSoundUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.beep2);


        if(dato_latitud != "nulo" && dato_longitud != "nulo"){
            if (s == 1){

                NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setVibrate(new long[] { 1000, 1000})
                        .setContentTitle("Sismo")
                        .setLargeIcon(icon)
                        .setContentIntent(pendingIntent)
                        .setStyle(new NotificationCompat.InboxStyle()
                                .addLine("Mag " + dato_magnitud)
                                .addLine(dato_referencia)
                                .addLine(dato_fecha + " "+ dato_hora_local)
                                .setSummaryText("Click para detalles - IGP"));
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                manager.notify(0,builder.build());
            }
            else
            {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentTitle("Sismo")
                        .setLargeIcon(icon)
                        .setContentIntent(pendingIntent)
                        .setStyle(new NotificationCompat.InboxStyle()
                                .addLine("Mag " + dato_magnitud)
                                .addLine(dato_referencia)
                                .addLine(dato_fecha + " "+ dato_hora_local)
                                .setSummaryText("Click para detalles - IGP"));
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                manager.notify(0,builder.build());
            }
        }




        else{
            if (s == 1){
                // String filtrado = message.replace(",", "");

                String titulo = message.split(",")[0];
                String contenido = message.split(",")[1];
                String url = message.split(",")[2];
                String fechax = message.split(",")[3];
                String hora_localx = message.split(",")[4];
                String latitudx = message.split(",")[5];
                String longitudx = message.split(",")[6];



                //    $titulo,$contenido,$url,$fecha,$hora_local,$latitud,$longitud

                //  String dato_magnitud;
                // String dato_epicentro;
                // String dato_referencia;
                //  String dato_fecha;
                //  String dato_hora_local;
                //  String dato_latitud;
                //    String dato_longitud;

                NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setVibrate(new long[] { 1000, 1000})
                        .setContentTitle("Sismos Perú")
                        .setLargeIcon(icon)
                        .setContentIntent(pi)
                        .setStyle(new NotificationCompat.InboxStyle()
                                .addLine(titulo)
                                .addLine(contenido)
                                .addLine(contenido)
                                .setSummaryText("Click para ver contenido - IGP"));
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                manager.notify(0,builder.build());
            }
            else
            {
                //   String filtrado = message.replace(",", "");

                String titulo = message.split(",")[0];
                String contenido = message.split(",")[1];
                String url = message.split(",")[2];
                String fechax = message.split(",")[3];
                String hora_localx = message.split(",")[4];
                String latitudx = message.split(",")[5];
                String longitudx = message.split(",")[6];

                NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentTitle("Sismos Perú")
                        .setLargeIcon(icon)
                        .setContentIntent(pi)
                        .setStyle(new NotificationCompat.InboxStyle()
                                .addLine(titulo)
                                .addLine(contenido)
                                .addLine(contenido)
                                .setSummaryText("Click para ver contenido - IGP"));
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                manager.notify(0,builder.build());
            }
        }

    }
}
