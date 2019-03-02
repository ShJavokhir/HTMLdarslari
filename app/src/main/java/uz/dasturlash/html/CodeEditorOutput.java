package uz.dasturlash.html;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CodeEditorOutput extends AppCompatActivity {
    WebView output;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.code_editor_output);
        init();
    }
    private void init(){
        Intent intent = getIntent();
        String code =  intent.getStringExtra("code");
        toolbar = (Toolbar) findViewById(R.id.toolbar_output);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        output = (WebView) findViewById(R.id.outputnext);
        output.getSettings().setJavaScriptEnabled(true);
        output.setWebChromeClient(new WebChromeClient());
        output.loadData(code,"text/html; charset=UTF-8",null);


    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }



}
