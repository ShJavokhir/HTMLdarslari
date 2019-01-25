//Darslar ro'yhatini ko'rsatish uchun class
package uz.dasturlash.html;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.TextView;

public class Lessons_list extends AppCompatActivity {
    ListView listView;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lessons_list);
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
}
