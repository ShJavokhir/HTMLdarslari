package uz.dasturlash.html;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class CodeEditor extends AppCompatActivity {
    Button check;
    CodeEditText output;
    WebView outputnext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.code_editor);
        check = (Button) findViewById(R.id.output);
        output = (CodeEditText) findViewById(R.id.editCode);
        output.setText("<html>\n<body>\n<p align=\"center\">Salom dunyo :)</p>\n</body>\n</html>");
        outputnext = (WebView) findViewById(R.id.outputnext);
        outputnext.getSettings().setJavaScriptEnabled(true);
        outputnext.setWebViewClient(new WebViewClient());

        outputnext.loadData(String.valueOf(output.getText()),"text/html; charset=UTF-8",null);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Log.e("noob",String.valueOf(code.getText()));
                outputnext.loadData(String.valueOf(output.getText()),"text/html; charset=UTF-8",null);
            }
        });
    }
}
