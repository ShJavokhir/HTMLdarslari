package uz.dasturlash.html;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
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

public class Task extends AppCompatActivity {
    LinearLayout lnLayout;
    

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task);
        lnLayout = (LinearLayout) findViewById(R.id.lnLayout);
        Intent intent = getIntent();
        String taskId = intent.getStringExtra("taskId");
        readJsonTask(taskId);
    }
    void readJsonTask(String taskId){

        String file_name = "N_"+taskId.charAt(0)+".json";
        String data = "";
        Log.e("noob",file_name);
        try {
            InputStream is =getAssets().open(file_name);
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(is));
            String line="";
            while(line != null){
                line = bufferedReader.readLine();
                data = data+line;
            }
        } catch (IOException e) {
            Toast.makeText(this, "Xatolik: Topshiriq topilmadi. Iltimos bu haqida bizga xabar bering.", Toast.LENGTH_SHORT).show();
        }
        //JsonArray
        try {
            JSONArray jsonarray = new JSONArray(data);
            for (int i = 0; i < jsonarray.length(); i++) {
                final JSONObject jsonobject = jsonarray.getJSONObject(i);
                //Jsondagi ma'lumot turini tekshirish uchun olish
                String type = jsonobject.getString("type");
                //Sarlavhalarni olish
                if(type.equals("task")){
                    Log.e("noob","kirildi");
                    LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    TextView task =new TextView(this);
                    task.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
                    task.setText("(1) "+jsonobject.getString("task"));

                    task.setLayoutParams(llp);
                    lnLayout.addView(task);
                }else if(type.equals("taskImage")){

                }else if(type.equals("answer")){
                    LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    llp.setMargins(10,10,10,10);
                    llp.gravity = Gravity.RIGHT;
                    Button showAnswer = new Button(this);
                    showAnswer.setText("Javobni ko'rish");
                    showAnswer.setLayoutParams(llp);
                    showAnswer.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                String answer = jsonobject.getString("answer");
                                Intent intent = new Intent(Task.this,showTaskAnswer.class);
                                intent.putExtra("answer",answer);
                                startActivity(intent);
                            } catch (JSONException e) {

                            }
                        }
                    });
                    lnLayout.addView(showAnswer);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
