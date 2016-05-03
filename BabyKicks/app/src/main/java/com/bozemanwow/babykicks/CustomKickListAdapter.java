package com.bozemanwow.babykicks;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by bozem_000 on 4/30/2016.
 */
public class CustomKickListAdapter extends ArrayAdapter<BabyKickEvent>{
    private final Activity mActvity;
    List<BabyKickEvent> mItemName;
    public CustomKickListAdapter(Activity context, List<BabyKickEvent>  itemname) {
        super(context, R.layout.history_item_layout,itemname);
        this.mItemName = itemname;
        this.mActvity = context;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=this.mActvity.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.history_item_layout, null);
        ViewHolder SetUpVeiwData = new ViewHolder();

        SetUpVeiwData.Date = (TextView) rowView.findViewById(R.id.Date);
        SetUpVeiwData.Date.setText( String.valueOf(   mItemName.get(position).getDate()));
        rowView.setTag(SetUpVeiwData);

        SetUpVeiwData.Time = (TextView) rowView.findViewById(R.id.Time);
        SetUpVeiwData.Time.setText( String.valueOf(   mItemName.get(position).getStart()+"-"+ mItemName.get(position).getEnd()));
        rowView.setTag(SetUpVeiwData);

        SetUpVeiwData.Kicks = (TextView) rowView.findViewById(R.id.Kicks);
        SetUpVeiwData.Kicks.setText( String.valueOf("Baby Kicks:" +  mItemName.get(position).getKicks()));
        rowView.setTag(SetUpVeiwData);


        return rowView;

    }
    public static class ViewHolder{

        public TextView Date;
        public TextView Time;
        public TextView Kicks;


    }

}
