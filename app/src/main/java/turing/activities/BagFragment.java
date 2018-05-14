package turing.activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import turing.pokomongo.R;

/**
 * Created by crespeau on 13/04/2018.
 */

public class BagFragment extends Fragment {
    public BagFragment() {
    }

    public static BagFragment newInstance() {
        return new BagFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("lol","onCreate Frag");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.d("lol","onCreateView Frag");
        Log.d("lol", "MapFragment : onCreateView");

        View view = inflater.inflate(R.layout.fragment_bag, container, false);

        return view;
    }
}
