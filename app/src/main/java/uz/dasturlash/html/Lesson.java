//Bitta darsni ko'rsatuvchi class
package uz.dasturlash.html;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Lesson extends AppCompatActivity {
    int catId=0,lesId=0;
    ArrayList<String[]>  contents = new ArrayList<String[]>();
    Boolean hasTask = false;
    String writeTag[] = new String[2];
    String code[] = new String[2];
    String codeOutput = "";
    String title = "";
    Toolbar toolbar;
    FragmentPageAdapter fragmentAdapter;
    TabLayout tabs;
    ViewPager mainViewPager ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson);
        code[0] = "";
        init();
        readLessonJson();
        fragmentAdapter = new FragmentPageAdapter(getSupportFragmentManager());
        mainViewPager = (ViewPager) findViewById(R.id.container);
        mainViewPager.setPageMargin(50);
        setupViewPager(mainViewPager);
        tabs= (TabLayout) findViewById(R.id.tabs);

        tabs.setupWithViewPager(mainViewPager);
        tabs.getTabAt(0).setIcon(R.drawable.ic_book_black_24dp);
        try {
            tabs.getTabAt(1).setIcon(R.drawable.ic_code);
            tabs.getTabAt(2).setIcon(R.drawable.ic_remove_red_eye_black_24dp);
            tabs.getTabAt(3).setIcon(R.drawable.ic_code);

        }catch (Exception e){

        }

    }
    public void setupViewPager(ViewPager viewPager)  {
        FragmentPageAdapter adapter = new FragmentPageAdapter(getSupportFragmentManager());
        //Fragmentlarni tavsiflab olish ya'ni lesson contentlarini qo'shish
        Lesson_Fragment_1 fr1 = new Lesson_Fragment_1();
        Lesson_Fragment_2 fr2 = new Lesson_Fragment_2();
        Lesson_Fragment_3 fr3 = new Lesson_Fragment_3();
        Lesson_Fragment_4 fr4 = new Lesson_Fragment_4();
        fr1.add(title,contents,mainViewPager);
        fr2.add(code,mainViewPager);
        fr3.add(code,mainViewPager);
        fr4.add(writeTag,mainViewPager);



        //Fragmentlarni adapterga qo'shish
        adapter.addFragment(fr1, "Dars");
            Log.e("alo","access");
            if(hasTask) {
                adapter.addFragment(fr4, "Topshiriq");
            }
        //Kod uchun tab. Agarda 'code' ga ma'lumot joylanmagan bo'lsa ko'rsatilmaydi
        if(!code[0].equals("")){
            Log.e("noob","kod bor");
            adapter.addFragment(fr2, "Namuna");
            adapter.addFragment(fr3, "Ko'rinish");
        }
        viewPager.setAdapter(adapter);

    }
    public void init(){
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        catId = intent.getIntExtra("catId",0)+1;
        lesId = intent.getIntExtra("lesId",0)+1;
        Log.e("keldi :) = ",String.valueOf(catId)+" -- "+String.valueOf(lesId));

    }
    public void readLessonJson(){
        //Local fayldagi jsondagi darsni o'qib olish
        String file_name = "l_"+catId+"_"+lesId+".json";
        String data = "";
        try {
            InputStream is =getAssets().open(file_name);
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(is));
            String line="";
            while(line != null){
                line = bufferedReader.readLine();
                data = data+line;
            }
        } catch (IOException e) {
            Toast.makeText(this, "Xatolik: Dars topilmadi. Iltimos bu haqida bizga xabar bering.", Toast.LENGTH_SHORT).show();
        }
        //JsonArray
        try {
            JSONArray jsonarray = new JSONArray(data);
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                //Jsondagi ma'lumot turini tekshirish uchun olish
                String type = jsonobject.getString("type");
                //Sarlavhalarni olish
                if(type.equals("main")){
                      String title = jsonobject.getString("title");
                      this.title = title;

                  }else if(type.equals("code")){
                      this.code[0] = jsonobject.getString("text");
                      this.code[1] = jsonobject.getString("about");

                  }else if(type.equals("writeTag")){
                    Log.e("aloo",jsonobject.getString("question"));
                    writeTag[0] = jsonobject.getString("question");
                    writeTag[1] = jsonobject.getString("answer");
                    hasTask = true;
                  }else{
                             String text = jsonobject.getString("text");
                             String[] temp = {type,text};
                             contents.add(temp);
                         }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
