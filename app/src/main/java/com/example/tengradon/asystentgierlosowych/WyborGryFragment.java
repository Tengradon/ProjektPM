package com.example.tengradon.asystentgierlosowych;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Tengradon on 2016-06-29.
 */
public class WyborGryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.wybor_gry_fragment, container, false);
    }
}
