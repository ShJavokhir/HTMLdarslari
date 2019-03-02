package uz.dasturlash.html;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class TagsAdapter extends BaseAdapter {
    Context context;
    String colors[] = {"#F44336","#42A5F5","#296FF2","#4DD0E1","#A5D67A","#CDDC39","#FFEB3B","#FF8F00","#6D4C41","#607D8B"};
    //"#C62828","#AD1457","#6A1B9A","#4527A0","#283593","#1565C0","#00695C","#2E7D32","#558B2F","#9E9D24","#F9A825","#D84315","#4E342E","#424242","#37474F","#FFC107"
    ArrayList<String[]>  tagNames = new ArrayList<String[]>();
    LayoutInflater inflater;
    public TagsAdapter(ArrayList<String[]> tagNames,Context context){
        this.tagNames = tagNames;
        this.context = context;
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return tagNames.size();
    }

    @Override
    public Object getItem(int position) {
        return tagNames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rootview;
        rootview = inflater.inflate(R.layout.all_tags_element,parent,false);
        TextView tagName = rootview.findViewById(R.id.tagName);
        View tagColor = rootview.findViewById(R.id.tagColor);
        TextView nums = rootview.findViewById(R.id.nums);
        nums.setText(String.valueOf(position));
        GradientDrawable draw = new GradientDrawable();
        draw.setShape(GradientDrawable.OVAL);
        Random randColor = new Random();
        draw.setColor(Color.parseColor(colors[randColor.nextInt(9)]));
        tagColor.setBackground(draw);

        final String temp[] = tagNames.get(position);
        tagName.setText(temp[0]);
        LinearLayout lnLayout2 = rootview.findViewById(R.id.lnLayout2);
        lnLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,TagView.class);
                intent.putExtra("file",temp[1]);
                intent.putExtra("description",temp[0]);
                context.startActivity(intent);

            }
        });
        return rootview;
    }
}
