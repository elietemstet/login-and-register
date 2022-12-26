package com.example.myfragmaentapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;


public class FragmentOne extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);

        Button button42 = view.findViewById(R.id.buttonForTwo);

        button42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_fragmentOne_to_fragmentTwo);
            //MainActivity mainActivity = (MainActivity) getActivity();
            //mainActivity.login();
            }
        });

        Button button43 = view.findViewById(R.id.buttonForT);

        button43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(R.id.action_fragmentOne_to_fragmentThree);

                //MainActivity mainActivity = (MainActivity) getActivity();
                //mainActivity.reg();
            }
        });


        return view;
    }
}