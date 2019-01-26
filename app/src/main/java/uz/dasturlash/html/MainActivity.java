//Bosh menyularni ko'rsatuvchi class
package uz.dasturlash.html;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout openCats,about_app,editor;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
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
    }
    void init(){
        editor = (LinearLayout) findViewById(R.id.editor);
        openCats = (LinearLayout) findViewById(R.id.openCats);
        toolbar =  (Toolbar) findViewById(R.id.toolbar1);
        about_app = (LinearLayout) findViewById(R.id.about_app);


    }
}
