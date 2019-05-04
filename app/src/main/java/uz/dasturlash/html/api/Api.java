package uz.dasturlash.html.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import uz.dasturlash.html.models.Notification;

public interface Api {
    @POST("api/register_user.php")
    Call<uz.dasturlash.html.models.UserRegistrationModel> createPost(@Body uz.dasturlash.html.models.UserRegistrationModel post);

    @GET("api/get_notification.php")
    Call<uz.dasturlash.html.models.Notification> getPost();

}
