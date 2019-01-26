package uz.dasturlash.html;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class CodeEditor extends AppCompatActivity {
    Button check;
    CodeEditText code;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.code_editor);
        check = (Button) findViewById(R.id.check);
        code = (CodeEditText) findViewById(R.id.editCode);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("noob",String.valueOf(code.getText()));
            }
        });
    }
}
