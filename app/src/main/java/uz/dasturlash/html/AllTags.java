package uz.dasturlash.html;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.ArrayList;

public class AllTags extends AppCompatActivity {
    ListView allTagsList;
    ArrayList<String[]> allTags = new ArrayList<String[]>();
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_tags);
        init();

        loadAllTags();

        TagsAdapter adapter = new TagsAdapter(allTags, this);
        allTagsList.setAdapter(adapter);


    }
    private void loadAllTags(){
        String[] temp = {"<html>", "html.json"};
        allTags.add(temp);

    }
    void init(){
        toolbar = (Toolbar) findViewById(R.id.toolbar_tags);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        allTagsList = (ListView) findViewById(R.id.allTagsList);

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
