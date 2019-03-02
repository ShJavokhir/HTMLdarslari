//Bosh menyularni ko'rsatuvchi class
package uz.dasturlash.html;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    LinearLayout openCats,about_app,editor,colorShow,tags;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        test();
        setSupportActionBar(toolbar);
        openCats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Categories.class);
                startActivity(intent);
            }
        });
        about_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutApp.class);
                startActivity(intent);

            }
        });
        editor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CodeEditor.class);
                startActivity(intent);

            }
        });
        colorShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Colors.class);
                startActivity(intent);

            }
        });
        tags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AllTags.class);
                startActivity(intent);
            }
        });
    }
    void init(){
        SharedPreferences pref = getSharedPreferences("baza", Context.MODE_PRIVATE);
        if(!pref.contains("isFirstRunning")){
            WhenFirstRun first = new WhenFirstRun(MainActivity.this);
        }

        colorShow = (LinearLayout) findViewById(R.id.colorsShow);
        editor = (LinearLayout) findViewById(R.id.editor);
        openCats = (LinearLayout) findViewById(R.id.openCats);
        toolbar =  (Toolbar) findViewById(R.id.toolbar1);
        about_app = (LinearLayout) findViewById(R.id.about_app);
        tags = (LinearLayout) findViewById(R.id.tags);


    }
    void test(){

    }
}
