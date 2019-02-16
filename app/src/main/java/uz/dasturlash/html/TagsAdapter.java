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
    String colors[] = {"#F02D3A","#9197AE","#93BC42","#52CFF2","#526FF2","#9452F2","#E452F2","#F2527F","#DAF252","#F2A32E","#93BC42","#F4EDEE","#455A64"};
    //"#C62828","#AD1457","#6A1B9A","#4527A0","#283593","#1565C0","#00695C","#2E7D32","#558B2F","#9E9D24","#F9A825","#D84315","#4E342E","#424242","#37474F","#FFC107"
    ArrayList<String> tagNames = new ArrayList<>();
    LayoutInflater inflater;
    public TagsAdapter(ArrayList<String> tagNames,Context context){
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
        draw.setColor(Color.parseColor(colors[randColor.nextInt(12)]));
        tagColor.setBackground(draw);
        tagName.setText(tagNames.get(position));
        LinearLayout lnLayout2 = rootview.findViewById(R.id.lnLayout2);
        lnLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,TagView.class);
                intent.putExtra("tag",tagNames.get(position));
                context.startActivity(intent);

            }
        });
        return rootview;
    }
}
