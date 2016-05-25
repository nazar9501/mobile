package com.example.lvivn.in_sport;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etRegName;
    EditText etRegEmail;
    EditText etRegPassword;
    TextView tvLingToLogin;
    Button bRegistration;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etRegName = (EditText) findViewById(R.id.etRegName);
        etRegEmail = (EditText) findViewById(R.id.etRegEmail);
        etRegPassword = (EditText) findViewById(R.id.etRegPassword);
        tvLingToLogin = (TextView) findViewById(R.id.tvLingToLogin);
        bRegistration = (Button) findViewById(R.id.bRegistration);

        dbHelper = new DBHelper(this);

        bRegistration.setOnClickListener(this);
        tvLingToLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvLingToLogin:
                Intent i = new Intent(this, LoginActivity.class);
                startActivity(i);
                finish();
                break;
            case R.id.bRegistration:
                onCreateNewUser();
                Intent reg = new Intent(this, sport.class);
                startActivity(reg);
                finish();
                break;
        }
    }

    void onCreateNewUser () {
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String name = etRegName.getText().toString();
        String email = etRegEmail.getText().toString();
        String password = etRegPassword.getText().toString();

        cv.put("user_name", name);
        cv.put("email", email);
        cv.put("password", password);

        db.insert("user", null, cv);
    }
}
