package uz.dasturlash.html;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WhenFirstRun {
    Context context;

    SharedPreferences pref;
    SharedPreferences.Editor shEdit;

    public WhenFirstRun(Context context){
        this.context = context;
        pref = context.getSharedPreferences("baza", context.MODE_PRIVATE);
        shEdit = pref.edit();
        //showTutorial();
        createLessons();

        shEdit.putBoolean("isFirstRunning",false);

    }
    void createLessons(){
        String temp="";
        int a[] = {5,8,1,3,4,5,2};
        for(int i=0;i<10;i++){
            temp = "category_"+String.valueOf(i+1);
            shEdit.putBoolean(temp,true);
        }
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[i];j++) {
                    temp = "lesson_" + String.valueOf(i + 1) + "_" + String.valueOf(j + 1);
                    shEdit.putBoolean(temp, false);

            }
        }
        shEdit.commit();
    }
    void showTutorial(){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View view = inflater.inflate(R.layout.test_result,null);

        TextView result = (TextView) view.findViewById(R.id.result);
        Button close = (Button) view.findViewById(R.id.okButton);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"sa;lom",Toast.LENGTH_SHORT).show();
                shEdit.putBoolean("isFirstRunning",true);

            }
        });
        result.setText("Ushbu dasturni tanlaganingizda xursandiz\nDarslarga muvaffaqiyat tilaymiz !!! ");


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);

        AlertDialog alert = builder.create();
        alert.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));

        alert.show();
    }
}
