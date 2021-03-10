package drone.hub;

        import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

//import com.example.drona.MainActivity;
//import com.example.drone.R;

public class MapsActivity<stringRequest> extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap = null;

    private Button button4;
    private Button button5;
    private Button button;
    private Button button11;
    private Button button7;
    private Button button10;

    final int[] radius_nr = {0};
    final int[] a = {0};
    final int[] altitudinea_nr = {0};
    final int[] last_radius = {100};
    private int ACCESS_LOCATION_REQUEST_CODE = 10001;







    Handler h = new Handler();
    Runnable r = new Runnable() {
        @Override
        public void run() {

        }
    };

    FusedLocationProviderClient fusedLocationProviderClient;


    //private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_maps, container, false);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());


        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        String server_url = "http://95.65.7.88:8080/server/writefileapp.php?data=none";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,

                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        TextView text = (TextView) getView().findViewById(R.id.textView14);
                        text.setText(response);
                        text.setText("none");
                        requestQueue.stop();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                TextView text = (TextView) getView().findViewById(R.id.textView14);
                text.setText("MESAJUL NU A FOST TRIMIS !");
                error.printStackTrace();
                requestQueue.stop();
            }
        });
        requestQueue.add(stringRequest);




        return v;


    }

    //@Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //mFusedLocationClient = LocationServices.getFusedLocationProviderClient();

        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);
        button4 = view.findViewById(R.id.button4);
        button5 = view.findViewById(R.id.button5);
        button7 = view.findViewById(R.id.button7);
        button = view.findViewById(R.id.button);
        button10 = view.findViewById((R.id.button10));
        button11 = view.findViewById((R.id.button11));


        button4.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                String server_url = "http://95.65.7.88:8080/server/writefileapp.php?data=DECOLARE";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,

                        new com.android.volley.Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                TextView text = (TextView) view.findViewById(R.id.textView14);
                                text.setText(response);
                                text.setText("DECOLARE");
                                requestQueue.stop();

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        TextView text = (TextView) view.findViewById(R.id.textView14);
                        text.setText("MESAJUL NU A FOST TRIMIS !");
                        error.printStackTrace();
                        requestQueue.stop();
                    }
                });
                requestQueue.add(stringRequest);


            }
        });
        button5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                String server_url = "http://95.65.7.88:8080/server/writefileapp.php?data=REVENIRE_ACASA";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,

                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                TextView text = (TextView) view.findViewById(R.id.textView14);
                                text.setText(response);
                                text.setText("REVENIRE ACASĂ");
                                requestQueue.stop();


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        TextView text = (TextView) view.findViewById(R.id.textView14);
                        text.setText("MESAJUL NU A FOST TRIMIS !");
                        error.printStackTrace();
                        requestQueue.stop();
                    }
                });
                requestQueue.add(stringRequest);
            }
        });


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                String server_url = "http://95.65.7.88:8080/server/writefileapp.php?data=ATERIZARE_FORTATA";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,

                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                TextView text = (TextView) view.findViewById(R.id.textView14);
                                text.setText(response);
                                text.setText("ATERIZARE FORȚATĂ");
                                requestQueue.stop();


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        TextView text = (TextView) view.findViewById(R.id.textView14);
                        text.setText("MESAJUL NU A FOST TRIMIS !");
                        error.printStackTrace();
                        requestQueue.stop();
                    }
                });
                requestQueue.add(stringRequest);
            }
        });


    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    private void setupMap() {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        Switch aSwitch;
        Switch aaSwitch;
        final int[] nr_de_markere = {0};


        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {


            @Override
            public void onMapClick(LatLng latLng) {
                if (nr_de_markere[0] == 0) {
                    nr_de_markere[0] = 1;
                    mMap.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Destinație")
                            .draggable(true)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))

                    );


                    button11.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {

                            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                            String server_url = "http://95.65.7.88:8080/server/writefileapp.php?data=C" + latLng;
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,

                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            TextView text = (TextView) getView().findViewById(R.id.textView14);
                                            text.setText(response);
                                            text.setText("GO TO COORDINATES");

                                            requestQueue.stop();


                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    TextView text = (TextView) getView().findViewById(R.id.textView14);

                                    text.setText("MESAJUL NU A FOST TRIMIS!");
                                    error.printStackTrace();
                                    requestQueue.stop();
                                }
                            });
                            requestQueue.add(stringRequest);


                        }
                    });


                }

            }
        });
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                LatLng latLng = marker.getPosition();
                button11.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {

                        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                        String server_url = "http://95.65.7.88:8080/server/writefileapp.php?data=C" + latLng;
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,

                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        TextView text = (TextView) getView().findViewById(R.id.textView14);
                                        text.setText(response);
                                        text.setText("GO TO COORDINATES");

                                        requestQueue.stop();


                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                TextView text = (TextView) getView().findViewById(R.id.textView14);

                                text.setText("MESAJUL NU A FOST TRIMIS!");
                                error.printStackTrace();

                                error.printStackTrace();
                                requestQueue.stop();
                            }
                        });
                        requestQueue.add(stringRequest);


                    }
                });


            }
        });


        final String[] latitudinea = {""};
        final String[] longitudinea = {""};
        final int[] checker = {0};
        final int[] stop = {0};
        final String[] volt = {""};
        final String[] altitudinea = {""};
        final String[] sateliti = {""};
        final String[] semnal = {""};
        final String[] response_salvat = {"a"};


        final double[] latitude = {47.0634159};
        final LatLng[] primele_coordonate = {null};
        final double[] longitude = {28.867351};
        final double[] latitudinea_numar = {0};

        final double[] longitudinea_numar = {0};
        final double[] last_latitude = {47.0634159};
        final double[] last_longitude = {28.867351};
        final int[] kill = {0};


        //googleMap.setBuildingsEnabled(true);


        mMap.moveCamera(CameraUpdateFactory.zoomTo(13.5f));
        LatLng drone1 = new LatLng(latitude[0], longitude[0]);
        Marker marker = mMap.addMarker(new MarkerOptions().position(drone1)
                .title("Drone1")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))

        );
        mMap.moveCamera(CameraUpdateFactory.newLatLng(drone1));
        //primele_coordonate[0] = new LatLng(latitude[0], longitude[0]);


        int delay = 100;
        final int[] aaa = {0};
        final long[] startTime = new long[1];
        final int[] number_of_clicks = {0};
        final int[] nr_text = {1};

        CircleOptions circley = new CircleOptions()
                .center(new LatLng(latitude[0], longitude[0]))
                .radius(radius_nr[0]); // in meters

        final Circle circle = mMap.addCircle(circley);


        r = new Runnable() {

            public void run() {
                // Log.v("Testing", "heere222");
                if (getView() != null) {


                    button10.setOnClickListener(new View.OnClickListener() {


                        public void onClick(View v) {
                            number_of_clicks[0]++;

                            if (number_of_clicks[0] == 2) {
                                button10.setText("FOLLOW ME");
                                TextView text = (TextView) getView().findViewById(R.id.textView14);

                                text.setText("UNFOLLOW ME");
                                nr_text[0] = 1;


                                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                    return;
                                }
                                number_of_clicks[0] = 0;
                                mMap.setMyLocationEnabled(false);

                            }








                        }
                    });

                    if(number_of_clicks[0] == 1){
                        button10.setText("UNFOLLOW ME");

                        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                            enableUserLocation();
                            Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();
                            locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
                                @Override
                                public void onSuccess(Location location) {
                                    Activity a = getActivity();
                                    if (isAdded() && a != null) {
                                        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

                                        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());


                                        String server_url = "http://95.65.7.88:8080/server/writefilelocatiamea.php?data=Follow_Locatia_Mea_" + latLng;
                                        RequestQueue finalRequestQueue = requestQueue;
                                        RequestQueue finalRequestQueue1 = requestQueue;

                                        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,
                                                new Response.Listener<String>() {
                                                    @Override
                                                    public void onResponse(String response) {

                                                        if(nr_text[0] == 1){
                                                            TextView text = (TextView) getView().findViewById(R.id.textView14);
                                                            text.setText(response);
                                                            text.setText("FOLLOW ME");
                                                            nr_text[0] = 2;
                                                        }





                                                        finalRequestQueue.stop();


                                                    }
                                                }, new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                TextView text = (TextView) getView().findViewById(R.id.textView14);
                                                text.setText("MESAJUL NU A FOST TRIMIS!");

                                                error.printStackTrace();
                                                finalRequestQueue1.stop();
                                            }
                                        });
                                        requestQueue.add(stringRequest);
                                    }
                                }
                            });

                        } else {
                            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
                                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_LOCATION_REQUEST_CODE);
                            } else {
                                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_LOCATION_REQUEST_CODE);
                            }
                        }
                    }





                    final NumberPicker numberPicker = getView().findViewById(R.id.numberPicker);
                    int start = 100;
                    String[] numbers = new String[100];
                    for (int i = 0; i < 100; i++) {
                        numbers[i] = start + "";
                        start = start + 100;
                    }

                    numberPicker.setMinValue(1);
                    numberPicker.setMaxValue(100);
                    numberPicker.setDisplayedValues(numbers);

                    last_radius[0] = radius_nr[0];
                    numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

                        @Override
                        public void onValueChange(NumberPicker numberPicker, int i, int i2) {

                            radius_nr[0] = i2 * 100;


                            circle.setCenter(new LatLng(latitude[0], longitude[0]));
                            circle.setRadius(radius_nr[0]);
                            circle.setStrokeColor(Color.RED);


                        }
                    });


                    final NumberPicker numberPicker2 = getView().findViewById(R.id.numberPicker2);


                    numberPicker2.setMinValue(1);
                    numberPicker2.setMaxValue(200);

                    numberPicker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

                        @Override
                        public void onValueChange(NumberPicker numberPicker2, int i, int i2) {

                            altitudinea_nr[0] = i2;


                        }
                    });


                    button7.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                            String server_url = "http://95.65.7.88:8080/server/writefileapp.php?data=AL"+altitudinea_nr[0];
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,

                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            TextView text = (TextView) getActivity().findViewById(R.id.textView14);
                                            text.setText(response);
                                            text.setText("ALTITUDINEA A FOST SETATĂ");
                                            requestQueue.stop();


                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    TextView text = (TextView) getActivity().findViewById(R.id.textView14);
                                    text.setText("MESAJUL NU A FOST TRIMIS !");
                                    error.printStackTrace();
                                    requestQueue.stop();
                                }
                            });
                            requestQueue.add(stringRequest);
                        }
                    });

                    RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                    String server_url = "http://95.65.7.88:8080/server/datastorage.txt";
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,

                            new Response.Listener<String>() {

                                @Override
                                public void onResponse(String response) {
                                    for (int i = 0; i < response.length(); i++) {
                                        response_salvat[0] += response.charAt(i);
                                    }
                                    for (int i = 0; i < response.length(); i++) {
                                        if (response.charAt(i) == 'L') {
                                            latitudinea[0] = "";
                                            checker[0] = 1;
                                            i++;
                                        }
                                        if (response.charAt(i) == 'W') {
                                            longitudinea[0] = "";
                                            checker[0] = 2;
                                            i++;
                                        }
                                        if (response.charAt(i) == 'A') {

                                            altitudinea[0] = "";
                                            checker[0] = 3;
                                            i++;
                                        }
                                        if (response.charAt(i) == 'V') {
                                            volt[0] = "";
                                            checker[0] = 4;
                                            i++;
                                        }
                                        if (response.charAt(i) == 'S') {
                                            sateliti[0] = "";
                                            checker[0] = 5;
                                            i++;
                                        }
                                        if (response.charAt(i) == 'R') {
                                            semnal[0] = "";
                                            checker[0] = 6;
                                            i++;
                                        }
                                        if (checker[0] == 1) {
                                            latitudinea[0] += response.charAt(i);
                                        }
                                        if (checker[0] == 2) {
                                            longitudinea[0] += response.charAt(i);
                                        }
                                        if (checker[0] == 3) {
                                            altitudinea[0] += response.charAt(i);
                                        }
                                        if (checker[0] == 4) {
                                            volt[0] += response.charAt(i);
                                        }
                                        if (checker[0] == 5) {
                                            sateliti[0] += response.charAt(i);
                                        }
                                        if (checker[0] == 6) {
                                            semnal[0] += response.charAt(i);
                                        }
                                    }

                                    response = "";


                                    try {
                                        TextView text = (TextView) getView().findViewById(R.id.textView7);
                                        text.setText(String.format(volt[0]));
                                        TextView text1 = (TextView) getView().findViewById(R.id.textView5);
                                        text1.setText(String.format(semnal[0]));
                                        TextView text2 = (TextView) getView().findViewById(R.id.textView10);
                                        text2.setText(String.format(altitudinea[0]));
                                        TextView text3 = (TextView) getView().findViewById(R.id.textView12);
                                        text3.setText(String.format(sateliti[0]));
                                        TextView text6 = (TextView) getView().findViewById(R.id.textView28);
                                        text6.setText(String.format(String.valueOf((System.nanoTime()-startTime[0])/1000000000))+" sec.");
                                        aaa[0] = 1;
                                        requestQueue.stop();
                                        //ap 413, damafn 413 scara 6 florilor 28/2;

                                    } catch (Exception e) {
                                        requestQueue.stop();

                                    }




                                }



                            }
                            , new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            try {


                                if(aaa[0] == 1){
                                    startTime[0] = System.nanoTime();
                                    aaa[0] = 0;
                                }




//                                TextView text = (TextView) getView().findViewById(R.id.textView7);
//                                text.setText("ER");
//                                TextView text1 = (TextView) getView().findViewById(R.id.textView5);
//                                text1.setText("ER");
//                                TextView text2 = (TextView) getView().findViewById(R.id.textView10);
//                                text2.setText("ER");
//                                TextView text3 = (TextView) getView().findViewById(R.id.textView12);
//                                text3.setText("ER");
                                Log.v("Handler Error", "Eroare");
                            } catch (Exception e) {
                                // Log.v("Handler Error", "Eroare");
                            }

                            error.printStackTrace();
                            requestQueue.stop();
                        }


                    });

                    requestQueue.add(stringRequest);


                    LatLng drone1 = new LatLng(latitude[0], longitude[0]);
                    marker.setPosition(new LatLng(latitude[0], longitude[0]));
                    //googleMap.moveCamera(CameraUpdateFactory.newLatLng(drone1));


                    if (latitudinea[0] != "" && longitudinea[0] != "") {
                        latitudinea_numar[0] = Double.parseDouble(latitudinea[0]);
                        longitudinea_numar[0] = Double.parseDouble(longitudinea[0]);

                        latitude[0] = latitudinea_numar[0];
                        longitude[0] = longitudinea_numar[0];
                        last_latitude[0] = latitudinea_numar[0];
                        last_longitude[0] = longitudinea_numar[0];


                        if ((a[0] == 1) && (Integer.parseInt(sateliti[0]) >= 13)) {

                            primele_coordonate[0] = new LatLng(latitude[0], longitude[0]);
                            Log.v("Primele Coordonate: ", String.valueOf(primele_coordonate[0]));
                            a[0] = 2;
                        }


                    }


                    if (response_salvat[0].charAt(0) != 'L') {
                        latitude[0] = last_latitude[0];
                        longitude[0] = last_longitude[0];


                    }

                    if (response_salvat[0].length() > 2 && response_salvat[0].charAt(1) == 'C' && semnal[0] == "") {

                        TextView text27 = (TextView) getView().findViewById(R.id.textView22);
                        text27.setText("Conecțiune Pierdută !");
                    }
                    if (sateliti[0] != "") {
                        TextView text27 = (TextView) getView().findViewById(R.id.textView22);
                        text27.setText("");
                    }


                    latitudinea[0] = "";
                    longitudinea[0] = "";
                    sateliti[0] = "";
                    volt[0] = "";
                    altitudinea[0] = "";
                    semnal[0] = "";
                    response_salvat[0] = "a";
                    checker[0] = 0;


                }

                h.postDelayed(this, delay);

            }


        };


        h.postDelayed(r, delay);


        aSwitch = (Switch) getView().findViewById(R.id.switch8);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    kill[0] = 1;
                }

            }
        });

        aaSwitch = (Switch) getView().findViewById(R.id.switch9);

        aaSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true && kill[0] == 1) {
                    kill[0] += 1;


                    if (kill[0] == 2) {
                        kill[0] = 0;
                        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                        String server_url = "http://95.65.7.88:8080/server/writefileapp.php?data=KILL";
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,

                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        TextView text = (TextView) getView().findViewById(R.id.textView14);
                                        text.setText(response);
                                        text.setText("KILL");
                                        requestQueue.stop();


                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                try {
                                    TextView text = (TextView) getView().findViewById(R.id.textView14);
                                    text.setText("MESAJUL NU A FOST TRIMIS !");
                                } catch (Exception e) {
                                }
                                error.printStackTrace();
                                requestQueue.stop();
                            }

                        });
                        requestQueue.add(stringRequest);

                    }

                }

            }
        });

    }

    public void enableUserLocation() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        mMap.setMyLocationEnabled(true);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == ACCESS_LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                enableUserLocation();

            } else {
                //We can show a dialog that permission is not granted...
            }
        }
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        h.removeCallbacks(r);
        mMap.setMyLocationEnabled(false);

    }
}