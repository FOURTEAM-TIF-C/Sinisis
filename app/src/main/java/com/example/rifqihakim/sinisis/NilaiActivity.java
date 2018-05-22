package com.example.rifqihakim.sinisis;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NilaiActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    private EditText nilaiPR;
    private EditText nilaiTugas;
    private EditText nilaiUH;
    private EditText nilaiUTS;
    private EditText nilaiUAS;

    private TextView nilaiAkhir;
    private TextView gradeAkhir;

    private int PR;
    private int tugas;
    private int UH;
    private int UTS;
    private int UAS;

    private double akhir;
    private String grade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nilai);
        Button button356= (Button) findViewById(R.id.button7);

        nilaiPR = (EditText)findViewById(R.id.pr);
        nilaiTugas = (EditText)findViewById(R.id.tugas);
        nilaiUH = (EditText)findViewById(R.id.ulangan);
        nilaiUTS = (EditText)findViewById(R.id.uts);
        nilaiUAS = (EditText)findViewById(R.id.uas);

        nilaiAkhir = (TextView)findViewById(R.id.nilai);
        gradeAkhir = (TextView)findViewById(R.id.grade);

        button356.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent il= new Intent(getApplicationContext(),DetailActivity.class);
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
                        Intent intent = new Intent(NilaiActivity.this, BerandaActivity.class); //Berpindah activity
                        startActivity(intent); //Menjalankan Activity
                        return true;
                    case R.id.navigation2:
                        //Toast.makeText(getApplicationContext(),"Tentang Telah Dipilih",Toast.LENGTH_SHORT).show();
                        Intent tentang = new Intent(NilaiActivity.this, TentangActivity.class); //Berpindah activity
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
//    private boolean validasiInput{
//        String pesan = "";
//
//        if(nilaiPR.getText().toString().equals("")){
//            pesan += "Nilai PR Harus Diisi !\n";
//        }
//        if(nilaiTugas.getText().toString().equals("")){
//            pesan += "Nilai Tugas Harus Diisi !\n";
//        }
//        if(nilaiUH.getText().toString().equals("")){
//            pesan += "Nilai Ulangan Harus Diisi !\n";
//        }
//        if(nilaiUTS.getText().toString().equals("")){
//            pesan += "Nilai UTS Harus Diisi !\n";
//        }
//        if(nilaiUAS.getText().toString().equals("")){
//            pesan += "Nilai UAS Harus Diisi !\n";
//        }
//        if(!pesan.equals("")){
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//            builde.setMessage(pesan)
//                    .setPositiveButton("OK", new DialogInterface.OnClickListener()){
//
//                @override
//                public void OnClick(DialogInterface dialog, int wich){
//                    dialog.dismiss();
//                }
//            });
//            AlertDialog alert = builder.create();
//            alert.show();
//
//            return false;
//        }else{
//            return true;
//        }
//    }
//    public  void bHasilOnClick(){
//        if(validasiInput()){
//            PR = Integer.parseInt(nilaiPR.getText().toString());
//            tugas = Integer.parseInt(nilaiTugas.getText().toString());
//            UH = Integer.parseInt(nilaiUH.getText().toString());
//            UTS = Integer.parseInt(nilaiUTS.getText().toString());
//            UAS = Integer.parseInt(nilaiUAS.getText().toString());
//
//            akhir = kalkulasiNilaiAkhir();
//            grade = gradeAkhir();
//
//            nilaiAkhir.setText("Nilai Akhir Anda : " + String.valueOf(nilaiAkhir));
//            gradeAkhir.setText("Grade Akhir Anda : " + String.valueOf(gradeAkhir));
//        }
//    }
//    private double kalkulasiNilaiAkhir(){
//        //misal ditentukan bobot dari tiap nilai
//        double pr = (double) PR;
//        double tgs = (double) tugas;
//        double uh = (double) UH;
//        double uts = (double) UTS;
//        double uas = (double) UAS;
//
//        return (pr + tgs + uh + uts +  uas) / 5;
//    }
//    private String gradeAkhir(){
////        String grade;
//
//        if(akhir >= 80){
//            grade = "A";
//        }else if(akhir <= 79 && akhir >= 70){
//            grade = "B";
//        }else{
//            grade = "C";
//        }
//    }

}
