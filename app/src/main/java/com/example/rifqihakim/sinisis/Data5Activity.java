package com.example.rifqihakim.sinisis;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Data5Activity extends AppCompatActivity {
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data5);

        Button button351 = (Button) findViewById(R.id.button);
        Button button341 = (Button) findViewById(R.id.button1);

        button341.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent il= new Intent(getApplicationContext(),NilaiActivity.class);
                startActivity(il);
            }
        });
        button351.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent il= new Intent(getApplicationContext(),RaportActivity.class);
                startActivity(il);
            }
        });

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Menginisiasi  NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        //Mengatur Navigasi View Item yang akan dipanggil untuk menangani item klik menu navigasi
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            // This method will trigger on item Click of navigation menu

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //Memeriksa apakah item tersebut dalam keadaan dicek  atau tidak,
                if(menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);
                //Menutup  drawer item klik
                drawerLayout.closeDrawers();
                //Memeriksa untuk melihat item yang akan dilklik dan melalukan aksi
                switch (menuItem.getItemId()){
                    // pilihan menu item navigasi akan menampilkan pesan toast klik kalian bisa menggantinya
                    //dengan intent activity
                    case R.id.navigation1:
                        //Toast.makeText(getApplicationContext(), "Pengaturan Telah Dipilih", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Data5Activity.this, BerandaActivity.class); //Berpindah activity
                        startActivity(intent); //Menjalankan Activity
                        return true;
                    case R.id.navigation2:
                        //Toast.makeText(getApplicationContext(),"Tentang Telah Dipilih",Toast.LENGTH_SHORT).show();
                        Intent tentang = new Intent(Data5Activity.this, TentangActivity.class); //Berpindah activity
                        startActivity(tentang); //Menjalankan Activity
                        return true;
                    case R.id.navigation3:
                        Toast.makeText(getApplicationContext(),"Keluar Telah Dipilih",Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(),"Kesalahan Terjadi ",Toast.LENGTH_SHORT).show();
                        return true;
                }
            }
        });
        // Menginisasi Drawer Layout dan ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer){
            @Override
            public void onDrawerClosed(View drawerView) {
                // Kode di sini akan merespons setelah drawer menutup disini kita biarkan kosong
                super.onDrawerClosed(drawerView);
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                //  Kode di sini akan merespons setelah drawer terbuka disini kita biarkan kosong
                super.onDrawerOpened(drawerView);
            }
        };
        //Mensetting actionbarToggle untuk drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        //memanggil synstate
        actionBarDrawerToggle.syncState();
    }
}
