//Tab dagi 1-sahifa
package uz.dasturlash.html;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Lesson_Fragment_4 extends Fragment {
    String que="",answer = "";
    View view;
    TextView question;
    EditText code;
    Button next,prev;
    ViewPager viewpager;
    RelativeLayout rl;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lesson_fragment_4, container, false);
        question = (TextView) view.findViewById(R.id.codeQuestion);
        code = (EditText) view.findViewById(R.id.code);
        next = (Button) view.findViewById(R.id.next);
        prev = (Button) view.findViewById(R.id.prev);
        rl = (RelativeLayout) view.findViewById(R.id.rl);
        init();
        return view;
    }
    public void add(String[] writeCode,ViewPager viewpager){
        que = writeCode[0];
        answer = writeCode[1];
        this.viewpager = viewpager;
    }
    void init(){
        question.setText(que);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(String.valueOf(code.getText()).toLowerCase().equals(answer)){
                    getActivity().onBackPressed();
                }else{
                    Snackbar snackbar;
                    snackbar = Snackbar.make(rl, "Noto'g'ri", Snackbar.LENGTH_SHORT);
                    View snackBarView = snackbar.getView();
                    snackBarView.setBackgroundColor(Color.parseColor("#F44336"));
                    TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextSize(25);
                    snackbar.show();
                    unlockNextLesson();

                }
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewpager.setCurrentItem(viewpager.getCurrentItem()-1);

            }
            
        });
    }
    private void unlockNextLesson(){
        //Keyingi darsni ochish
        Toast.makeText(getContext(), "Keyingi dars ochildi", Toast.LENGTH_SHORT).show();
    }


}
