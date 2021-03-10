package drone.hub;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.hardware.usb.UsbDevice;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.IccOpenLogicalChannelResponse;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import me.aflak.arduino.Arduino;
import me.aflak.arduino.ArduinoListener;

public class MainActivity3 extends FragmentActivity implements OnMapReadyCallback,ArduinoListener {
    private TextView textView;
    private Arduino arduino;
    GoogleMap map;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById((R.id.map));
        mapFragment.getMapAsync(this);

        textView = findViewById(R.id.textView);
        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        MediaPlayer GM = MediaPlayer.create(this, R.raw.gm);
        MediaPlayer GA = MediaPlayer.create(this, R.raw.ga);
        MediaPlayer GE = MediaPlayer.create(this, R.raw.ge);

        if (currentTime.contains("00")){
            GM.start();
        }
        if (currentTime.contains("01")){
            GM.start();
        }
        if (currentTime.contains("10")){
           GM.start();
        }
        if (currentTime.contains("11")){
            GM.start();
        }
        if (currentTime.contains("12")){
            GA.start();
        }
        if (currentTime.contains("13")){
            GA.start();
        }
        if (currentTime.contains("14")){
            GA.start();
        }
        if (currentTime.contains("15")){
            GA.start();
        }
        if (currentTime.contains("16")){
            GA.start();
        }
        if (currentTime.contains("17")){
            GA.start();
        }
        if (currentTime.contains("18")){
            GE.start();
        }
        if (currentTime.contains("19")){
            GE.start();
        }
        if (currentTime.contains("20")){
            GE.start();
        }
        if (currentTime.contains("21")){
            GE.start();
        }
        if (currentTime.contains("22")){
            GE.start();
        }
        if (currentTime.contains("23")){
            GE.start();
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng HUB = new LatLng(47.063425014603325, 28.867350998331393);
        map.addMarker(new MarkerOptions().position(HUB).title("Chisinau"));
        new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.black_base));
        map.moveCamera(CameraUpdateFactory.newLatLng(HUB));
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        float zoomLevel = 16.0f; //21
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(HUB, zoomLevel));
    }
    @Override
    protected void onStart() {
        super.onStart();
        arduino.setArduinoListener(this);
    }

    @Override
    public void onArduinoAttached(UsbDevice device) {
        textView.setText("Connected");
        arduino.open(device);
    }

    @Override
    public void onArduinoDetached() {
        textView.setText("Connection lost");
    }

    @Override
    public void onArduinoMessage(byte[] bytes) {

    }

    @Override
    public void onArduinoOpened() {

    }

    @Override
    public void onUsbPermissionDenied() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                arduino.reopen();
            }
        }, 3000);
        arduino.reopen();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        arduino.unsetArduinoListener();
        arduino.close();
    }
}

