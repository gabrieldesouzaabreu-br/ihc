package com.example.atividade2_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(MainActivity.this, new
                String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
    }

    public void showLatLong(View v){
        GPSTracker g = new GPSTracker(getApplicationContext());
        Location l = g.getLocation();
        if(l!=null)
        {
            double lat = l.getLatitude();
            double longi = l.getLongitude();
            Toast.makeText(getApplicationContext(), "LAT: "+lat + "LONG: " +
                    longi, Toast.LENGTH_LONG).show();
        }
    }
}

class GPSTracker implements LocationListener {
    Context context;
    public GPSTracker(Context c)
    {
        context = c;
    }
    public Location getLocation(){
        if(ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(context, "Permission not granted", Toast.LENGTH_LONG).show();
            return null;
        }
        LocationManager lm = (LocationManager)
                context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(isGPSEnabled)
        {
//            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10, this);
//            Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            List<String> providers = lm.getProviders(true);
            Location bestLocation = null;
            for (String provider : providers) {
                Location l = lm.getLastKnownLocation(provider);
                if (l == null) {
                    continue;
                }
                if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                    bestLocation = l;
                }
            }
            return bestLocation;
        }else
        {
            Toast.makeText(context, "Please enable GPS", Toast.LENGTH_LONG).show();
        }
        return null;
    }


    @Override
    public void onLocationChanged(Location location) {}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onProviderDisabled(String provider) {}
}
