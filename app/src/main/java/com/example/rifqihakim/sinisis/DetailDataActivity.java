package com.example.rifqihakim.sinisis;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.rifqihakim.sinisis.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import static com.example.rifqihakim.sinisis.konfigurasi.TAG_AGAMA;
import static com.example.rifqihakim.sinisis.konfigurasi.TAG_JK;
import static com.example.rifqihakim.sinisis.konfigurasi.TAG_NAMA_SISWA;
import static com.example.rifqihakim.sinisis.konfigurasi.TAG_NIS;
import static com.example.rifqihakim.sinisis.konfigurasi.TAG_TAHUN_AJARAN;
import static com.example.rifqihakim.sinisis.konfigurasi.TAG_TEMPAT_LAHIR;
import static com.example.rifqihakim.sinisis.konfigurasi.TAG_TGL_LAHIR;
import static com.example.rifqihakim.sinisis.konfigurasi.URL_GET_EMP;

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
    public static final String json_obj_req ="json_obj_req";
    public static final String URL_GET_EMP = "http://192.168.43.232/SinisisAndroid/detailSiswa1.php";
    public static final String TAG_NIS = "nis";
    public static final String TAG_NAMA_SISWA = "nama";


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

        induk.setText(nis);

        Volley();

        nilai = (Button)findViewById(R.id.nilai);
        raport = (Button)findViewById(R.id.raport);

        nilai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent nil= new Intent(getApplicationContext(),NilaiActivity.class);
//                startActivity(nil);
                Volley();
            }
        });
        raport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ra= new Intent(getApplicationContext(),RaportActivity.class);
                startActivity(ra);
            }
        });


//        getEmployee();
    }
    private void Volley(){
        final String nama1 = induk.getText().toString().trim();
        StringRequest strReq = new StringRequest(Request.Method.POST, URL_GET_EMP, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Tnama.setText(jsonObject.getString(TAG_NAMA_SISWA));
                    Ttempat.setText(jsonObject.getString(TAG_TEMPAT_LAHIR));
                    Ttanggal.setText(jsonObject.getString(TAG_TGL_LAHIR));
                    Tjenis.setText(jsonObject.getString(TAG_JK));
                    Tagama.setText(jsonObject.getString(TAG_AGAMA));
                    Ttahun.setText(jsonObject.getString(TAG_TAHUN_AJARAN));

                    ////            Ttempat.setText(lahir);
                    ////            Ttanggal.setText(tgl);
                    ////            Tjenis.setText(jk);
                    ////            Tagama.setText(agm);
                    ////            Ttahun.setText(th);
                    ////
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
                params.put(TAG_NIS, nama1);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, json_obj_req);
    }





//    private void getEmployee(){
//        class GetEmployee extends AsyncTask<Void,Void,String> {
//            ProgressDialog loading;
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                loading = ProgressDialog.show(DetailDataActivity.this,"Mengambil Data...","Tunggu...",false,false);
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                loading.dismiss();
//                showEmployee(s);
//            }
//
//            @Override
//            protected String doInBackground(Void... params) {
//                RequestHandler rh = new RequestHandler();
//                String s = rh.sendGetRequestParam(konfigurasi.URL_GET_EMP,nis);
//                return s;
//            }
//        }
//        GetEmployee ge = new GetEmployee();
//        ge.execute();
//    }
//    private void showEmployee(String json){
//        try {
//            JSONObject jsonObject = new JSONObject(json);
//            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);
//            JSONObject c = result.getJSONObject(0);
////            String nis = c.getString(konfigurasi.TAG_NIS);
//            String siswa = c.getString(konfigurasi.TAG_NAMA_SISWA);
//            String lahir = c.getString(konfigurasi.TAG_TEMPAT_LAHIR);
//            String tgl = c.getString(konfigurasi.TAG_TGL_LAHIR);
//            String jk = c.getString(konfigurasi.TAG_JK);
//            String agm = c.getString(konfigurasi.TAG_AGAMA);
//            String th = c.getString(konfigurasi.TAG_TAHUN_AJARAN);
//
////            induk.setText(nis);
//            Tnama.setText(siswa);
//            Ttempat.setText(lahir);
//            Ttanggal.setText(tgl);
//            Tjenis.setText(jk);
//            Tagama.setText(agm);
//            Ttahun.setText(th);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
}
