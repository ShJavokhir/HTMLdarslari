package uz.dasturlash.html;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ColorsAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<String> colors;
    public ColorsAdapter(Context context, ArrayList<String> colors){
        this.context = context;
        this.colors = colors;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return colors.size();
    }

    @Override
    public Object getItem(int position) {
        return colors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rootview;
        rootview = inflater.inflate(R.layout.colors_item,parent,false);



        View view = (View) rootview.findViewById(R.id.color);
        TextView colorName = (TextView) rootview.findViewById(R.id.colorCode);
        view.setBackgroundColor(Color.parseColor(colors.get(position)));
        colorName.setText(colors.get(position));


        return rootview;
    }
}
