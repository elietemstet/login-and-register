package com.example.myfragmaentapp;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Fragment_for extends Fragment {

    private EditText searchText;
    private Button searchButton;
    private TextView results;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_for, container, false);
        searchText = view.findViewById(R.id.searchName);
        searchButton = view.findViewById(R.id.searchButton);
        results = view.findViewById(R.id.searchResult);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                read();
            }
        });

        return view;
    }

    public void read(){

        // Read from the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users ");

        myRef.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                dataSnapshot.getChildren().forEach(child->{
                    Person value = child.getValue(Person.class);
                    if (value.name.equals(searchText.getText().toString())) {
                        String text = value.name +"\n"+ value.email +"\n"+ value.phone +"\n"+ value.id;
                        results.setText(text);

                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

    }
}