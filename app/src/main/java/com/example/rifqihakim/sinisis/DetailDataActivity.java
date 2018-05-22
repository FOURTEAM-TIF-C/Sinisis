package com.example.rifqihakim.sinisis;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class DetailDataActivity extends AppCompatActivity{
    private String nis;

    private TextView induk;
    private TextView Tnama;
    private TextView Ttempat;
    private TextView Ttanggal;
    private TextView Tjenis;
    private TextView Tagama;
    private TextView Ttahun;

    private Button nilai;
    private Button raport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);

        Intent intent = getIntent();

        nis = intent.getStringExtra(konfigurasi.EMP_NIS);

        induk = (TextView)findViewById(R.id.a);
        Tnama = (TextView)findViewById(R.id.b);
        Ttempat = (TextView)findViewById(R.id.c);
        Ttanggal = (TextView)findViewById(R.id.d);
        Tjenis = (TextView)findViewById(R.id.e);
        Tagama = (TextView)findViewById(R.id.f);
        Ttahun = (TextView)findViewById(R.id.g);

        nilai = (Button)findViewById(R.id.nilai);
        raport = (Button)findViewById(R.id.raport);

        nilai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nil= new Intent(getApplicationContext(),NilaiActivity.class);
                startActivity(nil);
            }
        });
        raport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ra= new Intent(getApplicationContext(),RaportActivity.class);
                startActivity(ra);
            }
        });

        induk.setText(nis);

        getEmployee();
    }
    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailDataActivity.this,"Mengambil Data...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_GET_EMP,nis);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }
    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
//            String nis = c.getString(konfigurasi.TAG_NIS);
            String siswa = c.getString(konfigurasi.TAG_NAMA_SISWA);
            String lahir = c.getString(konfigurasi.TAG_TEMPAT_LAHIR);
            String tgl = c.getString(konfigurasi.TAG_TGL_LAHIR);
            String jk = c.getString(konfigurasi.TAG_JK);
            String agm = c.getString(konfigurasi.TAG_AGAMA);
            String th = c.getString(konfigurasi.TAG_TAHUN_AJARAN);

//            induk.setText(nis);
            Tnama.setText(siswa);
            Ttempat.setText(lahir);
            Ttanggal.setText(tgl);
            Tjenis.setText(jk);
            Tagama.setText(agm);
            Ttahun.setText(th);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
