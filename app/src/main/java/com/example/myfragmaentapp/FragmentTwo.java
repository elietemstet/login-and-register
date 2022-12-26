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

public class FragmentTwo extends Fragment {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private EditText emailT;
    private  EditText passT;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_two, container, false);

        emailT = view.findViewById(R.id.emailAdress);
        passT = view.findViewById(R.id.Password);

        Button button23 = view.findViewById(R.id.loginButton);

        button23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        return view;
    }
    public void login(){

        String email = emailT.getText().toString();
        String password = passT.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Navigation.findNavController(requireView()).navigate(R.id.action_fragmentTwo_to_fragment_for);
                        } else {
                            Toast.makeText(requireContext(),"login fail",Toast.LENGTH_LONG).show();

                        }
                    }
                });

    }
}
