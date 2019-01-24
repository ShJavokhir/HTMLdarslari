//Bo'limlarni ko'rsatish uchun adapter
package uz.dasturlash.html;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
    //Bo'limlar
    String[] items;
    Context context;
    public CustomAdapter(Context context,String[] items){
        this.items = items;
        this.context = context;
    }
    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.item_template, parent, false);
        }
        CardView layout = (CardView) convertView.findViewById(R.id.cat_layout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Lessons_list.class);
                intent.putExtra("id",position);
                context.startActivity(intent);

            }
        });
        TextView itemName = (TextView) convertView.findViewById(R.id.itemName);
        itemName.setText(items[position]);
        return convertView;
    }
}
