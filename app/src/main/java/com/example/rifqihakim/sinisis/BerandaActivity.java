package com.example.rifqihakim.sinisis;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

public class BerandaActivity extends AppCompatActivity {
    
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        @SuppressLint("WrongViewCast")
        Button imageButton = (Button) findViewById(R.id.imageButton);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent(getApplicationContext(), DataActivity.class);
                startActivity(data);
            }
        });

        @SuppressLint("WrongViewCast")
        Button imageButton2 = (Button) findViewById(R.id.imageButton2);

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent absensi = new Intent(getApplicationContext(), PresensiActivity.class);
                startActivity(absensi);
            }
        });

        @SuppressLint("WrongViewCast")
        Button imageButton4 = (Button) findViewById(R.id.imageButton4);

        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nilai = new Intent(getApplicationContext(), NilaiActivity.class);
                startActivity(nilai);
            }
        });
    }
}
