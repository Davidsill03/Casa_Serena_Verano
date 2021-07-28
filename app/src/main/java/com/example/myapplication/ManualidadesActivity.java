package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ManualidadesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manualidades);
    }
    public void Config12(View view) {
        Intent sigueinte = new Intent(this, ConfiguracionActivity.class);
        startActivity(sigueinte);
    }
}