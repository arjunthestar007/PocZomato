package com.example.arjun.poczomato.View;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.example.arjun.poczomato.ActiviyListener;
import com.example.arjun.poczomato.DataModel.DatamodelService;
import com.example.arjun.poczomato.R;
import com.example.arjun.poczomato.ViewModel.Viewmodel;
import com.example.arjun.poczomato.model.Model.search.Location;
import com.example.arjun.poczomato.model.Model.search.Restaurant;
import com.example.arjun.poczomato.model.Model.search.Search;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment  implements OnMapReadyCallback{

    private static final int REQUEST_LOCATION_PERMISSION =1000 ;
    EditText msearchEdittext;
    //RecyclerView mrecyclerview;
    // SearchAdapter adapter;
    List<Restaurant> reslist;
    Button msearchButton;
    Switch mapSwitch;
    SupportMapFragment mapFragment;
    LinearLayout maplayout;
    private boolean mTrackingLocation;

    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback mLocationCallback;
    GoogleMap mGoogleMap;
    android.location.Location  mlocation;

    public SearchFragment() {
    }

    public static SearchFragment getInstance() {

        Bundle args = new Bundle();
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ActiviyListener listener = (ActiviyListener) getActivity();
        listener.settitle(true, "Search Fragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        msearchEdittext = view.findViewById(R.id.searcheditText);
        msearchButton = view.findViewById(R.id.searchbutton);
        //mrecyclerview = view.findViewById(R.id.searchrecyclerview);
        mapSwitch = view.findViewById(R.id.mapswitch);
        maplayout = view.findViewById(R.id.layout);
        //mapFragment=view.findViewById(R.id.mapfragment);
        mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.mapfragment);
        // Initialize the FusedLocationClient.
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(
                getActivity());

        Viewmodel viewmodel = new Viewmodel(new DatamodelService());

        msearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loc = msearchEdittext.getText().toString();


            }

        });

        msearchEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int entered = s.toString().length();
                if (entered > 3) {
                    viewmodel.getRestaurants(s.toString()).subscribe(this::setdata);
                }
//               else if (entered < 3) {
//                    if (reslist != null)
//                        adapter.clearList();
//                }

            }

            private void setdata(Search search) {

                reslist = search.getRestaurants();
                registermap();

               // getlatandlong();
//                if (!mapSwitch.isChecked()) {
//                    maplayout.setVisibility(View.GONE);
//                    adapter = new SearchAdapter(search.getRestaurants(), getActivity(), new RecyclerviewCallback() {
//                        @Override
//                        public void item(View v, int position) {
//                            Restaurant restaurant = reslist.get(position);
//                            Location location = restaurant.getRestaurant().getLocation();
//                            ActiviyListener listener = (ActiviyListener) getActivity();
//                            listener.addfragment(RestaurentFragment.getInstance());
//                        }
//                    });
//                    mrecyclerview.setHasFixedSize(true);
//                    mrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
//                    mrecyclerview.setAdapter(adapter);
//                } else {
//                    mrecyclerview.setVisibility(View.GONE);
//                    maplayout.setVisibility(View.VISIBLE);
//
//                }
            }
        });

        mLocationCallback = new LocationCallback() {
            /**
             * This is the callback that is triggered when the
             * FusedLocationClient updates your location.
             * @param locationResult The result containing the device location.
             */
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (mTrackingLocation) {
                    mlocation=locationResult.getLastLocation();


                }
            }
        };
        startTrackingLocation();
        return view;
    }

    void registermap(){
        mapFragment.getMapAsync(this);
    }

    private void filter(String text) {
        //new array list that will hold the filtered data
        ArrayList<Restaurant> filterdNames = new ArrayList<>();

        //looping through existing elements
        for (Restaurant s : reslist) {
            //if the existing elements contains the search input
            if (s.getRestaurant().getName().toLowerCase().contains(text.toLowerCase())) {
                //adding the element to filtered list
                filterdNames.add(s);
            }
        }

        //calling a method of the adapter class and passing the filtered list
        // adapter.filterList(filterdNames);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap=googleMap;

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(mlocation.getLatitude(), mlocation.getLongitude())).title("my current location")

        );
        if(reslist!=null){

            for (Restaurant restaurant : reslist) {
                Location loc = restaurant.getRestaurant().getLocation();
                loc.getLatitude();
                loc.getLatitude();
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(Double.parseDouble(loc.getLatitude()), Double.parseDouble(loc.getLongitude())))
                        .title(loc.getAddress()));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(loc.getLatitude()), Double.parseDouble(loc.getLongitude())), 10));
            }

        }

    }

    private void startTrackingLocation() {
        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        } else {
            mTrackingLocation = true;
            mFusedLocationClient.requestLocationUpdates
                    (getLocationRequest(),
                            mLocationCallback,
                            null /* Looper */);


        }
    }

    private LocationRequest getLocationRequest() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return locationRequest;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION:

                // If the permission is granted, get the location, otherwise,
                // show a Toast
                if (grantResults.length > 0
                        && grantResults[0]
                        == PackageManager.PERMISSION_GRANTED) {
                    startTrackingLocation();
                } else {
                    Toast.makeText(getActivity(),
                            "location_permission_denied",
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }    }
}
