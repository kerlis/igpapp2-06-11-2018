package sistemasfireg.igp.org.sismosperu;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Locale;
import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
public class Getlocationandmore extends Activity {
    Button btnShowLocation,picksound;
    GPSTracker gps;
    Uri ringtone;

    /*
    String longitudcmp1 = "-12.8120534";
    String longitudcmp2 = "-76.5416381";

   // https://www.google.com/maps/@-12.8120534,-76.5416381,13z
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_location);

        picksound = (Button) findViewById(R.id.picksound);
        picksound.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                 pickRingtone();

            }
        });




        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);
        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // create class object
                gps = new GPSTracker(Getlocationandmore.this);
                // check if GPS enabled
                if(gps.canGetLocation()){
                    double longitude = gps.getLongitude();
                    double latitude = gps.getLatitude();
                    getAddress(latitude, longitude);
                }

                else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }

            }
        });
    }


    // public void pickRingtone(View view) {


   public void pickRingtone() {

        // TODO Auto-generated method.   stub
        Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE,
                RingtoneManager.TYPE_RINGTONE);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select Ringtone");

      // intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, Uri.parse(strUri));

        // for existing ringtone
        Uri urie =     RingtoneManager.getActualDefaultRingtoneUri(
                getApplicationContext(), RingtoneManager.TYPE_NOTIFICATION);

        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, urie);
        Toast.makeText(getApplicationContext(), "sonido elegido:  " +  urie , Toast.LENGTH_LONG).show();
        startActivityForResult(intent, 5);

    }


     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
                    ringtone = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
                    Toast.makeText(getApplicationContext(), "ringtone :  " +  RingtoneManager.URI_COLUMN_INDEX, Toast.LENGTH_LONG).show();

                 //   Toast.makeText(getBaseContext(),RingtoneManager.URI_COLUMN_INDEX,
                    //  Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }
        }
    }



    private void getAddress(double latitude, double longitude) {


        double distance;
        Location locationA = new Location("point A");
        locationA.setLatitude(latitude);
        locationA.setLongitude(longitude);

        Location locationB = new Location("point B");
        locationB.setLatitude(Double.parseDouble(longitudcmp1));
        locationB.setLongitude(Double.parseDouble(longitudcmp2));

        distance = locationA.distanceTo(locationB)/1000;

        Toast.makeText(getApplicationContext(), "la distancia es de :  " +  distance +  "km", Toast.LENGTH_LONG).show();

      //  Toast.makeText(getApplicationContext(), "latitud  " + String.valueOf(latitude) + " ," +   "longitud:"  + "," + String.valueOf(longitude) , Toast.LENGTH_LONG).show();



        StringBuilder result = new StringBuilder();
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                result.append(address.getLocality()).append("\n");
                //result.append(address.getPostalCode());
                result.append(address.getAdminArea()).append("\n");
                result.append(address.getCountryName());
                String address1 = addresses.get(0).getAddressLine(0);
                String city = addresses.get(0).getAddressLine(1);
                String country = addresses.get(0).getAddressLine(2);
                Toast.makeText(getApplicationContext(), "tu direccion es   " + address1 + " ," +   city + "," + country , Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }
    }

    */

}
