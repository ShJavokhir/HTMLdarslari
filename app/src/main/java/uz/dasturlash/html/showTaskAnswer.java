package uz.dasturlash.html;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import thereisnospon.codeview.CodeView;
import thereisnospon.codeview.CodeViewTheme;

public class showTaskAnswer  extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_task_answer);
        Intent intent = getIntent();
        String answer = intent.getStringExtra("answer");
        CodeView taskCodeAnswer = (CodeView) findViewById(R.id.taskAnswerCode);
        taskCodeAnswer.setTheme(CodeViewTheme.ANDROIDSTUDIO).fillColor();
        taskCodeAnswer.showCode(answer);

    }
}
