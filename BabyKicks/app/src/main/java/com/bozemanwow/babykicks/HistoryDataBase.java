package com.bozemanwow.babykicks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.audiofx.BassBoost;
import android.provider.BaseColumns;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by bozem_000 on 4/16/2016.
 */
public class HistoryDataBase {

    private Context mContext;
    private DataBaseHistoryHelper mDataBaseHistoryHelper;

    public HistoryDataBase(Context context) {
        mContext = context;
        mDataBaseHistoryHelper = new DataBaseHistoryHelper(context);
    }

    private SQLiteDatabase open() {
        return mDataBaseHistoryHelper.getWritableDatabase();
    }

    private void close(SQLiteDatabase database) {
        database.close();
    }

    public  long insertData(BabyKickEvent babyKick) {
        long keyId = -5;
        SQLiteDatabase database = open();
        database.beginTransaction();

        ContentValues cV = new ContentValues();
        cV.put(mDataBaseHistoryHelper.Col_Date, babyKick.getDate() );
        cV.put(mDataBaseHistoryHelper.Col_Clock_Start,  String.valueOf(babyKick.getStart()));
        cV.put(mDataBaseHistoryHelper.Col_Clock_End, babyKick.getEnd());
        cV.put(mDataBaseHistoryHelper.Col_Clock_Length, String.valueOf(babyKick.getLength()));
        cV.put(mDataBaseHistoryHelper.Col_KickCount, babyKick.getKicks());

        keyId =  database.insert(mDataBaseHistoryHelper.Table_Name, null, cV);

        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);

        return  keyId;
    }
    private int getIntFromColumnName(Cursor cursor, String columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        return cursor.getInt(columnIndex);
    }

    private String getStringFromColumnName(Cursor cursor, String columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        return cursor.getString(columnIndex);
    }

    private double getDoubleFromColumnName(Cursor cursor, String columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        return cursor.getDouble(columnIndex);
    }
    public List<BabyKickEvent> getAllKicks() {
        List<BabyKickEvent> kickList = new LinkedList<BabyKickEvent>();
        SQLiteDatabase database = open();

        Cursor cursor = database.rawQuery(
                " SELECT * FROM " + DataBaseHistoryHelper.Table_Name, null);

        if(cursor.moveToFirst()) {
            do{
                BabyKickEvent newKick = new BabyKickEvent();
                newKick.setId(getIntFromColumnName(cursor, BaseColumns._ID));
                newKick.setDate(getStringFromColumnName(cursor, DataBaseHistoryHelper.Col_Date));
                newKick.setStart(getStringFromColumnName(cursor, DataBaseHistoryHelper.Col_Clock_Start));
                newKick.setEnd( getStringFromColumnName(cursor,  DataBaseHistoryHelper.Col_Clock_End));
                newKick.setLength(Float.parseFloat(getStringFromColumnName(cursor,  DataBaseHistoryHelper.Col_Clock_Length)));
                newKick.setKicks(getIntFromColumnName(cursor, DataBaseHistoryHelper.Col_KickCount));

                kickList.add(newKick);

            }while(cursor.moveToNext());
        }

        return kickList;
    }

    public void delete_byId(int id) {
        SQLiteDatabase database = open();
        database.delete(DataBaseHistoryHelper.Table_Name, BaseColumns._ID + "=" + "'"+id+"'", null);
    }
}


