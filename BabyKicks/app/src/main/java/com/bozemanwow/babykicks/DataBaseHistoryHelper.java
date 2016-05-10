package com.bozemanwow.babykicks;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by bozem_000 on 4/17/2016.
 */
public class DataBaseHistoryHelper extends SQLiteOpenHelper {

    private static final String DataBase_Name = "BabyKicks.db";
    private static final int DataBase_Version = 1;

    public static final String Table_Name = "BabyKicked";

    //----Baby Coloums---
    public static final String Col_Date = "Date";
    public static final String Col_Clock_Start = "Clock_Time_Start";
    public static final String Col_Clock_End = "Clock_Time_End";
    public static final String Col_Clock_Length = "Clock_Length";
    public static final String Col_KickCount = "Kick_Count";
    private static String CREATE_BABYKICKED = "CREATE TABLE BabyKicked ( " +
            BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Date TEXT, " +
            "Clock_Time_Start TEXT, " +
            "Clock_Time_End TEXT, " +
            "Clock_Length TEXT, " +
            "Kick_Count INTEGER )";


    public DataBaseHistoryHelper(Context context) {
        super(context, DataBase_Name, null, DataBase_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BABYKICKED);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(db);
    }

}
