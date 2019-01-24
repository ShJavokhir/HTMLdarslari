//Tab dagi 1-sahifa
package uz.dasturlash.html;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import thereisnospon.codeview.CodeView;
import thereisnospon.codeview.CodeViewTheme;

public class Lesson_Fragment_2 extends Fragment {
    ViewPager viewpager;
    String[] code= new String[1];
    CodeView codeView;
    Button next;
    Button prev;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lesson_fragment_2, container, false);
        next = view.findViewById(R.id.next);
        prev = view.findViewById(R.id.prev);

        codeView = (CodeView) view.findViewById(R.id.codeView);
        codeView.setTheme(CodeViewTheme.ANDROIDSTUDIO).fillColor();
try {
    codeView.showCode(code[0]);

}catch (Exception e){
}
        init();
        return view;
    }
    public void add(String[] code, ViewPager viewPager){
        this.viewpager = viewPager;
        this.code =code;
    }
    public void init(){
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewpager.setCurrentItem(viewpager.getCurrentItem()+1);
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
