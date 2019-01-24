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

public class Lesson_Fragment_3 extends Fragment {
    Button next,prev;
    ViewPager viewpager;
    WebView output;
    String[] code = new String[2];
//    String codeView = "";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lesson_fragment_3, container, false);
        next = view.findViewById(R.id.next);
        prev = view.findViewById(R.id.prev);
        output = (WebView) view.findViewById(R.id.output);
        loadOutput();
        init();
        return view;
    }

    public void add(String[] code, ViewPager viewpager){
  //      this.codeView = codeView;
        this.code = code;
        this.viewpager = viewpager;
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
