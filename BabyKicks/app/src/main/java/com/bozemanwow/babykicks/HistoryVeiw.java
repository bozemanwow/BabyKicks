package com.bozemanwow.babykicks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoryVeiw extends AppCompatActivity {

    ListView HisVe;
    List<String> Liste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_veiw);
        HisVe = (ListView)findViewById(R.id.listViewHistory);

        PopulateListView();

        SetUpItemListner();

    }
    ArrayAdapter<String> adp;
    private void PopulateListView()
    {
        String[] myItems ={"Blue","Green","Purple"};
        Liste = new ArrayList<String>();
        Collections.addAll(Liste,myItems);
        adp = new ArrayAdapter<String>(this,R.layout.history_item,Liste);
        HisVe.setAdapter(adp);

    }
    private void SetUpItemListner()
    {
        HisVe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Liste.remove(position);

                adp.notifyDataSetChanged();


            }
        });
    }

}
