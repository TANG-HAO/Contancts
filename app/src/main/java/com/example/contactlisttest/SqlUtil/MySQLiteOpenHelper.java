package com.example.contactlisttest.SqlUtil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    final String CREATE_DB_SQL="create table personinfo(_id integer primary key autoincrement"+",name,phonenumber)";
    public MySQLiteOpenHelper(@Nullable Context context, @Nullable String name, int version) {
        super(context, name, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //第一次使用数据哭时，建表
        sqLiteDatabase.execSQL(CREATE_DB_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int olderVersion, int newVersion) {
        System.out.println("----------update called------------"+
                olderVersion+"----->"+newVersion);
    }
}
