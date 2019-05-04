//Tab dagi 1-sahifa
package uz.dasturlash.html;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class Lesson_Fragment_3 extends Fragment {
    Button next,prev;
    ViewPager viewpager;
    WebView output;
    String[] code = new String[2];
    private boolean isLastTab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lesson_fragment_3, container, false);
        next = view.findViewById(R.id.next);
        prev = view.findViewById(R.id.prev);
        output = (WebView) view.findViewById(R.id.output);
        loadOutput();
        init();
        if(!isLastTab){
            unlockNextLesson();
        }
        return view;
    }

    public void add(String[] code, ViewPager viewpager, boolean isLastTab){
  //      this.codeView = codeView;
        this.code = code;
        this.viewpager = viewpager;
        this.isLastTab = isLastTab;
    }

    private void unlockNextLesson(){
        //Keyingi darsni ochish
        Toast.makeText(getContext(), "Keyingi dars ochildi", Toast.LENGTH_SHORT).show();
    }

    public void loadOutput(){
        output.loadData(code[0], "text/html; charset=UTF-8",null);
    }

    public void init(){
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewpager.setCurrentItem(viewpager.getCurrentItem()-1);
            }
        });
    }
}
