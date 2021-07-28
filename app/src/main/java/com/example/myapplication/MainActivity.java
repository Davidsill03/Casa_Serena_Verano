package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Animaciones
        Animation animacion1 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_arriba);
        Animation animacion2 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo);

        TextView By = findViewById(R.id.By);
        TextView ITA = findViewById(R.id.ITA);
        ImageView LogoIV = findViewById(R.id.LogoIV);

        By.setAnimation(animacion2);
        ITA.setAnimation(animacion2);
        LogoIV.setAnimation(animacion1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.perfil) {
            Toast.makeText(this, "perfil", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.contacto) {
            Toast.makeText(this, "contacto", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.ajustes) {
            Toast.makeText(this, "ajustes", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}