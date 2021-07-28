package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItem;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuInicio extends AppCompatActivity {
    private String correoD;
    private boolean invitado;
    FirstFragment firstFragment = new FirstFragment();
    SecondFragment secondFragment = new SecondFragment();
    ThirdFragment thirdFragment = new ThirdFragment();
    FourthFragment fourthFragment = new FourthFragment();
    FifthFragment fifthFragment = new FifthFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicio);
        correoD = getIntent().getStringExtra("correo");
        invitado = getIntent().getBooleanExtra("correo2", false);
        Bundle bundle = new Bundle();
        bundle.putString("Valor", correoD);
        bundle.putBoolean("Invitado", invitado);
        firstFragment.setArguments(bundle);


        BottomNavigationView navigation = findViewById(R.id.config);
        navigation.setOnNavigationItemSelectedListener(mOnNavegationItemSelectListener);

        loadFragment(firstFragment);
    }
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavegationItemSelectListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.firstFragment:
                    loadFragment(firstFragment);
                    return true;
                case R.id.secondFragment:
                    loadFragment(secondFragment);
                    return true;
                case R.id.thirdFragment:
                    loadFragment(thirdFragment);
                    return true;
                case R.id.fourthFragment:
                    loadFragment(fourthFragment);
                    return true;
                case R.id.fifthFragment:
                    loadFragment(fifthFragment);
                    return true;
            }
            return false;
        }
    };
    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.commit();
    }

    public void Conf(View view) {
        System.out.println(correoD);
            Intent sigueinte = new Intent(this, ConfiguracionActivity.class);
            sigueinte.putExtra("correo1", correoD);
            sigueinte.putExtra("invitado2", invitado);
            startActivity(sigueinte);
    }
    public void Fisicas(View view) {
        Intent sigueinte4 = new Intent(this, FisicasActivity.class);
        startActivity(sigueinte4);
    }
    public void Taichi(View view) {
        Intent sigueinte4 = new Intent(this, TaichiActivity.class);
        startActivity(sigueinte4);
    }
    public void Yoga(View view) {
        Intent sigueinte4 = new Intent(this, YogaActivity.class);
        startActivity(sigueinte4);
    }
    public void Manualidades(View view) {
        Intent sigueinte4 = new Intent(this, ManualidadesActivity.class);
        startActivity(sigueinte4);
    }
    public void Lectura(View view) {
        Intent sigueinte4 = new Intent(this, LecturaActivity.class);
        startActivity(sigueinte4);
    }
    public void Recreativas(View view) {
        Intent sigueinte4 = new Intent(this, RecreativasActivity.class);
        startActivity(sigueinte4);
    }
    public void Login(View view) {
        Intent sigueinte = new Intent(this, LoginActivity.class);
        startActivity(sigueinte);
    }
}