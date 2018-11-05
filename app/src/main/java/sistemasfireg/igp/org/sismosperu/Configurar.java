package sistemasfireg.igp.org.sismosperu;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.google.firebase.iid.FirebaseInstanceId;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import me.leolin.shortcutbadger.ShortcutBadger;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class Configurar extends AppCompatActivity {
    TextView datos;
    RadioButton notificar;
    RadioButton nonotificar;
    String token;
    String valor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar);

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

        token = FirebaseInstanceId.getInstance().getToken();
        datos = (TextView) findViewById(R.id.titutlo);
        notificar = (RadioButton) findViewById(R.id.notificar);
        nonotificar = (RadioButton) findViewById(R.id.nonotificar);
        datos.setText(token);
        enviardatos(token,valor);


        RadioGroup rg = (RadioGroup) findViewById(R.id.radio);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                switch(checkedId)
                {
                    case R.id.notificar:
                        valor = "1";
                        enviardatos(token,valor);
                        break;
                    case R.id.nonotificar:
                        valor = "0";
                        enviardatos(token, valor);
                        break;
                }
            }
        });
    }

    private void enviardatos(String token,String valor) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("Token",token)
                .add("Valor",valor)
                .build();
        Request request = new Request.Builder()
                .url("http:www.arteypixel.com/envio_notificaciones/register.php")
                // .url("http:www.arteypixel.com/firebase/register.php")
                // .url("http:sismos-app.igp.gob.pe/register.php")
                .post(body)
                .build();

        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
