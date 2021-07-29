package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class FisicasActivity extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fisicas);

        imageView = findViewById(R.id.image1fisicas);

        String url = "https://www.dropbox.com/s/lbxxi7b3viwkwks/actividad_fisica.jpg?dl=0";
        Glide.with(this)
                .load(url)
                .placeholder(R.drawable.logorosa)
                .error(R.drawable.logorosa)
                .into(imageView);
    }
    public void Configg(View view) {
        Intent sigueinte = new Intent(this, ConfiguracionActivity.class);
        startActivity(sigueinte);
    }
}