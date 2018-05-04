package com.example.rifqihakim.sinisis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button login;
    private EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button)findViewById(R.id.btnLogin);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){

    }
}
