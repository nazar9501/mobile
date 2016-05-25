package com.example.lvivn.in_sport;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etUserName;
    EditText etPassword;
    TextView etGoToRegister;
    Button btnLogin;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DBHelper(this);

        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etGoToRegister = (TextView) findViewById(R.id.etGoToRegister);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        etGoToRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.etGoToRegister:
                Intent i = new Intent(this, RegisterActivity.class);
                startActivity(i);
                finish();
                break;
            case R.id.btnLogin:
                if (onVerificateUser()) {
                    Intent log = new Intent(this, sport.class);
                    startActivity(log);
                    finish();
                }
                break;
        }
    }

    boolean onVerificateUser() {
        ContentValues cv = new ContentValues();

        String name = etUserName.getText().toString();
        String password = etPassword.getText().toString();

        boolean flag = false;

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("user", null, null, null, null, null, null);
        c.moveToNext();
        if (c.moveToFirst()) {

            do {
                int userColIndex = c.getColumnIndex("user_name");
                int passColIndex = c.getColumnIndex("password");
                String cname = c.getString(userColIndex);
                String cpass = c.getString(passColIndex);

                if (name.equals(cname)){
                    if (password.equals(cpass)) {
                        Toast.makeText(this,"Вітаємо!", Toast.LENGTH_LONG).show();
                        flag = true;
                        break;
                    } else {
                        Toast.makeText(this,"Неправильний пароль!", Toast.LENGTH_LONG).show();
                        flag = false;
                        break;
                    }
                }

            } while (c.moveToNext());

        } else
            Toast.makeText(this,"Даного користувача немає в системі!", Toast.LENGTH_LONG).show();
        c.close();
        return flag;
    }
}
