package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.model.Usuario;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.Objects;
import java.util.UUID;

public class ConfiguracionActivity extends AppCompatActivity {
    private TextInputLayout textVName;
    private TextInputLayout textVapP;
    private TextInputLayout textVEmail;
    private TextInputLayout textVPass;
    private Button edit, delete;
    private String ID;
    private String apM;
    private String correoD;
    private boolean invitado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        textVName = (TextInputLayout) findViewById(R.id.textView3);
        textVapP = (TextInputLayout) findViewById(R.id.textView5);
        textVEmail = (TextInputLayout) findViewById(R.id.textView6);
        textVPass = (TextInputLayout) findViewById(R.id.textView7);
        edit = (Button) findViewById(R.id.button);
        delete = (Button) findViewById(R.id.button2);


            correoD = getIntent().getStringExtra("correo1");
            invitado = getIntent().getBooleanExtra("invitado2", true);
            if(invitado == false){
            System.out.println(correoD);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference();

            Query lastQuery = ref.child("Usuario").orderByChild("correo").equalTo(correoD);
            lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        if (snapshot.hasChild("id")) {
                            ID = snapshot.child("id").getValue(String.class);
                        }
                        if (snapshot.hasChild("apM")) {
                            apM = snapshot.child("apM").getValue(String.class);
                        }
                        if (snapshot.hasChild("nombre")) {
                            String name = snapshot.child("nombre").getValue(String.class);
                            textVName.getEditText().setText(name);
                        }
                        if (snapshot.hasChild("apP")) {
                            String lname = snapshot.child("apP").getValue(String.class);
                            textVapP.getEditText().setText(lname);
                        }
                        if (snapshot.hasChild("correo")) {
                            String email = snapshot.child("correo").getValue(String.class);
                            textVEmail.getEditText().setText(email);
                        }
                        if (snapshot.hasChild("contraseña")) {
                            String pass = snapshot.child("contraseña").getValue(String.class);
                            textVPass.getEditText().setText(pass);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    throw error.toException();
                }
            });
        }else{
                textVName.getEditText().setText("No estas registrado");
                textVName.setEnabled(false);
                textVPass.setEnabled(false);
                textVEmail.setEnabled(false);
                textVapP.setEnabled(false);
                edit.setEnabled(false);
                delete.setEnabled(false);
            }
    }

    public void Regresar(View view) {
        Intent sigueinte = new Intent(ConfiguracionActivity.this, MenuInicio.class);
        startActivity(sigueinte);
    }
    public void Editar(View view){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        Query lastQuery = ref.child("Usuario").orderByChild("correo").equalTo(correoD);
        lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Usuario u = new Usuario();
                    u.setId(ID);
                    u.setNombre(textVName.getEditText().getText().toString().trim());
                    u.setApP(textVapP.getEditText().getText().toString().trim());
                    u.setApM(apM);
                    u.setCorreo(textVEmail.getEditText().getText().toString().trim());
                    u.setContraseña(textVPass.getEditText().getText().toString().trim());
                    ref.child("Usuario").child(u.getId()).setValue(u);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                throw error.toException();
            }
        });
        correoD = textVEmail.getEditText().getText().toString();
    }
    public void Eliminar(View view){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        Query lastQuery = ref.child("Usuario").orderByChild("correo").equalTo(correoD);
        lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Usuario u = new Usuario();
                    u.setId(ID);
                    u.setNombre(textVName.getEditText().getText().toString().trim());
                    u.setApP(textVapP.getEditText().getText().toString().trim());
                    u.setApM(apM);
                    u.setCorreo(textVEmail.getEditText().getText().toString().trim());
                    u.setContraseña(textVPass.getEditText().getText().toString().trim());
                    ref.child("Usuario").child(u.getId()).removeValue();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                throw error.toException();
            }
        });
        textVName.getEditText().setText("");
        textVapP.getEditText().setText("");
        textVEmail.getEditText().setText("");
        textVPass.getEditText().setText("");
        Intent eliminado = new Intent(ConfiguracionActivity.this, LoginActivity.class);
        startActivity(eliminado);
    }
}