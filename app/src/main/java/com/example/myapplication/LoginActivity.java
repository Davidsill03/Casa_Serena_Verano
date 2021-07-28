package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.model.Usuario;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout usuario;
    private TextInputLayout contra;
    private boolean invitado = false;
    //private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuario = findViewById(R.id.usuarioTextFaild);
        contra = findViewById(R.id.contraTextFaild);

    }

    private boolean validarU(){
        String valN = usuario.getEditText().getText().toString().trim();

        if(valN.isEmpty()){
            usuario.setError("Complete el campo");
            return false;
        }else{
            usuario.setError(null);
            return true;
        }
    }
    private boolean validarC(){
        String valN = contra.getEditText().getText().toString().trim();

        if(valN.isEmpty()){
            contra.setError("Complete el campo");
            return false;
        }else{
            contra.setError(null);
            return true;
        }
    }
    public void Siguiente(View view) {
        Intent sigueinte = new Intent(this, register.class);
        startActivity(sigueinte);
    }
    public void Siguiente3(View view) {
        String a = "";
        if(!validarU() || !validarC()){
            return;
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        String getEmail = usuario.getEditText().getText().toString();
        String getPassword = contra.getEditText().getText().toString();
        System.out.println(getEmail);

        Query lastQuery = ref.child("Usuario").orderByChild("correo").equalTo(getEmail);
        Query lastQuery2 = ref.child("Usuario").orderByChild("contraseña").equalTo(getPassword);
        lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        if (snapshot.hasChild("correo")) {
                            lastQuery2.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                                    if(dataSnapshot1.exists()){
                                        for (DataSnapshot snapshot1 : dataSnapshot1.getChildren()){
                                            if(snapshot1.hasChild("contraseña")){
                                                    String input = "Bienvenido";
                                                    Toast.makeText(LoginActivity.this, input, Toast.LENGTH_SHORT).show();
                                                    Intent sigueinte3 = new Intent(LoginActivity.this, MenuInicio.class);
                                                    sigueinte3.putExtra("correo", getEmail);
                                                    startActivity(sigueinte3);
                                            }
                                        }
                                    }else{
                                        Toast.makeText(LoginActivity.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(LoginActivity.this, "Error contraseña", Toast.LENGTH_SHORT).show();
                                    throw error.toException();
                                }
                            });
                        }
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "Correo incorrecto", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LoginActivity.this, "Error correo", Toast.LENGTH_SHORT).show();
                throw error.toException();
            }
        });
    }
    public void Invitado(View view) {
        invitado=true;
        Intent sigueinte = new Intent(this, MenuInicio.class);
        sigueinte.putExtra("correo2", invitado);
        startActivity(sigueinte);
    }
    public void Olvido(View view) {
        String input = "Recupere su contraseña";
        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
        Intent sigueinte = new Intent(this, OlvidadoActivity.class);
        startActivity(sigueinte);
    }

}
