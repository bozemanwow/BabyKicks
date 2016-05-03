package com.bozemanwow.babykicks;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Chronometer;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;


public class MainActivityIntro extends AppCompatActivity {
    TextView KPH;
    Chronometer Timer;
    int Kicks = 0;
    long twohours = (60 * 60)*2 * 100;
    long elapsedMillis=0;
    HistoryDataBase DB;
   String sDate;
    String STime;

    Time mClock;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_intro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DB = new HistoryDataBase(this);
        mClock = new Time();
        mClock.setToNow();
        SimpleDateFormat da = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        sDate = da.format(new Date(0));

        int hour = mClock.hour;
        if(hour > 12)
            hour -= 12;
        String hourtemp,minutetemp;
        if(hour < 10)
            hourtemp ="0"+String.valueOf(hour);
        else
            hourtemp =String.valueOf(hour);
        if(mClock.minute < 10)
            minutetemp="0"+String.valueOf(mClock.minute);
        else
            minutetemp=String.valueOf(mClock.minute);

        STime= hourtemp +":"+minutetemp;

        Timer = (Chronometer) findViewById(R.id.LastKickTimer);
        Timer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            public void onChronometerTick(Chronometer cArg) {

                if(elapsedMillis  <  twohours )
                {
                    elapsedMillis = SystemClock.elapsedRealtime() - cArg.getBase();


                }
                else
                {
                    InsertData();
                    Kicks = 0;
                    elapsedMillis = 0;
                    cArg.stop();
                }
                cArg.setText(DateFormat.format("mm:ss",elapsedMillis));
            }
        } );
        KPH = (TextView) findViewById(R.id.textViewKicksPerHour);
        KPH.setText(STime);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }

    public void InsertData()
    {
        int hour = mClock.hour;
        if(hour > 12)
           hour -= 12;
        String hourtemp,minutetemp;
        if(hour < 10)
            hourtemp ="0"+String.valueOf(hour);
        else
            hourtemp =String.valueOf(hour);
        if(mClock.minute < 10)
            minutetemp="0"+String.valueOf(mClock.minute);
        else
            minutetemp=String.valueOf(mClock.minute);
        String endTime =hourtemp +":"+minutetemp;
    if(    -1>DB.insertData(new BabyKickEvent(sDate,STime,endTime,elapsedMillis,Kicks)))
    {
        KPH.setText("Nope");
    }
        STime = endTime;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity_intro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void AddKick(View v) {

        if (elapsedMillis >= 0 && Kicks < 10) {

            Kicks++;
            KPH.setText("Past Hour " + String.valueOf(Kicks));
        }
        else if( Kicks < 9)
        {
            Timer.setBase(SystemClock.elapsedRealtime());
            Timer.start();
            Kicks++;
            KPH.setText("Past Hour " + String.valueOf(Kicks));
        }
        else
        {
            Kicks++;
            KPH.setText("Past Hour " + String.valueOf(0));
            InsertData();
            Kicks = 0;
            elapsedMillis = 0;

            Timer.setBase(SystemClock.elapsedRealtime());
            Timer.setText(DateFormat.format("mm:ss",elapsedMillis));
        }
    }
    public void ResetInfo(View v)
    {
        Timer.stop();
        Kicks = 0;
        elapsedMillis = 0;
        KPH.setText("Past Hour " + String.valueOf(0));
        Timer.setText(DateFormat.format("mm:ss",elapsedMillis));
    }
    public void VeiwHistory(View v)
    {
    Intent History_Reveiw = new Intent(this, HistoryVeiw.class);
        startActivity(History_Reveiw);
    }
    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MainActivityIntro Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.bozemanwow.babykicks/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MainActivityIntro Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.bozemanwow.babykicks/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
