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
        cV.put(mDataBaseHistoryHelper.Col_Clock_Start, babyKick.getStart());
        cV.put(mDataBaseHistoryHelper.Col_Clock_End, babyKick.getEnd());
        cV.put(mDataBaseHistoryHelper.Col_Clock_Length, babyKick.getLength());
        cV.put(mDataBaseHistoryHelper.Col_KickCount, babyKick.getKicks());

        keyId =  database.insert(mDataBaseHistoryHelper.Table_Name, null, cV);

        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);

        return  keyId;
    }

    public List<BabyKickEvent> getAllKicks() {
        List<BabyKickEvent> kickList = new LinkedList<BabyKickEvent>();
        SQLiteDatabase database = open();

        Cursor cursor = database.rawQuery(
                " SELECT * FROM " + DataBaseHistoryHelper.Table_Name, null);

        if(cursor.moveToFirst()) {
            do{
                BabyKickEvent newKick = new BabyKickEvent();
                newKick.setId(Integer.parseInt(cursor.getString(0)));
                newKick.setDate(cursor.getString(1));
                newKick.setStart(cursor.getString(2));
                newKick.setEnd(cursor.getString(3));
                newKick.setLength(cursor.getFloat(4));
                newKick.setKicks(cursor.getInt(5));

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


