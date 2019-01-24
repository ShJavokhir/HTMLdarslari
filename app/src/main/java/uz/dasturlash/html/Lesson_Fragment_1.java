//Tab dagi 1-sahifa
package uz.dasturlash.html;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class Lesson_Fragment_1 extends Fragment {
    Button next;

    ArrayList<String[]>  contents = new ArrayList<String[]>();
    ViewPager viewpager;
    String title= "";
    LinearLayout lnLayout;
    public void add(String title, ArrayList<String[]> contents,ViewPager viewpager){
        //Lesson classdan ma'lumotlarni o'qib olish)
        this.contents = contents;
        this.title = title;
        this.viewpager = viewpager;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lesson_fragment_1, container, false);
        lnLayout = (LinearLayout) view.findViewById(R.id.lnLayout1);
        next = (Button) view.findViewById(R.id.next);


        TextView title = (TextView) view.findViewById(R.id.titlePost);
        title.setText(this.title);
        //Darsni textlarini qo'shish :)
        fillContent();
        return view;
    }
    public void fillContent(){
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewpager.setCurrentItem(viewpager.getCurrentItem()+1);
            }
        });


        for (int i=0;i<contents.size();i++){
            String[] temp = contents.get(i);
            //TextView
            //Type ni aniqlab olish
            String type = temp[0];
            TextView text = new TextView(getActivity());
            text.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            if(type.equals("simpleText")){
                LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                llp.setMargins(0, 30, 30, 30); // llp.setMargins(left, top, right, bottom);
                text.setText(temp[1]);
                text.setTextColor(Color.parseColor("#000000"));
                text.setLayoutParams(llp);
                text.setTextSize(16);            }else if(type.equals("italicText")){
                LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                llp.setMargins(0, 30, 30, 30); // llp.setMargins(left, top, right, bottom);
                text.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
                text.setText(temp[1]);
                text.setTextColor(Color.parseColor("#00BBFF"));
                text.setLayoutParams(llp);
                text.setTextSize(16);
            }else if(type.equals("warningText")){
                text.setText(temp[1]);
                text.setTextColor(Color.parseColor("#FF9800"));
                text.setBackgroundResource(R.drawable.remember_text);
                LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                text.setTextSize(17);                llp.setMargins(0, 30, 30, 30); // llp.setMargins(left, top, right, bottom);
                text.setLayoutParams(llp);
                text.setPadding(40,50,20,50);
            }else if(type.equals("codeText")){
                text.setText(temp[1]);
                text.setTextColor(Color.parseColor("#00BBFF"));

            }
           lnLayout.addView(text);
        }

    }
}
