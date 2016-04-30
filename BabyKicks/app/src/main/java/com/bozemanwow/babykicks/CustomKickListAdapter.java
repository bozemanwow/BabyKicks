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
public class CustomKickListAdapter extends ArrayAdapter<String>{
    private final Activity mActvity;
    List<String> mItemName;
    public CustomKickListAdapter(Activity context, List<String>  itemname) {
        super(context, R.layout.history_item,itemname);
        this.mItemName = itemname;
        this.mActvity = context;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=this.mActvity.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.history_item, null);
        ViewHolder k = new ViewHolder();
       k.text = (TextView) rowView.findViewById(R.id.image);
        k.text.setText(mItemName.get(position));
        rowView.setTag(k);
        return rowView;

    }
    public static class ViewHolder{

        public TextView text;



    }

}
