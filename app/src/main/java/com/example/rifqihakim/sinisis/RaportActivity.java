package com.example.rifqihakim.sinisis;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.rifqihakim.sinisis.app.AppController;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.rifqihakim.sinisis.konfigurasi.TAG_JSON_ARRAY;
import static com.example.rifqihakim.sinisis.konfigurasi.TAG_NIS;
import static com.example.rifqihakim.sinisis.konfigurasi.URL_GET_RAPORT;

public class RaportActivity extends AppCompatActivity implements View.OnClickListener {
    //Mendefinisikan variabel

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    private TextView mtk;
    private TextView bindo;
    private TextView agama;
    private TextView big;
    private TextView bd;
    private TextView ips;
    private TextView pkn;
    private TextView seni;
    private TextView ipa;
    private TextView penjas;

    private TextView raport;
    private TextView status;

    private Button hitung;
    private Button simpan;

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
    private String nStatus, nis;

    SharedPreferences sharedpreferences;
    public static final String my_shared_preferences = "my_shared_preferences";


    public static final String json_obj_req ="json_obj_req";
    private static final String TAG = RaportActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raport);

        //Inisialisasi dari View
        raport = (TextView) findViewById(R.id.raport);
        status = (TextView) findViewById(R.id.status);
        simpan = (Button) findViewById(R.id.btn_simpan1);

        //Setting listeners to button
        simpan.setOnClickListener(this);


        //Dibawah ini merupakan perintah untuk Menambahkan  (CREATE)


        mtk = (TextView)findViewById(R.id.mtk);
        bindo = (TextView)findViewById(R.id.bindo);
        agama = (TextView)findViewById(R.id.agama);
        big = (TextView)findViewById(R.id.big);
        bd = (TextView)findViewById(R.id.bd);
        ips = (TextView)findViewById(R.id.ips);
        pkn = (TextView)findViewById(R.id.pkn);
        seni = (TextView)findViewById(R.id.seni);
        ipa = (TextView)findViewById(R.id.ipa);
        penjas = (TextView)findViewById(R.id.penjas);

        nis = getIntent().getStringExtra(konfigurasi.EMP_NIS);

     Volley();

        hitung = (Button)findViewById(R.id.hitung);

        raport = (TextView)findViewById(R.id.raport);
        status = (TextView)findViewById(R.id.status);

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
    //iki
    private void addEmployee(){

        final String nlr = raport.getText().toString().trim();
        final String sts = status.getText().toString().trim();
        final String nis = getIntent().getStringExtra(konfigurasi.EMP_NIS);

        class AddEmployee extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(RaportActivity.this, "Menambahkan...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(RaportActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put(konfigurasi.KEY_EMP_NLR, nlr);
                params.put(konfigurasi.KEY_EMP_STS, sts);
                params.put("nis", nis);


                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(konfigurasi.URL_GET_DATA, params);
                return res;
            }

        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    private void Volley(){
        final String id = nis;
        StringRequest strReq = new StringRequest(Request.Method.POST, URL_GET_RAPORT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Login Response: " + response.toString());
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray("response");
                    Log.e("Successfully Login!", jsonObject.getString("response"));


                    for(int i = 0; i<result.length(); i++) {
                        JSONObject jsonobject = result.getJSONObject(i);
                        String nilai = jsonobject.getString("nilai");
                        String nama_mapel = jsonobject.getString("mapel");

                        switch(nama_mapel){
                            case "Matematika":
                                mtk.setText(nilai);
                                break;
                            case "Bahasa Indonesia":
                                bindo.setText(nilai);
                                break;
                            case "Pendidikan Agama":
                                agama.setText(nilai);
                                break;
                            case "Bahasa Inggris":
                                big.setText(nilai);
                                break;
                            case "Pendidikan Kewarganegaraan":
                                pkn.setText(nilai);
                                break;
                            case "Ilmu Pengetahuan Alam":
                                ipa.setText(nilai);
                                break;
                            case "Ilmu Pengetahuan Sosial":
                                ips.setText(nilai);
                                break;
                            case "Seni Budaya dan Keterampilan":
                                seni.setText(nilai);
                                break;
                            case "Pendidikan Jasmani dan Olahraga":
                                penjas.setText(nilai);
                                break;
                            case "Bahasa Daerah":
                                bd.setText(nilai);
                        }
                    }
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
                params.put("id", id);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, json_obj_req);
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

        if(nRaport >= 75){
            sts = "NAIK";
        }else{
            sts = "TIDAK NAIK";
        }
        return sts;
    }

    @Override
    public void onClick(View v) {
        if (v == simpan) {
            addEmployee();
        }


    }
}
