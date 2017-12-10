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
    public static final String DATABASE_NAME="Set10";
    public static final int DATABASE_VERSION=1;

    public static final String USER_TABLE="settings";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_FONTSIZE="fontsize";
    public static final String COLUMN_COLOR="background";
    public static final String COLUMN_FONTCOLOR="fontcolor";


    public static final String CREATE_TABLE_USERS="CREATE TABLE "+ USER_TABLE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_FONTSIZE + " TEXT, "
            + COLUMN_FONTCOLOR +" TEXT, "
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

    public void AyarKaydet(String fontsize,String background,String fontcolor)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(COLUMN_FONTSIZE,fontsize);
        values.put(COLUMN_COLOR,background);
        values.put(COLUMN_FONTCOLOR,fontcolor);
        long id=db.insert(USER_TABLE,null,values);
        db.close();
        Log.e(TAG,"Ayarlar kaydedildi. "+id);
    }

    public String AyarGetir()
    {
        try{
            String selectQuery="select * from "+ USER_TABLE + " where "+
                    COLUMN_ID +" = "+"'"+1+"'";
            SQLiteDatabase db=this.getReadableDatabase();
            Cursor cursor=db.rawQuery(selectQuery,null);
            cursor.moveToFirst();
            String s=String.valueOf(cursor.getString(1))+"-"+String.valueOf(cursor.getString(2)+"+"+String.valueOf(cursor.getString(3))); // ilk değer font size, diğeri color
            db.close();
            cursor.close();
            return s;
        }
        catch (Exception e)
        {
            Log.e(TAG,"Hata var."+e.getMessage().toString()+" - "+e.getStackTrace().toString());
            return null;
        }
    }

    public boolean AyarGuncelle(String yaziBoyutu,String arkaPlan,String yaziRengi)
    {
        try{
                ContentValues cv=new ContentValues();
                cv.put(COLUMN_FONTSIZE,yaziBoyutu);
                cv.put(COLUMN_COLOR,arkaPlan);
                cv.put(COLUMN_FONTCOLOR,yaziRengi);

            String sorgu="select * from "+ USER_TABLE + " where "+
                    COLUMN_ID +" = "+"'"+1+"'";
            SQLiteDatabase db=this.getReadableDatabase();
            db.update(USER_TABLE,cv,COLUMN_ID+"="+1,null);
            db.close();
            Log.e(TAG,"ayarlar güncellendi!");
            return true;
        }
        catch (Exception e)
        {
            Log.e(TAG,"Ayar guncelle hatası!"+e.getMessage().toString()+" - "+e.getStackTrace().toString());
            return false;
        }
    }



}
