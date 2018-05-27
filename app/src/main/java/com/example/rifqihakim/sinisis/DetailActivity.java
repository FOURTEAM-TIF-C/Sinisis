package com.example.rifqihakim.sinisis;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
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

import java.util.HashMap;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    private EditText Semester;
    private EditText nPR;
    private EditText nTugas;
    private EditText nUH;
    private EditText nUTS;
    private EditText nUAS;

    private TextView nAkhir;
    private TextView nGrade;

    private Button simpan;

    private int PR;
    private int Tugas;
    private int UH;
    private int UTS;
    private int UAS;

    private double Akhir;
    private String Grade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        nPR = (EditText)findViewById(R.id.pr);
        nTugas = (EditText)findViewById(R.id.tugas);
        nUH = (EditText)findViewById(R.id.ulangan);
        nUTS = (EditText)findViewById(R.id.uts);
        nUAS = (EditText)findViewById(R.id.uas);

        nAkhir = (TextView)findViewById(R.id.rata);
        nGrade = (TextView)findViewById(R.id.grade);

        simpan = (Button) findViewById(R.id.simpan);
        simpan.setOnClickListener(this);

//        simpan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent il= new Intent(getApplicationContext(),RaportActivity.class);
//                startActivity(il);
//            }
//        });

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
                        Intent intent = new Intent(DetailActivity.this,BerandaActivity.class); //Berpindah activity
                        startActivity(intent); //Menjalankan Activity
                        return true;
                    case R.id.navigation2:
                        //Toast.makeText(getApplicationContext(),"Tentang Telah Dipilih",Toast.LENGTH_SHORT).show();
                        Intent tentang = new Intent(DetailActivity.this, TentangActivity.class); //Berpindah activity
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
    private boolean validasiInput(){
        String pesan = "" ;

        if(nTugas.getText().toString().equals("")){
            pesan += "Nilai Tugas harus diisi !\n";
        }
        if(nPR.getText().toString().equals("")){
            pesan += "Nilai PR harus diisi !\n";
        }
        if(nUH.getText().toString().equals("")){
            pesan += "Nilai Ulangan Harian harus diisi !\n";
        }
        if(nUTS.getText().toString().equals("")){
            pesan += "Nilai UTS harus diisi !\n";
        }
        if(nUAS.getText().toString().equals("")){
            pesan += "Nilai UAS harus diisi !\n";
        }
        if(!pesan.equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(pesan)
                    .setPositiveButton("SIMPAN", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
            return false;
        }else{
            return true;
        }
    }
    public  void bHitung(View view){
        if(validasiInput()){
            Tugas = Integer.parseInt(nTugas.getText().toString());
            PR = Integer.parseInt(nPR.getText().toString());
            UH = Integer.parseInt(nUH.getText().toString());
            UTS = Integer.parseInt(nUTS.getText().toString());
            UAS = Integer.parseInt(nUAS.getText().toString());

            Akhir = kalkulasiNilaiAkhir();
            Grade = gradeAkhir();

            nAkhir.setText(String.valueOf(Akhir));
            nGrade.setText(Grade);
        }
    }
    private double kalkulasiNilaiAkhir(){
        double pr = (double) PR;
        double tgs = (double) Tugas;
        double uh = (double) UH;
        double uts = (double) UTS;
        double uas = (double) UAS;

        return (pr + tgs + uh + uts +  uas) / 5;
    }
    private String gradeAkhir(){
        String _grade;

        if(Akhir >= 80){
            _grade = "A";
        }else if(Akhir <= 79 && Akhir >= 70){
            _grade = "B";
        }else{
            _grade = "C";
        }
        return _grade;
    }
    //Dibawah ini merupakan perintah untuk Menambahkan nilai (CREATE)
    private void add(){

        final String smt = Semester.getText().toString().trim();
        final String rata = nAkhir.getText().toString().trim();
        final String grade = nGrade.getText().toString().trim();

        class Add extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailActivity.this,"Menambahkan...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(DetailActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(konfigurasi.KEY_EMP_SMT,smt);
                params.put(konfigurasi.KEY_EMP_RATA,rata);
                params.put(konfigurasi.KEY_EMP_GRADE,grade);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(konfigurasi.URL_ADD, params);
                return res;
            }
        }

        Add ae = new Add();
        ae.execute();
    }
    public void onClick(View v) {
        if(v == simpan){
            add();
        }
    }
}
