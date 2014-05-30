package com.project.navigationdrawer.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.navigationdrawer.R;

 
public class BreakingBadFragment extends Fragment {
     
    public BreakingBadFragment(){}
     
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.fragment_breaking_bad, container, false);

        return rootView;
    }
}
