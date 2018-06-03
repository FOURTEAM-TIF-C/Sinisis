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
import android.widget.ImageButton;
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

    private ImageButton kembali;
    private Button nilai;
    private Button raport;
    public static final String json_obj_req ="json_obj_req";
//    public static final String URL_GET_EMP = "http://192.168.43.232/SinisisAndroid/detailSiswa1.php";
//    public static final String TAG_NIS = "nis";
//    public static final String TAG_NAMA_SISWA = "nama";


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

        induk.setText(nis);

        Volley();

        kembali = (ImageButton)findViewById(R.id.back);
        nilai = (Button)findViewById(R.id.nilai);
        raport = (Button)findViewById(R.id.raport);

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(getApplicationContext(),DataActivity.class);
                startActivity(back);
            }
        });

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

}
