package uz.dasturlash.html;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class test extends AppCompatActivity {
    ArrayList<String> questions = new ArrayList<>();
    ArrayList<String> A = new ArrayList<>();
    ArrayList<String> B = new ArrayList<>();
    ArrayList<String> C = new ArrayList<>();
    ArrayList<String> tItem= new ArrayList<>();
    TextView question,remained,a,b,c;
    String titem = "";
    String checked = "";
    RadioGroup radioGroup;
    Button next;
    int ball=0,id=0,checkedid=0,minBall=0,wrongAns=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        Typeface font = Typeface.createFromAsset(this.getAssets(),"fonts/RobotoRegular.ttf");

        question = (TextView) findViewById(R.id.question);
        question.setTypeface(font);
        a = (TextView) findViewById(R.id.A);
        a.setTypeface(font);
        b = (TextView) findViewById(R.id.B);
        b.setTypeface(font);
        c = (TextView) findViewById(R.id.C);
        c.setTypeface(font);
        next = (Button) findViewById(R.id.next);
        remained = (TextView) findViewById(R.id.remained);
        radioGroup = (RadioGroup) findViewById(R.id.tests);
        Intent intent = getIntent();
        String testId = intent.getStringExtra("testId");
        readJsonTask(testId);
        minBall = questions.size();
        setMinBall();
        question.setText(questions.get(0));
        a.setText(A.get(0));
        b.setText(B.get(0));
        c.setText(C.get(0));
        remained.setText(id+1+"/"+questions.size());

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextTest();
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
               if(checkedId==R.id.A){
                   checked = "A";
               }else if(checkedId==R.id.B){
                   checked = "B";
               }else{
                   checked = "C";
               }

            }
        });
    }

    @SuppressLint("ResourceAsColor")
    void nextTest(){
        LinearLayout ln = findViewById(R.id.lnLay);
        if(checked.equals(tItem.get(id))){
            ball++;
            Snackbar snackbar;
            snackbar = Snackbar.make(ln, "To'g'ri", Snackbar.LENGTH_SHORT);
            View snackBarView = snackbar.getView();
            snackBarView.setBackgroundColor(Color.parseColor("#66BB6A"));
            TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextSize(25);
            snackbar.show();
        }else{
            wrongAns++;
            Snackbar snackbar;
            snackbar = Snackbar.make(ln, "Noto'g'ri", Snackbar.LENGTH_SHORT);
            View snackBarView = snackbar.getView();
            snackBarView.setBackgroundColor(Color.parseColor("#F44336"));
            TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextSize(25);
            snackbar.show();
        }

        if(wrongAns==minBall){

            View view = getLayoutInflater().inflate(R.layout.test_result,null);
            TextView result = (TextView) view.findViewById(R.id.result);
            Button close = (Button) view.findViewById(R.id.okButton);
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            result.setText("Afsuski natijalar yaxshi emas :(\nBalingiz: "+ball+"\nTopish kerak bo'lgan eng past ball-"+(questions.size()-minBall));
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(view);
            AlertDialog alert = builder.create();
            alert.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
            alert.show();


        }
        if(wrongAns!=minBall) {
            if (id != questions.size() - 1) {
                id++;
                question.setText(questions.get(id));
                a.setText(A.get(id));
                b.setText(B.get(id));
                c.setText(C.get(id));
                remained.setText(id + 1 + "/" + questions.size());

            } else {

                View view = getLayoutInflater().inflate(R.layout.test_result,null);
                TextView result = (TextView) view.findViewById(R.id.result);
                Button close = (Button) view.findViewById(R.id.okButton);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                result.setText("Tabriklaymiz =)\nTestdan muvaffaqiyatli o'tdingiz.\nBalingiz: "+ball+"\nKeyingi bosqichga o'tishingiz mumkin.");


                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setView(view);

                AlertDialog alert = builder.create();
                alert.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));

                alert.show();

                Log.e("noob", "Sizning olgan balingiz :" + ball);
            }
        }
    }
    void readJsonTask(String testId){


        String file_name = "T_"+testId.charAt(0)+".json";
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
            Toast.makeText(this, "Xatolik. Iltimos bu haqida bizga xabar bering.", Toast.LENGTH_SHORT).show();
        }

        try {
            JSONArray jsonarray = new JSONArray(data);
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                String type = jsonobject.getString("type");
                if(type.equals("test")){
                    questions.add(jsonobject.getString("question"));
                    A.add(jsonobject.getString("A"));
                    B.add(jsonobject.getString("B"));
                    C.add(jsonobject.getString("C"));
                    tItem.add(jsonobject.getString("tItem"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }
    void setMinBall(){
        if(minBall==5){
            minBall = 2;
        }else if(minBall==10){
            minBall = 3;
        }else if(minBall==3){
            minBall = 1;
        }
    }
}
