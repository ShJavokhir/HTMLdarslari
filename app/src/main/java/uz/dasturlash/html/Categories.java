//Bo'limlar ni ko'rsatish uchun

package uz.dasturlash.html;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.ListView;

public class Categories extends AppCompatActivity {
        GridView lstview;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.categories);
            lstview = (GridView) findViewById(R.id.lstview);
            //Bo'limlarni e'lon qilish
            String items[] = {"HTML ga kirish!","DEMO","DEMO","DEMO","DEMO","DEMO","DEMO","DEMO"};
            CustomAdapter adapter = new CustomAdapter(this,items);
            lstview.setAdapter(adapter);

        }
}


