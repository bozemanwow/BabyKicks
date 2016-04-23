package com.bozemanwow.babykicks;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bozem_000 on 4/17/2016.
 */
public class DataBaseHistoryHelper extends SQLiteOpenHelper {

    public static final String DataBase_Name = "BabyKicks.db";
    public static final String Table_Name = "BabyKicked";

    //----Baby Coloums---
    public static final String Col_ID = "ID";
    public static final String Col_Date = "Date";
    public static final String Col_Clock_Start = "Clock_Time_Start";
    public static final String Col_Clock_End = "Clock_Time_End";
    public static final String Col_Clock_Length = "Clock_Length";
    public static final String Col_KickCount = "Kick_Count";

    SQLiteDatabase dbs;
    public DataBaseHistoryHelper(Context context) {
        super(context, DataBase_Name, null, 1);
       dbs = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL("create table if not exists " + Table_Name
     //   + " ("+Col_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
        +Col_Date+ " String ,"
        +Col_Clock_Start+" String,"
        +Col_Clock_End+" String,"
        +Col_Clock_Length+ " FLOAT, "
        +Col_KickCount+ " INTEGER );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(db);
    }
    public boolean InsertData(String start, String end, float length, int kicks)
    {
        ContentValues cV = new ContentValues();
     //   cV.put(Col_ID, (byte[]) null);
        cV.put(Col_Date, "Temp");
        cV.put(Col_Clock_Start, start);
        cV.put(Col_Clock_End, end);
        cV.put(Col_Clock_Length, length);
        cV.put(Col_KickCount, kicks);
      long result =  dbs.insert(Table_Name,null,cV);
      //  dbs.execSQL("Insert");
        if(result > -1)
        return true;
        else
            return false;
    }
}
