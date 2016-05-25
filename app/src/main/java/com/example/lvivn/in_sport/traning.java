package com.example.lvivn.in_sport;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

public class traning extends Activity implements OnClickListener {

    final String LOG_TAG = "Training";

    EditText editText, editText2;
    Button Tsave, Tview, Tdelete;

    DBHelper dbHelper;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.traning);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText);

        Tsave = (Button) findViewById(R.id.Tsave);
        Tsave.setOnClickListener(this);

        Tview = (Button) findViewById(R.id.Tview);
        Tview.setOnClickListener(this);

        Tdelete = (Button) findViewById(R.id.Tdelete);
        Tdelete.setOnClickListener(this);

        dbHelper = new DBHelper(this);
    }


    public void onClick(View v) {

        ContentValues cv = new ContentValues();

        String text = editText.getText().toString();
        String number = editText2.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch (v.getId()) {
            case R.id.Tsave:
                cv.put("Text", text);
                db.insert("mytable", null, cv);
                Toast.makeText(this, "Тренування збережено", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Tview:

                Cursor c = db.query("mytable", null, null, null, null, null, null);
                c.moveToNext();
                editText.setText("");
                if (c.moveToFirst()) {

                    int textColIndex = c.getColumnIndex("Text");

                    do {
                        Log.d(LOG_TAG, "Text" + c.getString(textColIndex) );

                        editText.setText(editText.getText().toString() + "\n"+c.getString(textColIndex));

                    } while (c.moveToNext());

                } else
                    Toast.makeText(this, "Збережених тренувань немає", Toast.LENGTH_SHORT).show();
                c.close();
                break;
            case R.id.Tdelete:
                if (number.equalsIgnoreCase("")) {
                    break;
                }
                Toast.makeText(this, "Тренування видалено", Toast.LENGTH_SHORT).show();
                number = editText2.getText().toString();
                db.delete("mytable", "_id = " + number, null);
                break;
        }

        dbHelper.close();
    }

    public void onButtonClick3(View v) {
        if (v.getId() == R.id.Tprogram) {
            Intent i = new Intent(traning.this, program.class);
            startActivity(i);
        }
    }

    public void onButtonClick4(View v) {
        if (v.getId() == R.id.Tsport) {
            Intent i = new Intent(traning.this, sport.class);
            startActivity(i);
        }
    }
}
