//Darslar ro'yhatini ko'rsatish uchun adapter
package uz.dasturlash.html;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomAdapterLessonsList extends BaseAdapter {
    //quyidagi id bu category id si;
    int id = 0;
    String[] items;
    Context context;
    public CustomAdapterLessonsList(Context context,String[] items,int id){
        this.items = items;
        this.context = context;
        this.id = id;
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
        if (items[position].contains("NAZORAT")){


            if (convertView == null) {
                convertView = LayoutInflater.from(context).
                        inflate(R.layout.lessons_list_item, parent, false);
            }
            LinearLayout cat_layout = (LinearLayout) convertView.findViewById(R.id.cat_layout);
            cat_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,Task.class);
                    intent.putExtra("taskId",items[position]);
                    context.startActivity(intent);

                }
            });
            TextView itemName = (TextView) convertView.findViewById(R.id.lesson_name);
            itemName.setTypeface(null, Typeface.BOLD);;
            itemName.setTextColor(Color.RED);
            itemName.setText(position+1+". "+"Topshiriqlar");



        }else {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).
                        inflate(R.layout.lessons_list_item, parent, false);
            }
            LinearLayout cat_layout = (LinearLayout) convertView.findViewById(R.id.cat_layout);
            cat_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Lesson.class);
                    intent.putExtra("catId", id);
                    intent.putExtra("lesId", position);
                    context.startActivity(intent);

                }
            });
            TextView itemName = (TextView) convertView.findViewById(R.id.lesson_name);
            itemName.setText(position+". "+items[position]);
        }
        return convertView;
    }
}
