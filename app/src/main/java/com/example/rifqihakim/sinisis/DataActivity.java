package com.example.rifqihakim.sinisis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        Button button34 = (Button) findViewById(R.id.button34);

        button34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent il= new Intent(getApplicationContext(),NilaiActivity.class);
                startActivity(il);
            }
        });



    }
}
