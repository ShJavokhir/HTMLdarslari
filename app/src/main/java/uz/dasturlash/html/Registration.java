package uz.dasturlash.html;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.rengwuxian.materialedittext.MaterialEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uz.dasturlash.html.models.UserRegistrationModel;

public class Registration  extends AppCompatActivity {
    private MaterialEditText name,email,password;
    private String _name,_email,_password,_region;
    private Button register,restore,sign_in;
    private uz.dasturlash.html.models.UserRegistrationModel registerModel;
    private uz.dasturlash.html.api.RetrofitInstance retrofit;
    private uz.dasturlash.html.api.Api api;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_page);
        init();


    }

    private void init(){
        name = (MaterialEditText) findViewById(R.id.name);
        email = (MaterialEditText) findViewById(R.id.email);
        password = (MaterialEditText) findViewById(R.id.password);
        register = (Button) findViewById(R.id.register);
        restore = (Button) findViewById(R.id.restore);
        sign_in = (Button) findViewById(R.id.sign_in);
        initButtons();
    }
    private void initButtons(){
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this,SignIn.class);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }
    private void updateValues(){
        try {

            _name = String.valueOf(name.getText());
            _email = String.valueOf(email.getText());
            _password = String.valueOf(password.getText());

        }catch (Exception e){
            Log.e("%777 Error: ",String.valueOf(e));
        }
    }
    private void register(){
        final ProgressBar progressBar = new ProgressBar(this);
        progressBar.setProgress(80);
        progressBar.setMax(100);
        progressBar.setVisibility(View.VISIBLE);

        updateValues();
        checkAll();

        FirebaseAuth registration = FirebaseAuth.getInstance();
        registration.createUserWithEmailAndPassword(_email,_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(_name)
                            .build();

                    user.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("salom", "User profile created");
                                        progressBar.setVisibility(View.INVISIBLE);

                                        registered();

                                    }
                                }
                            });


                }else{
                    Log.e("salom","O'tkazilmadi");
                    progressBar.setVisibility(View.INVISIBLE);

                }
            }
        });



        /*
        if(checkAll()){
            uz.dasturlash.html.models.UserRegistrationModel model = new UserRegistrationModel(_username,_first_name,_email,_region,_password);
            api = uz.dasturlash.html.api.RetrofitInstance.RetrofitInstance().create(uz.dasturlash.html.api.Api.class);
            Call<UserRegistrationModel> call = api.createPost(model);
            call.enqueue(new Callback<UserRegistrationModel>() {
                @Override
                public void onResponse(Call<UserRegistrationModel> call, Response<UserRegistrationModel> response) {
                    if(!response.isSuccessful()){
                       // Toast.makeText(Registration.this,"Xatolik yuzaga keldi !",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Registration.this,AboutApp.class);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(Registration.this,AboutApp.class);
                        startActivity(intent);
                        Toast.makeText(Registration.this,"Muvaffaqiyatli ro'yhatdan o'tkazildi !",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<UserRegistrationModel> call, Throwable t) {
                    Intent intent = new Intent(Registration.this,AboutApp.class);
                    startActivity(intent);
                    Toast.makeText(Registration.this,"Tarmoq nosozliklari bor !",Toast.LENGTH_LONG).show();

                }
            });
        }*/
    }
    private boolean checkAll(){

        if(this._name!="" && this._name.length()>=3 && this._name.length()<=125 &&
               this._email!="" && this._email.length()>=5 && this._email.contains("@") && this._email.length()<=125 &&
                    this._password.length()>=5 && this._password.length()<=200) {
            return true;
        }else{
            return false;

        }

    }
    private void registered(){

        AlertDialog.Builder builder = new AlertDialog.Builder(Registration.this);
        builder.setTitle("Muvaffaqiyatli bajarildi")
                .setMessage("Assalomu alaykum " + _name + " ! \n Ro'yhatdan o'tkazildi.")
                .setIcon(R.drawable.checked)
                .setCancelable(false)
                .setNegativeButton("Kirish",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(Registration.this,SignIn.class);
                                startActivity(intent);
                                //dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();


    }

}
