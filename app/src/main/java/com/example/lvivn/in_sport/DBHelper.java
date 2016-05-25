package com.example.lvivn.in_sport;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

/**
 * Created by vett on 25.05.16.
 */
public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "myDB", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table mytable ("
                + "_id integer primary key autoincrement,"
                + "Text text" + ");");

        db.execSQL("Create table user("
                + "_id integer primary key autoincrement, "
                + "user_name text, "
                + "email text, "
                + "password text "
                + ");");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}


