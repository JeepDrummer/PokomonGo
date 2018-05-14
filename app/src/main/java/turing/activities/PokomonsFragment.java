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

public class PokomonsFragment extends Fragment {
    public PokomonsFragment() {
    }

    public static PokomonsFragment newInstance() {
        return new PokomonsFragment();
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

        View view = inflater.inflate(R.layout.fragment_pokomon_grid, container, false);

        return view;
    }
}
