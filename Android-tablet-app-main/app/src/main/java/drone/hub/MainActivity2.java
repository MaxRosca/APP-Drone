package drone.hub;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import me.aflak.arduino.Arduino;
import me.aflak.arduino.ArduinoListener;

//import android.support.v7.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity implements ArduinoListener {
    private static final String TAG = "onArduinoMessage" ;
    private Arduino arduino;
    private Button button4;
    private Button button3;
    private TextView textView;
    private TextView textView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView2 = (TextView)  findViewById(R.id.textView2);
        textView2.setMovementMethod(new ScrollingMovementMethod());
       // EditText editText = (EditText) findViewById(R.id.textView3);



        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = ("a");
                arduino.send(str.getBytes());
            }
        });
        arduino = new Arduino(this);
    }





    public void openActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onStart() {
        super.onStart();
        arduino.setArduinoListener(this);
    }
   // @SuppressLint("SetTextI18n")
    @Override
    public void onArduinoAttached(UsbDevice device) {
        textView.setText("Connected");
        arduino.open(device);

    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onArduinoDetached() {
        textView.setText("Connection lost");
    }

   @SuppressLint("SetTextI18n")
    @Override
    public void onArduinoMessage(byte[] bytes) {
       String message = new String(bytes);
       textView2.setText(message);
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


