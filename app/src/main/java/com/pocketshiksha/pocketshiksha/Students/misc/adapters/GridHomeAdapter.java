package com.pocketshiksha.pocketshiksha.Students.misc.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pocketshiksha.pocketshiksha.R;


/**
 * Created by AIS on 03-Apr-17.
 */

public class GridHomeAdapter extends BaseAdapter {
    private Context ctx;
    private final int[] Imageid;
    private final String[] gridcolor;
    private final String[] girdText;
    public GridHomeAdapter(Context ctx, int[] imageid, String[] gridcolor, String[] girdText) {

        this.ctx = ctx;
        Imageid = imageid;
        this.gridcolor = gridcolor;
        this.girdText = girdText;
    }

    @Override
    public int getCount() {
        return girdText.length;
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
        View grid;
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            grid = inflater.inflate(R.layout.notes_grid_items, null);
            ImageView book_poster = (ImageView) grid.findViewById(R.id.book_poster);
            TextView grid_text = (TextView) grid.findViewById(R.id.grid_text);
            book_poster.setImageResource(Imageid[position]);
            grid.setBackgroundColor(Color.parseColor(gridcolor[position]));
            grid_text.setText(girdText[position]);


        } else {
            grid = convertView;
        }
        return grid;
    }
}
