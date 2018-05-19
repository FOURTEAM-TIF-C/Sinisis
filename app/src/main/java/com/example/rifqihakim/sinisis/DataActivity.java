package com.example.rifqihakim.sinisis;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class DataActivity extends AppCompatActivity implements ListView.OnItemClickListener{
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    private ListView listView;
    private String JSON_STRING;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        getJSON();

//        Button button01 = (Button) findViewById(R.id.button);
//        Button button02 = (Button) findViewById(R.id.button1);
//        Button button11 = (Button) findViewById(R.id.button2);
//        Button button12 = (Button) findViewById(R.id.button3);
//        Button button21 = (Button) findViewById(R.id.button4);
//        Button button22 = (Button) findViewById(R.id.button5);
//        Button button31 = (Button) findViewById(R.id.button6);
//        Button button32 = (Button) findViewById(R.id.button7);
//
//        button01.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent il= new Intent(getApplicationContext(),NilaiActivity.class);
//                startActivity(il);
//            }
//        });
//        button02.setOnClickListener(new View.OnClickListener() {
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
                        Intent intent = new Intent(DataActivity.this, BerandaActivity.class); //Berpindah activity
                        startActivity(intent); //Menjalankan Activity
                        return true;
                    case R.id.navigation2:
                        //Toast.makeText(getApplicationContext(),"Tentang Telah Dipilih",Toast.LENGTH_SHORT).show();
                        Intent tentang = new Intent(DataActivity.this, TentangActivity.class); //Berpindah activity
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
    private void showEmployee(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String nis = jo.getString(konfigurasi.TAG_NIS);
                String nama = jo.getString(konfigurasi.TAG_NAMA_SISWA);
//                String tempat = jo.getString(konfigurasi.TAG_TEMPAT_LAHIR);
//                String lahir = jo.getString(konfigurasi.TAG_TGL_LAHIR);
//                String tahun = jo.getString(konfigurasi.TAG_TAHUN_AJARAN);
//                String jenis = jo.getString(konfigurasi.TAG_JK);
//                String agama = jo.getString(konfigurasi.TAG_AGAMA);

                HashMap<String,String> employees = new HashMap<>();
                employees.put(konfigurasi.TAG_NIS,nis);
                employees.put(konfigurasi.TAG_NAMA_SISWA,nama);
//                employees.put(konfigurasi.TAG_TEMPAT_LAHIR,tempat);
//                employees.put(konfigurasi.TAG_TGL_LAHIR,lahir);
//                employees.put(konfigurasi.TAG_TAHUN_AJARAN,tahun);
//                employees.put(konfigurasi.TAG_JK,jenis);
//                employees.put(konfigurasi.TAG_AGAMA,agama);
                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                DataActivity.this, list, R.layout.list_item,
                new String[]{konfigurasi.TAG_NIS,konfigurasi.TAG_NAMA_SISWA},
                new int[]{R.id.id, R.id.name});

        listView.setAdapter(adapter);
    }
    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DataActivity.this,"Mengambil Data","Mohon Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(konfigurasi.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, DetailDataActivity.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String empId = map.get(konfigurasi.TAG_NIS).toString();
        intent.putExtra(konfigurasi.EMP_NIS,empId);
        startActivity(intent);
    }



}
