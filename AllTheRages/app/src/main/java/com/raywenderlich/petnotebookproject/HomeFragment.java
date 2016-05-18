package com.raywenderlich.petnotebookproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.raywenderlich.alltherages.R;

public class HomeFragment extends Fragment implements View.OnClickListener{

    public HomeFragment() {
        // Required empty public constructor
    }
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        Button aboutBtn = (Button) rootView.findViewById(R.id.listButton);
   //     Button addBtn = (Button) rootView.findViewById(R.id.addButton);
        aboutBtn.setOnClickListener(this);
     //   addBtn.setOnClickListener(this);
        return rootView;

    }

    @Override
    public void onClick(View v) {
            switch (v.getId()) {
                case R.id.listButton:
                    //what to put here
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.addToBackStack(null);
                    ft.replace(R.id.root_layout, new PetListFragment(), "fragment_screen");
                    ft.commit();
                    break;
//                case R.id.addButton:
//                    FragmentManager fm1 = getFragmentManager();
//                    FragmentTransaction ft1 = fm1.beginTransaction();
//                    ft1.addToBackStack(null);
//                    ft1.replace(R.id.root_layout, new AddFragment(),"Fragment");
//                    ft1.commit();
//                    break;
            }
       }
}
