package uz.dasturlash.html;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ColorsAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<String> colors;
    ClipboardManager clipboard;
    public ColorsAdapter(Context context, ArrayList<String> colors){
        this.context = context;
        this.colors = colors;
        inflater = ( LayoutInflater )context.
        getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);



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
    public View getView(final int position, View convertView, ViewGroup parent) {

        View rootview;
        rootview = inflater.inflate(R.layout.colors_item,parent,false);



        View view = (View) rootview.findViewById(R.id.color);
        final TextView colorName = (TextView) rootview.findViewById(R.id.colorCode);
        view.setBackgroundColor(Color.parseColor(colors.get(position)));
        colorName.setText(colors.get(position));
        ImageButton copyColor =(ImageButton) rootview.findViewById(R.id.copyColor);
        copyColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipData clip = ClipData.newPlainText("Color Code", colors.get(position));
                clipboard.setPrimaryClip(clip);
                Toast.makeText(context,"Rang kodidan nusxa olindi",Toast.LENGTH_SHORT).show();
            }
        });
        return rootview;
    }
}
