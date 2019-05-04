package uz.dasturlash.html;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uz.dasturlash.html.models.Notification;

public class Notification_body  extends AppCompatActivity {
    private TextView notification_title, notification_text;
    private ImageView notification_image;
    private uz.dasturlash.html.api.RetrofitInstance retrofit = new uz.dasturlash.html.api.RetrofitInstance();
    private uz.dasturlash.html.api.Api api;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_body);
        init();
        getNotification();
    }
    private void getNotification(){
        api = retrofit.RetrofitInstance().create(uz.dasturlash.html.api.Api.class);
        Call<Notification> call = api.getPost();
        call.enqueue(new Callback<Notification>() {
            @Override
            public void onResponse(Call<Notification> call, Response<Notification> response) {
                String text = response.body().getText();
                notification_title.setText(response.body().getTitle());
                notification_text.setText(response.body().getText());
            }

            @Override
            public void onFailure(Call<Notification> call, Throwable t) {
                Log.e("javohir",String.valueOf(t));
            }
        });
    }
    private void init(){

        notification_title = (TextView) findViewById(R.id.notification_title);
        notification_text = (TextView) findViewById(R.id.notification_text);
        notification_image = (ImageView) findViewById(R.id.notification_image);

    }
}
