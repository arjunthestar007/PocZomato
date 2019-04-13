package com.example.arjun.poczomato.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arjun.poczomato.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurentFragment extends Fragment {


    public RestaurentFragment() {
        // Required empty public constructor
    }

    public static RestaurentFragment getInstance() {

        Bundle args = new Bundle();

        RestaurentFragment fragment = new RestaurentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_restaurent, container, false);
        return view;
    }

}
