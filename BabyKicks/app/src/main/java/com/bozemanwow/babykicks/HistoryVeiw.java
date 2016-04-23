package com.bozemanwow.babykicks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class HistoryVeiw extends AppCompatActivity {

    ListView HisVe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_veiw);
        HisVe = (ListView)findViewById(R.id.listViewHistory);
      //  SetUpItemListner();
        PopulateListView();
    }
    ArrayAdapter<String> adp;
    private void PopulateListView()
    {
        String[] myItems ={"Blue","Green","Purple"};
        adp = new ArrayAdapter<String>(this,R.layout.history_item,myItems);
        HisVe.setAdapter(adp);

    }
    private void SetUpItemListner()
    {
        HisVe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

             parent.removeView(view);
            }
        });
    }

}
