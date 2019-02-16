//Darslar ro'yhatini ko'rsatish uchun class
package uz.dasturlash.html;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;

public class Lessons_list extends AppCompatActivity {
    ListView listView;
    Toolbar toolbar;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lessons_list);
        test();
        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        GetLessonList lessons = new GetLessonList();
        String[] lesson =  lessons.GetLessonList(id+1);
        Log.e("idRaqam",String.valueOf(id));
        listView = (ListView) findViewById(R.id.lessons_list);
        CustomAdapterLessonsList adapter = new CustomAdapterLessonsList(Lessons_list.this,lesson,id);
        Animation animation = null;
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.lst_view_anim);
        //animation.setDuration(700);
        listView.startAnimation(animation);
        animation = null;

        listView.setAdapter(adapter);

    }
    void test(){
        SharedPreferences pref = getSharedPreferences("base", Context.MODE_PRIVATE);
        String name = pref.getString("salome","as");
        if(name.equals("0")){
          Log.e("salom","afsus");
        }else {
            Log.e("salom", name);
        }

        }
}
