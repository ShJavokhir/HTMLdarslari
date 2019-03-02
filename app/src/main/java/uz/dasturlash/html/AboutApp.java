package uz.dasturlash.html;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class AboutApp extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_app);
        toolbar = (Toolbar) findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
    }
}

