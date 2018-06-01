package com.example.rifqihakim.sinisis;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.rifqihakim.sinisis.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.rifqihakim.sinisis.konfigurasi.TAG_AGAMA;
import static com.example.rifqihakim.sinisis.konfigurasi.TAG_JK;
import static com.example.rifqihakim.sinisis.konfigurasi.TAG_NAMA_SISWA;
import static com.example.rifqihakim.sinisis.konfigurasi.TAG_NILAI;
import static com.example.rifqihakim.sinisis.konfigurasi.TAG_NIS;
import static com.example.rifqihakim.sinisis.konfigurasi.TAG_RAPORT;
import static com.example.rifqihakim.sinisis.konfigurasi.TAG_TEMPAT_LAHIR;
import static com.example.rifqihakim.sinisis.konfigurasi.TAG_TGL_LAHIR;
import static com.example.rifqihakim.sinisis.konfigurasi.URL_GET_EMP;
import static com.example.rifqihakim.sinisis.konfigurasi.URL_GET_RAPORT;

public class RaportActivity extends AppCompatActivity {
    //Mendefinisikan variabel
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    private EditText mtk;
    private EditText bindo;

//    private TextView mtk;
//    private TextView bindo;
    private TextView agama;
    private TextView big;
    private TextView bd;
    private TextView ips;
    private TextView pkn;
    private TextView seni;
    private TextView ipa;
    private TextView penjas;

    private TextView raport;
    private EditText status;

    private Button hitung;

    private int nMTK;
    private int nBI;
    private int nAgama;
    private int nBIG;
    private int nBD;
    private int nIPS;
    private int nPKN;
    private int nSeni;
    private int nIPA;
    private int nPenjas;

    private double nRaport;
    private String nStatus;

    public static final String json_obj_req ="json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raport);

        mtk = (EditText) findViewById(R.id.mtk);
        bindo = (EditText) findViewById(R.id.bindo);

//        mtk = (TextView)findViewById(R.id.mtk);
//        bindo = (TextView)findViewById(R.id.bindo);
        agama = (TextView)findViewById(R.id.agama);
        big = (TextView)findViewById(R.id.big);
        bd = (TextView)findViewById(R.id.bd);
        ips = (TextView)findViewById(R.id.ips);
        pkn = (TextView)findViewById(R.id.pkn);
        seni = (TextView)findViewById(R.id.seni);
        ipa = (TextView)findViewById(R.id.ipa);
        penjas = (TextView)findViewById(R.id.penjas);

      //Volley(raport1);

        hitung = (Button)findViewById(R.id.hitung);

        raport = (TextView)findViewById(R.id.raport);
        status = (EditText)findViewById(R.id.status);

        // Menginisiasi Toolbar dan mensetting sebagai actionbar
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
                        Intent intent = new Intent(RaportActivity.this, BerandaActivity.class); //Berpindah activity
                        startActivity(intent); //Menjalankan Activity
                        return true;
                    case R.id.navigation2:
                        //Toast.makeText(getApplicationContext(),"Tentang Telah Dipilih",Toast.LENGTH_SHORT).show();
                        Intent tentang = new Intent(RaportActivity.this, TentangActivity.class); //Berpindah activity
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
    public void bHitung(View view){
        nMTK = Integer.parseInt(mtk.getText().toString());
        nBI= Integer.parseInt(bindo.getText().toString());
        nAgama = Integer.parseInt(agama.getText().toString());
        nBIG = Integer.parseInt(big.getText().toString());
        nBD = Integer.parseInt(bd.getText().toString());
        nIPS = Integer.parseInt(ips.getText().toString());
        nPKN = Integer.parseInt(pkn.getText().toString());
        nSeni = Integer.parseInt(seni.getText().toString());
        nIPA = Integer.parseInt(ipa.getText().toString());
        nPenjas = Integer.parseInt(penjas.getText().toString());

        nRaport = hitungNilai();
        nStatus = statusAkhir();

        raport.setText(String.valueOf(nRaport));
        status.setText(nStatus);
    }
    private double hitungNilai(){
        double mat = (double) nMTK;
        double bin = (double) nBI;
        double agma = (double) nAgama;
        double bg = (double) nBIG;
        double bdaer = (double) nBD;
        double sos = (double) nIPS;
        double kn = (double) nPKN;
        double sni = (double) nSeni;
        double alam = (double) nIPA;
        double pjk = (double) nPenjas;

        return (mat + bin + agma + bg +  bdaer + sos + kn + sni + alam + pjk) / 10;
    }
    private String statusAkhir(){
        String sts;

        if(nRaport >= 60){
            sts = "NAIK";
        }else{
            sts = "TIDAK NAIK";
        }
        return sts;
    }
    private void Volley(final String raport1){
        StringRequest strReq = new StringRequest(Request.Method.POST, URL_GET_RAPORT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    mtk.setText(jsonObject.getString(TAG_NILAI));
                    bindo.setText(jsonObject.getString(TAG_NILAI));
//                    agama.setText(jsonObject.getString(TAG_TGL_LAHIR));
//                    big.setText(jsonObject.getString(TAG_JK));
//                    ips.setText(jsonObject.getString(TAG_AGAMA));
//                    pkn.setText(jsonObject.getString(TAG_AGAMA));
//                    seni.setText(jsonObject.getString(TAG_AGAMA));
//                    ipa.setText(jsonObject.getString(TAG_AGAMA));
//                    penjas.setText(jsonObject.getString(TAG_AGAMA));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(DetailDataActivity.class.getSimpleName(), "Error :"+ error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<String, String>();
                params.put(TAG_RAPORT, raport1);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, json_obj_req);
    }
}
