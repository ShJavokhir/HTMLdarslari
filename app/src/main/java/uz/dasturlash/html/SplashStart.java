//Splash classi
package uz.dasturlash.html;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashStart extends AppCompatActivity {
    private TextView start_title;
    private static int Splash_time = 500; //3000
    private ImageView start_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashstart);

        start_title = (TextView)findViewById(R.id.start_title);
        start_image = (ImageView) findViewById(R.id.start_image);
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.blink);
        Animation anim2 = AnimationUtils.loadAnimation(this,R.anim.yonibochish);
        start_title.startAnimation(anim);
        start_image.startAnimation(anim2);
        final Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    Log.e("tekshirish","Ishladi !");

                    sleep(500);

                    Intent intent = new Intent(SplashStart.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                    e.printStackTrace();
                }

            }
        };
        thread.start();
    }
}
