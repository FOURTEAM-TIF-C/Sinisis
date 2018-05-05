package com.example.rifqihakim.sinisis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
    }

    @Override
    public void onClick(View view){
        String usernameA = username.getText().toString();
        String pass = password.getText().toString();
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker (this);
        backgroundWorker.execute(type, usernameA, pass);
    }
}
