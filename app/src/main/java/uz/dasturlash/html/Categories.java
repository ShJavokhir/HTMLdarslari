//Bo'limlar ni ko'rsatish uchun

package uz.dasturlash.html;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.GridLayoutAnimationController;
import android.widget.GridView;

public class Categories extends AppCompatActivity {
        GridView lstview;
        Toolbar toolbar;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.categories);
            toolbar = (Toolbar) findViewById(R.id.toolbar2);

            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            lstview = (GridView) findViewById(R.id.lstview);
            //Bo'limlarni e'lon qilish
            String items[] = {"HTML ga kirish","DEMO","DEMO","DEMO","DEMO"};
            CustomAdapter adapter = new CustomAdapter(this,items);
            Animation animation = AnimationUtils.loadAnimation(this,R.anim.lst_view_anim);
            GridLayoutAnimationController controller = new GridLayoutAnimationController(animation, .2f, .2f);
            lstview.setLayoutAnimation(controller);
            lstview.setAdapter(adapter);

        }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}


