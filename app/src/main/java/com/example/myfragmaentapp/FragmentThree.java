package com.example.myfragmaentapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;


public class FragmentThree extends Fragment {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private EditText emailT;
    private  EditText passT;
    private EditText name;
    private  EditText phone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_three, container, false);

        emailT = view.findViewById(R.id.email);
        passT = view.findViewById(R.id.pass);
        name = view.findViewById(R.id.name);
        phone = view.findViewById(R.id.Phone);

        Button registerButton = view.findViewById(R.id.register);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                reg();
            }
        });
        return view;
    }
    public void reg(){

        String email = emailT.getText().toString();
        String password = passT.getText().toString();


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            write();
                            Navigation.findNavController(requireView()).navigate(R.id.action_fragmentThree_to_fragment_for);

                        } else {
                            Toast.makeText(requireContext(),"reg fail",Toast.LENGTH_LONG).show();

                        }
                    }
                });


    }
    public void write(){

        String email = emailT.getText().toString();
        String nameText = name.getText().toString();
        String phoneText = phone.getText().toString();
        Person person = new Person(nameText, UUID.randomUUID().toString(),phoneText,email);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users ").child(person.id);

        myRef.setValue(person);

    }
}
