package com.example.arjun.poczomato;

import android.content.Context;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {
    FragmentListener listener;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener= (FragmentListener) context;
    }

    interface FragmentListener {
        void addFragment(Fragment mfragment);
    }
}
