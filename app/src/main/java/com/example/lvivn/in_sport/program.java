package com.example.lvivn.in_sport;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class program extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.program);
    }

    public void onButtonClick5(View v)
    {
        if(v.getId() == R.id.Psport)
        {
            Intent i = new Intent(program.this, sport.class);
            startActivity(i);
        }
    }

    public void onButtonClick6(View v)
    {
        if(v.getId() == R.id.Ptraning)
        {
            Intent i = new Intent(program.this, traning.class);
            startActivity(i);
        }
    }
}

