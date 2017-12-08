package com.example.hakankurt.turizmsikayet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by FURKAN on 8.12.2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String TAG=DBHelper.class.getSimpleName();
    public static final String DATABASE_NAME="Set";
    public static final int DATABASE_VERSION=1;

    public static final String USER_TABLE="settings";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_FONTSIZE="fontsize";
    public static final String COLUMN_COLOR="color";

    public static final String CREATE_TABLE_USERS="CREATE TABLE "+ USER_TABLE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_FONTSIZE + " TEXT, "
            + COLUMN_COLOR + " TEXT);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ USER_TABLE);
        onCreate(db);
    }

    public void DB_KullaniciEkle(String fontsize,String color)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(COLUMN_FONTSIZE,fontsize);
        values.put(COLUMN_COLOR,color);

        long id=db.insert(USER_TABLE,null,values);
        db.close();
        Log.d(TAG,"Ayarlar kaydedildi."+id);
    }

    public String DB_AyarGetir()
    {
        String selectQuery="select * from "+ USER_TABLE + " where "+
                COLUMN_ID +" = "+"'"+0+"'";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        cursor.moveToFirst();
        String s=String.valueOf(cursor.getInt(0))+"-"+String.valueOf(cursor.getInt(1)); // ilk değer font size, diğeri color
        db.close();
        cursor.close();
        return s;
    }

            // ayarlar activity de seçilen değişiklikleri veri tabanına yazacak ve her seferinde çekip uygulayacak.


}
