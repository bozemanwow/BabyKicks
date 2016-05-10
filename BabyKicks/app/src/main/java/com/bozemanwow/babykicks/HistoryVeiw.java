package com.bozemanwow.babykicks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;



import java.util.List;

public class HistoryVeiw extends AppCompatActivity {

    ListView HisVe;
    List<BabyKickEvent> Liste;
    HistoryDataBase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_veiw);
        HisVe = (ListView)findViewById(R.id.listViewHistory);
        db = new HistoryDataBase(this);
        PopulateListView();

        SetUpItemListner();

    }
     CustomKickListAdapter adp;
    private void PopulateListView()
    {


        Liste = db.getAllKicks();

        adp = new CustomKickListAdapter(this, Liste );
        HisVe.setAdapter(adp);

    }
    private void SetUpItemListner()
    {
        HisVe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                db.delete_byId(Liste.get(position).getId());
                Liste.remove(position);

                adp.notifyDataSetChanged();
              //  Toast.makeText(HistoryVeiw.this,"Removed",Toast.LENGTH_SHORT).show();

            }
        });
    }

}
