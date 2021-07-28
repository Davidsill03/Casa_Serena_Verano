package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class FifthFragment extends Fragment {
    private String correoD;
    private boolean invitado;
    View vista;
    TextView textName, textapP, textCorreo;

    public FifthFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                // We use a String here, but any type that can be put in a Bundle is supported
                correoD = bundle.getString("Valor");
                invitado = bundle.getBoolean("Invitado", false);
                // Do something with the result...
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_fifth, container, false);
        textName = vista.findViewById(R.id.textView11);
        textapP = vista.findViewById(R.id.textView14);
        textCorreo = vista.findViewById(R.id.textView15);
        if(invitado == false) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference();

            Query lastQuery = ref.child("Usuario").orderByChild("correo").equalTo(correoD);
            lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        if (snapshot.hasChild("nombre")) {
                            String name = snapshot.child("nombre").getValue(String.class);
                            textName.setText("Nombre: " + name);
                        }
                        if (snapshot.hasChild("apP")) {
                            String apP = snapshot.child("apP").getValue(String.class);
                            textapP.setText("Apellido: " + apP);
                        }
                        if (snapshot.hasChild("correo")) {
                            String correo = snapshot.child("correo").getValue(String.class);
                            textCorreo.setText("Correo: " + correo);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    throw error.toException();
                }
            });
        }else{
            textName.setText("¡No esta registrado!");
        }
        return vista;
    }
}