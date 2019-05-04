package uz.dasturlash.html;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import thereisnospon.codeview.CodeView;
import thereisnospon.codeview.CodeViewTheme;

public class TagView extends AppCompatActivity {
    CollapsingToolbarLayout ColTool;
    LinearLayout TagViewLayout;
    String file_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tag_view);
        TagViewLayout = (LinearLayout) findViewById(R.id.TagViewlayout);
        ColTool = (CollapsingToolbarLayout) findViewById(R.id.ColTool);
        Intent intent = getIntent();
        file_name = intent.getStringExtra("file");
        String description = intent.getStringExtra("description");
        ColTool.setTitle(description);
        readContent();
    }
    private void readContent(){
        Typeface font = Typeface.createFromAsset(TagView.this.getAssets(),"fonts/RobotoRegular.ttf");

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
            Toast.makeText(this, "Xatolik: Teg topilmadi. Iltimos bu haqda bizga xabar bering.", Toast.LENGTH_SHORT).show();
        }

        try {
            JSONArray jsonarray = new JSONArray(data);
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                String type = jsonobject.getString("type");
                 if(type.equals("text")){
                     String content = jsonobject.getString("text");
                     TextView text = new TextView(TagView.this);

                     LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                     llp.setMargins(30, 30, 30, 30); // llp.setMargins(left, top, right, bottom);
                     text.setText(content);
                     text.setTextColor(Color.parseColor("#797777"));
                     text.setLayoutParams(llp);
                     text.setTypeface(font);
                     text.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                     TagViewLayout.addView(text);
                 }else if(type.equals("code")){
                     CodeView code = new CodeView(this);
                     code.setTheme(CodeViewTheme.DARKULA).fillColor();
                     code.showCode(jsonobject.getString("text"));
                     LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                     llp.setMargins(30, 30, 30, 30);
                     code.setLayoutParams(llp);

                     TagViewLayout.addView(code);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
