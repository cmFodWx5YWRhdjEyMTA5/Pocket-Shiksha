package com.pocketshiksha.pocketshiksha.Students.misc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.pocketshiksha.pocketshiksha.R;


/**
 * Created by AIS on 26-May-17.
 */

public class EssentialListAdapter extends BaseAdapter {
    private final int[] listText;
    private Context ctx;

    public EssentialListAdapter(int[] listText, Context ctx) {
        this.listText = listText;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return listText.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View list;
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            list = new View(ctx);
            list = inflater.inflate(R.layout.list_layout, null);
            ImageView listTxt = (ImageView) list.findViewById(R.id.listText);
            listTxt.setBackgroundResource(listText[position]);

        } else {
            list = convertView;
        }
        return list;
    }
}

