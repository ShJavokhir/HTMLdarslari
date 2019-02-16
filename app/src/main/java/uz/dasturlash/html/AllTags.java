package uz.dasturlash.html;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class AllTags extends AppCompatActivity {
    ListView allTagsList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_tags);
        allTagsList = (ListView) findViewById(R.id.allTagsList);
        ArrayList<String> allTags = new ArrayList<>();
        allTags.add("salom");
        allTags.add("salom");
        allTags.add("salom");
        allTags.add("salom");allTags.add("salom");
        allTags.add("salom");
        TagsAdapter adapter = new TagsAdapter(allTags,this);
        allTagsList.setAdapter(adapter);


    }
}
