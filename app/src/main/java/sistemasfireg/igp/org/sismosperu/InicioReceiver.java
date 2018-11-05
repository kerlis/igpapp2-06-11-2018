package sistemasfireg.igp.org.sismosperu;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
public class InicioReceiver extends BroadcastReceiver {
    public InicioReceiver() {
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        Intent myIntent = new Intent(context, SplashScreen.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(myIntent);
    }
}
